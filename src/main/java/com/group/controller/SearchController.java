package com.group.controller;

import com.group.service.QueryService;
import com.group.utilities.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("search")
public class SearchController {

    private static Logger log = LogManager.getLogger(SearchController.class.getName());

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "/wild", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doWildSearch(@RequestParam String query, @RequestHeader Map<String, String> header){

        String token_key = "token";
        if(!header.containsKey(token_key) || JwtUtils.tokenIsExpired(header.get(token_key))) {

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(queryService.wildSearch(query), HttpStatus.OK);
    }
}
