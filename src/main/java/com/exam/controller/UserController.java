package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user){

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        return this.userService.createUser(user,roles);

    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){

      return this.userService.getUser(username);


    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);

    }
    // update user by id
    @PutMapping("/update/{userId}")
    public User updateUser(@RequestBody User up_user, @PathVariable("userId") Long userId) {
        User userinfo = userService.findUserById(userId);
//        this.userService.updateUser(up_user);
//        return up_user;

        User updatedUser = userService.updateUser(up_user,userinfo);

        if (ObjectUtils.isEmpty(updatedUser)) {
            return null;
        }
        return  updatedUser;

    }
}
