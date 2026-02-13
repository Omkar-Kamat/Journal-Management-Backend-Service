package com.learn.journal.service;

import com.learn.journal.Entity.User;
import com.learn.journal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userRepo.findByUserName(userName);
        if(user!=null){
            UserDetails userDetail = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetail;
        }
        throw new UsernameNotFoundException("User not found with username: " + userName);
    }
}
