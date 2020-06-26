package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.User;
import com.isil.eco.Services.UserService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = {"*"})
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User getAllClients(@RequestBody User user){
               User retrivedUser =  userService.getByEmail(user.getEmail())
                       .orElseThrow(()-> new ModelNotFoundException("Client", user.getEmail()));
               if(retrivedUser.getPassword().equals(user.getPassword())){
                   return retrivedUser;
               }
               throw new ModelNotFoundException("Client", user.getEmail());
    }
}
