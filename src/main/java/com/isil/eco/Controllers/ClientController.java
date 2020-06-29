package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.User;
import com.isil.eco.Services.UserService;
import com.isil.eco.helpers.ModelValidator;
import com.isil.eco.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = {"*"})
public class ClientController {
     UserService userService;
    @Autowired
    PasswordEncoder encoder;
    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    List<User> all() {
        return userService.getAllClients();
    }

    @PostMapping("/add")
    User newClient(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        user.setRole("Client");
        return userService.saveClient(user);
    }

    @GetMapping("/find/{id}")
    User one(@PathVariable Long id) {
        return userService.getOneClient(id);
    }

    @PutMapping("/update/{id}")
    User replaceClient(@RequestBody User user, @PathVariable Long id) {
        user.setPassword(encoder.encode(user.getPassword()));

        return userService.updateClient(user,id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteClient(@PathVariable Long id) {
        userService.deleteClient(id);
    }

}
