package com.lldpractice.bookmyshowapp.services;

import com.lldpractice.bookmyshowapp.models.User;
import com.lldpractice.bookmyshowapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User signUp(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        System.out.println("email id in service :"+email + " pass :"+password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }

}
