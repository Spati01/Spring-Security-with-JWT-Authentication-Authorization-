package com.SecurityWithJwt.Services;

import com.SecurityWithJwt.Entity.Users;
import com.SecurityWithJwt.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private AuthenticationManager authManager;

   @Autowired
   private PasswordEncoder encoder;

   @Autowired
   private JWTServices jwtService;

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));  // Password encryption before saving in database.
        return userRepository.save(user);
    }
    public Optional<Users> getUser(int id) {
        return userRepository.findById(id);
    }
    public List<Users> getAllUsers(){
    return userRepository.findAll();
}

    public String verify(Users user) {
    Authentication authentication =
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    if (authentication.isAuthenticated()) {
        return jwtService.generateToken(user.getUsername());
    } else {
        return "Invalid Credentials";
    }

    }
}
