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
//import org.apache.commons.fileupload.MultipartStream
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "podcast")
public class PodcastController {

    private final String token_key = "token";

    @Autowired
    private final PodcastService podcastService;

    List<String> files = new ArrayList<String>();
    public PodcastController(PodcastService podcastService){
        this.podcastService = podcastService;
    }
    private final Path rootLocation = Paths.get("_Path_To_Save_The_File");

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> createPodcast(@RequestHeader Map<String, String> header,
                                           @RequestBody Map<String, ?> body
                                           ) throws IOException {
        System.out.println(body);

//        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {
//
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        //boolean success = podcastService.createPodcast(header.get(this.token_key), body);

        HttpStatus status = body != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(status);
    }

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPodcasts(@RequestHeader Map<String, String> header,
                                         @PathVariable("username") String username){

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getPodcastsBelongingTo(username), HttpStatus.OK);
    }
}
