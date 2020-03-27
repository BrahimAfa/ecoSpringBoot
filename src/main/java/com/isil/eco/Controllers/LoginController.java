package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.Client;
import com.isil.eco.Services.ClientService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = {"http://localhost:3000"})
public class LoginController {
    private final ClientService clientService;

    public LoginController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    public Client getAllClients(@RequestBody Client client){
               Client retrivedClient=  clientService.getClientByEmail(client.getEmail());
               if(retrivedClient==null) throw new ModelNotFoundException("Client",client.getEmail());
               if(retrivedClient.getPassword().equals(client.getPassword())){
                   return retrivedClient;
               }
               throw new ModelNotFoundException("Client",client.getEmail());
    }
}
