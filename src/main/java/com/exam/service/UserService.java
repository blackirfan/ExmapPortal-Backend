package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {

    // creating user
    public User createUser(User user, Set<UserRole> userRoles);

    // get user by username
    public User getUser(String username);

    // delete user by id
    public void deleteUser(Long userId);

    public User updateUser(User up_user, User userinfo);

    User findUserById(Long userId);
}
