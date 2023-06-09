package com.kabelo.restaurant.dao;

import com.kabelo.restaurant.POJO.User;
import com.kabelo.restaurant.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao  extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUsers();

}
