package com.group.service;

import com.group.model.User;
import com.group.repository.UserRepo;
import com.group.dao.UserDao;
import com.group.utilities.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepo userRepo;
    private static Logger log = LogManager.getLogger(UserService.class.getName());

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    private String getNewToken(User user){

        return JwtUtils.encodeUser(user);
    }

    private boolean userExists(String username){

        return userRepo.existsByUsername(username);
    }

    //	Add User to User table
    public String createUser(Map<String,String> body){

        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        // TODO: hash password

        if(this.userExists(username)){
            return null;
        }

        UserDao userDao = new UserDao(username, email, password);
        System.out.println(userDao.toString());
        userRepo.save(userDao);

        User user = new User(username, password);
        String token = JwtUtils.encodeUser(user);
        System.out.println(token);

        return token;
    }

    public String processLogin(Map<String, String> body){

        String username = body.get("username");
        String password = body.get("password");

        if(this.userExists(username)){

            User user = new User(username, password);
            return getNewToken(user);
        }

        return null;
    }

    public String updateUser(Map<String, ? > body, String token){

        // Needs discussion

        // Might have to set preferences in token for category.
        String username = body.get("username").toString();
        String password = body.get("password").toString();

        User user = new User(username, password);

        if(! JwtUtils.tokenIsExpired(token) && token != null){

            User decodedUser = JwtUtils.decodeUser(token);

            if(user.equals(decodedUser)){

                UserDao userDao = new UserDao();

                userDao.setPassword(user.getPassword());
                if(body.containsKey("photo")){
                    userDao.setPhoto((Byte[]) body.get("photo"));
                }

                userRepo.save(userDao);
                return getNewToken(user);
            }

            System.out.println("It seems that the token, " + token + " has been compromised");
        }

        return null;
    }

    public boolean deleteUser(String token){

        if(! JwtUtils.tokenIsExpired(token) && token != null) {

            User user = JwtUtils.decodeUser(token);
            return userRepo.deleteUserDaoByUsername(user.getUsername());
        }

        return false;
    }

    public UserDao getUserByUsername(String token){

        if(! JwtUtils.tokenIsExpired(token) && token != null) {

            User user = JwtUtils.decodeUser(token);
            return userRepo.getUserDaoByUsername(user.getUsername());
        }

        return null;
    }

    public List<UserDao> getUsersByUsernameWith(String token, String word) {

        if(! JwtUtils.tokenIsExpired(token) && token != null){

            return userRepo.getUserDaosByUsernameContains(word);
        }

        return null;
    }

}
