package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.Client;
import com.isil.eco.Models.Product;
import com.isil.eco.Services.ClientService;
import com.isil.eco.Services.ProductService;
import com.isil.eco.helpers.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/client")
public class ClientController {
     ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    List<Client> all() {
        return clientService.getAllClients();
    }

    @PostMapping("/")
    Client newClient(@RequestBody @Valid Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    Client one(@PathVariable Long id) {
        return clientService.getOneClient(id);
    }

    @PutMapping("/{id}")
    Client replaceClient(@RequestBody Client client, @PathVariable Long id) {
        return clientService.updateClient(client,id);
    }

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

}
