package com.SecurityWithJwt.Services;

import com.SecurityWithJwt.Entity.UserPrincipal;
import com.SecurityWithJwt.Entity.Users;
import com.SecurityWithJwt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    // Implement logic to fetch user details from your database using username


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUsername(username);
        if(user == null){
            System.out.println("User not found because user is null");
            throw  new UsernameNotFoundException("User not found");
        }
        if(user.getUsername().equals(username)){
            System.out.println("User Found: " + user);
        }

        return new UserPrincipal(user);
    }
}
