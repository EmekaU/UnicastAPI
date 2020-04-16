package com.group.controller;
import com.group.service.PodcastService;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping(value = "podcast")
public class PodcastController {

    private final String token_key = "token";

    @Autowired
    private final PodcastService podcastService;

    public PodcastController(PodcastService podcastService){
        this.podcastService = podcastService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> createPodcast(@RequestHeader Map<String, String> header,
                                           @RequestBody Map<String, String> body
                                           ) {
        System.out.println(body);
        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean success = podcastService.createPodcast(header.get(this.token_key), body);

        HttpStatus status = body != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(status);
    }

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserPodcasts(@RequestHeader Map<String, String> header,
                                         @PathVariable("username") String username){

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getPodcastsBelongingTo(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPodcastById(@RequestHeader Map<String, String> header,
                                         @PathVariable("id") String id){

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService, HttpStatus.OK);
    }
}
