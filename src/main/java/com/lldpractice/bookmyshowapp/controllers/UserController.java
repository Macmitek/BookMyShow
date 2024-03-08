package com.lldpractice.bookmyshowapp.controllers;

import com.lldpractice.bookmyshowapp.dto.*;
import com.lldpractice.bookmyshowapp.models.User;
import com.lldpractice.bookmyshowapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDto signUp(SignUpRequestDto request) {
        System.out.println("reqq: "+request.getEmail() +"|| "+request.getPassword());
        SignUpResponseDto response = new SignUpResponseDto();
       try{
           User user = userService.signUp(request.getEmail(), request.getPassword());
           response.setStatus(ResponseStatus.SUCCESS);
           response.setUserId(user.getId());
       }catch (Exception e ){
           response.setStatus(ResponseStatus.FAILURE);
       }
        return response;
    }
    public LoginResponseDto login(LoginRequestDto request) {

        return null;
    }
}
