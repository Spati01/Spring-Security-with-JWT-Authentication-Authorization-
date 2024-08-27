package com.SecurityWithJwt.Controller;

import com.SecurityWithJwt.Entity.Users;
import com.SecurityWithJwt.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestBody Users user) {

    return userService.verify(user);
    }



    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        System.out.println(user);
     return userService.registerUser(user);
    }

    @GetMapping("/get_users/{id}")
    public ResponseEntity<Users> getUsers(@PathVariable int id) {
        Optional<Users> user = userService.getUser(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

}
