package com.group.controller;
import com.group.dao.UserDao;
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

    private final UserService userService;
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> body) {

        String token = userService.createUser(body);

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {

        String token = userService.processLogin(body);

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> body, @RequestHeader Map<String, String> header) {

        String token = userService.updateUser(body, header.get("token"));

        HttpStatus status = token != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", token);

        return new ResponseEntity<>(responseHeaders, status);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String, String> header) {

        boolean success = userService.deleteUser(header.get("token"));

        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(success, status);
    }

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam String username) {

        UserDao user = userService.getUserByUsername(username);

        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {

        return null;
    }

}
