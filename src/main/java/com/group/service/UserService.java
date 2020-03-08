package com.group.service;

import com.group.repository.UserRepo;
import com.group.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //	Add User to User table
    public boolean createUser(Map<String,String> body){

        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        // TODO: hash password
        if(userRepo.existsByUsername(username)){
            return false;
        }

        User userDao = new User(username, email, password);
        userRepo.save(userDao);
        // TODO: create and return token
        return true;
    }

    public boolean updateUser(User userDao){
        userRepo.save(userDao);
        return true;
    }

}
