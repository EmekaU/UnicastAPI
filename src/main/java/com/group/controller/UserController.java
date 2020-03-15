package com.group.controller;
import com.group.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Principal getAuthenticatedUser(Principal user){
        // check if user is in DB.
        return user;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody Map <String, String> body){

        boolean userisCreated = userService.createUser(body);

        HttpStatus status = userisCreated? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(status);
    }git 
}
