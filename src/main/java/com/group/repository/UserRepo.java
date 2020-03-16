package com.group.repository;

import com.group.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserDao, String> {

    public boolean existsByUsername(String username);

    public boolean deleteUserDaoByUsername(String username);

    // search for users with username containing x, y, z

}
