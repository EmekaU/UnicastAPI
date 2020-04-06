package com.group.service;

import com.group.dao.Subscriptions;
import com.group.dao.SubscriptionsKey;
import com.group.model.User;
import com.group.repository.SubscriptionRepo;
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
    private SubscriptionRepo subscriptionRepo;

    private static Logger log = LogManager.getLogger(UserService.class.getName());

    @Autowired
    public UserService(UserRepo userRepo, SubscriptionRepo subscriptionRepo){

        this.userRepo = userRepo;
        this.subscriptionRepo = subscriptionRepo;
    }

    private String getNewToken(User user){

        return JwtUtils.encodeUser(user);
    }

    private boolean userExists(String username, String password){

        return userRepo.existsByUsernameAndPassword(username, password);
    }

    //	Add User to User table
    public String createUser(Map<String,String> body){

        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        // TODO: hash password

        if(this.userExists(username, password)){
            return null;
        }

        UserDao userDao = new UserDao(username, email, password);
        System.out.println(userDao.toString());
        userRepo.save(userDao);

        User user = new User(username, password);

        return getNewToken(user);
    }

    public String processLogin(Map<String, String> body){

        String username = body.get("username");
        String password = body.get("password");

        if(this.userExists(username, password)){

            User user = new User(username, password);
            return getNewToken(user);
        }

        return null;
    }
//
//    public String updateUser(Map<String, ? > body, String token){
//
//        String username = body.get("username").toString();
//        String password = body.get("password").toString();
//        // update photo
//        User user = new User(username, password);
//
//        User decodedUser = JwtUtils.decodeUser(token);
//
//        if(user.equals(decodedUser)){
//
//            UserDao userDao = new UserDao();
//
//            userDao.setPassword(user.getPassword());
//            if(body.containsKey("photo")){
//                userDao.setPhoto(body.get("photo").toString());
//            }
//
//            userRepo.save(userDao);
//            return getNewToken(user);
//        }
//
//        return null;
//    }

    public boolean toggleSubscription(String token, Map<String, String> body){

        User user = JwtUtils.decodeUser(token);
        Subscriptions subscriptions = new Subscriptions();

        SubscriptionsKey subscriptionsKey = new SubscriptionsKey();

        subscriptionsKey.setSubscriberId(body.get("subscriberid"));
        subscriptionsKey.setSubscribeToId(body.get("subscribetoid"));

        if(this.subscriptionRepo.existsById(subscriptionsKey)){

            this.subscriptionRepo.deleteById(subscriptionsKey);
            return true;
        }
        else{

            subscriptions.setId(subscriptionsKey);
            this.subscriptionRepo.save(subscriptions);

            return true;
        }
    }

    public boolean deleteUser(String token){

        User user = JwtUtils.decodeUser(token);
        return userRepo.deleteUserDaoByUsername(user.getUsername());
    }

    public UserDao getUserByUsername(String username, String token){
        JwtUtils.decodeUser(token);
        return userRepo.getUserDaoByUsername(username);
    }

    public List<UserDao> getUsersByUsernameWith(String word) {

        return userRepo.getUserDaosByUsernameContains(word);
    }

}
