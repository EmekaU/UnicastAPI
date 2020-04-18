package com.group.repository;

import com.group.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepo extends JpaRepository<UserDao, String> {

    public boolean existsByUsernameAndPassword(String username, String Password);

    public UserDao getUserDaoByUsername(String username);

    public boolean deleteUserDaoByUsername(String username);

    public List<UserDao> getUserDaosByUsernameContains(String match);
}
