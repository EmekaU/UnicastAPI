package com.group.controller;

import com.group.dao.Podcast;
import com.group.service.PodcastService;
import com.group.service.UserService;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "podcast")
public class PodcastController {

    @Autowired
    private final PodcastService podcastService;

    public PodcastController(PodcastService podcastService){
        this.podcastService = podcastService;
    }

    @RequestMapping()
    public ResponseEntity<?> createPodcast(@RequestHeader Map<String, String> headers,
                                           @RequestParam("file")MultipartFile file,
                                           @RequestBody Map<String, String> body){

        String token = headers.get("token");

        try {
            boolean success = podcastService.createPodcast(token, body, file.getBytes());

            HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

            return new ResponseEntity<>(status);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPodcasts(@RequestHeader Map<String, String> header,
                                         @PathVariable("username") String username){
        String token_key = "token";
        if(!header.containsKey(token_key) || JwtUtils.tokenIsExpired(header.get(token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getPodcastsBelongingTo(username), HttpStatus.OK);
    }
}
