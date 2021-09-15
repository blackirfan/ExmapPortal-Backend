package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles)  {
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("User is already there !!");
            try {
                throw new Exception("User is already present");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            // user create
            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }
//getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User up_user,User userinfo) {
//        User user = new User();
        userinfo.setUsername(up_user.getUsername());
        userinfo.setEmail(up_user.getEmail());
        userinfo.setUserRoles(up_user.getUserRoles());
        userinfo.setPassword(up_user.getPassword());
        userinfo.setFirstName(up_user.getFirstName());
        userinfo.setLastName(up_user.getLastName());
        userinfo.setProfile(up_user.getProfile());
        userinfo.setPhone(up_user.getPhone());
        User updateUser = userRepository.save(userinfo);
//       List list = userRepository.findAll().stream().map(v -> {
//            if(v.getIduserinfo)== userId){
//               v.setFirstName(up_user.getFirstName());
//               v.setLastName(up_user.getFirstName());
//            }
//            return v;
//        }).collect(Collectors.toList());


        return updateUser;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> data = userRepository.findById(userId);
        if (data.isPresent()) {
            User user = data.get();
            return user;
        }

        return null;
    }


}
