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
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody Map <String, String> body){

        boolean userisCreated = userService.createUser(body.get("username"), body.get("email"), body.get("password"));

        HttpStatus status = userisCreated? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(status);
    }
}
