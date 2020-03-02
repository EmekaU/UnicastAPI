package com.group.repository;

import com.group.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    User getUserByUsername(String username);
    void deleteUserByUsername(String username);
    //Each user should have a list of subscriptions.
    //Needed for Hub.


}
