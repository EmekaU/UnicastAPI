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

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody Map<String, String> body){

        // check if user exists in DB
        // create token
        // send token

        String username = body.get("username");
        HttpStatus status = HttpStatus.NOT_FOUND;

        if(userService.userExists(username)){

            status = HttpStatus.OK;
            return "User Token";
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody Map <String, String> body) {

        // Validate User Info
        // Store User in DB if username doesn't exist already
        // Generate access Token for this User
        // Store username : access token in noSql DB
        // Create Token by encoding user
        // Send Token to user

        // If error: send Bad Request status.
        boolean userisCreated = userService.createUser(body);

        HttpStatus status = userisCreated ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(status);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public void logout(HttpRequest request){

        // change signing key
        HttpHeaders header = request.getHeaders();

        header.get("refreshToken");
        header.get("userToken");

        // Retrieve access Token
        //
        // remove token from DB.

    }
}
