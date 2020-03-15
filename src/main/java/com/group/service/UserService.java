package com.group.service;

import com.group.repository.UserRepo;
import com.group.dao.UserDao;
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

        UserDao userDao = new UserDao(username, email, password);
        System.out.println(userDao.toString());
        userRepo.save(userDao);
        // TODO: create and return token
        return true;
    }

    public boolean userExists(String username){
        return userRepo.existsByUsername(username);
    }
    public boolean updateUser(UserDao userDao){
        userRepo.save(userDao);
        return true;
    }

}
