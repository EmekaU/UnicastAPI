package com.group.service;

import com.group.repository.UserRepo;
import com.group.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean createUser(String username, String email, String password){
        if(userRepo.existsById(username)){
            return false;
        }

        User user = new User(username, email, password);
        user.setToken(null);
        user.setPhoto(null);
        user.setPodcasts(null);

        userRepo.save(user);
        return true;
    }

    public boolean updateUser(User user){
        userRepo.save(user);
        return true;
    }

    public User getUser(String username){
        return userRepo.getUserByUsername(username);
    }
}
