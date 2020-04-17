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

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final SubscriptionRepo subscriptionRepo;
    private EntityManager em;

    private static final Logger log = LogManager.getLogger(UserService.class.getName());

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

    public String updateUser(Map<String, String> body, String token){

        System.out.println(body);

        String username = body.get("username");
        String password = body.get("password");
        // update photo
        User decodedUser = JwtUtils.decodeUser(token);

        // check if loggedIn user's key data is valid. ie, matches token.
        if(decodedUser.getUsername().equals(username)){

            UserDao userDao = this.userRepo.getUserDaoByUsername(username);
            userDao.setPhoto(body.get("photo"));
            userDao.setBio(body.get("bio"));
            userDao.setEmail(body.get("email"));

            userRepo.save(userDao);
            return getNewToken(new User(username, password));
        }

        return null;
    }

    public UserDao toggleSubscription(String token, Map<String, String> body){

        User user = JwtUtils.decodeUser(token);
        Subscriptions subscriptions = new Subscriptions();

        SubscriptionsKey subscriptionsKey = new SubscriptionsKey();

        subscriptionsKey.setSubscriberId(body.get("subscriberid"));
        subscriptionsKey.setSubscribeToId(body.get("subscribetoid"));

        if(this.subscriptionRepo.existsById(subscriptionsKey)){

            this.subscriptionRepo.deleteById(subscriptionsKey);
        }
        else{

            subscriptions.setId(subscriptionsKey);
            this.subscriptionRepo.save(subscriptions);

        }
        return this.getUserByUsername(body.get("subscribetoid"), token);
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
