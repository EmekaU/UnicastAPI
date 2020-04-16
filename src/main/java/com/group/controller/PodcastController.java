package com.group.controller;
import com.group.dao.Comment;
import com.group.service.CommentService;
import com.group.service.PodcastService;
import com.group.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "podcast")
public class PodcastController {

    private final String token_key = "token";

    private final PodcastService podcastService;
    private final CommentService commentService;

    @Autowired
    public PodcastController(PodcastService podcastService, CommentService commentService){
        this.podcastService = podcastService;
        this.commentService = commentService;
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

        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

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
                                         @PathVariable("id") long id){

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getPodcastById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPodcastByCategory(@RequestHeader Map<String, String> header,
                                                  @RequestParam(name = "category") String category) {

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getPodcastsByCategory(category), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/recent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPodcastByKey(@RequestHeader Map<String, String> header) {

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.podcastService.getRecentPodcasts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComment(@RequestHeader Map<String, String> header,
                                        @RequestBody Map<String, String> body)  {

        if(!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean success = this.commentService.addComment(body.get("message"), Long.parseLong(body.get("podcast_id")));

        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(status);
    }

    @RequestMapping(value = "get/comments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getComments(@RequestHeader Map<String, String> header,
                                        @PathVariable("id") String id) {

        if (!header.containsKey(this.token_key) || JwtUtils.tokenIsExpired(header.get(this.token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Comment> comments = this.podcastService.getCommentsBelongingToPodcastWithId(Long.getLong(id));

        HttpStatus status = comments != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(comments, status);
    }
}
