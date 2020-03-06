package com.group.service;

import com.group.repository.UserRepo;
import com.group.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //	Add User to User table
    public boolean createUser(String username, String email, String password){
        if(userRepo.existsById(username)){
            return false;
            //return null
        }
        UserDAO userDao = new UserDAO(username, email, password);
        userRepo.save(userDao);
        // create and return token
        return true;
    }

    public boolean updateUser(UserDAO userDao){
        userRepo.save(userDao);
        return true;
    }

}
