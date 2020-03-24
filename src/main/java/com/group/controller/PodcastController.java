package com.group.controller;

import com.group.dao.Podcast;
import com.group.service.PodcastService;
import com.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
