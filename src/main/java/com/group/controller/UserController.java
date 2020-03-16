package com.group.controller;
import com.group.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody Map <String, String> body) {

        String token = userService.createUser(body);

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body){

        String token = userService.processLogin(body);

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> body, @RequestHeader Map<String, String> header){

        String token = userService.updateUser(body, header.get("token"));

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout(){

        return null;
    }
}
