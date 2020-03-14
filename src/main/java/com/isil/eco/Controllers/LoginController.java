package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.Client;
import com.isil.eco.Services.ClientService;
import com.isil.eco.helpers.ModelValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class LoginController {
    private final ClientService clientService;

    public LoginController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public Client getAllClients(@RequestBody Client client){
               Client retrivedClient=  clientService.getClientByEmail(client.getEmail());
               if(retrivedClient==null) throw new ModelNotFoundException("Client",client.getEmail());
               if(retrivedClient.getPassword().equals(client.getPassword())){
                   return retrivedClient;
               }
               throw new ModelNotFoundException("Client",client.getEmail());
    }
    @PostMapping("/register")
    public Client Register(@RequestBody @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        client.setRole("Client");
        return clientService.saveClient(client);
    }
}
