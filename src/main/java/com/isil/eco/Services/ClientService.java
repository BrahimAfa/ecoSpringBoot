package com.isil.eco.Services;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.Client;
import com.isil.eco.Models.Product;
import com.isil.eco.Repositories.ClientRepository;
import com.isil.eco.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    public Client getOneClient(long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Client" ,id));
    }
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
    public Client updateClient(Client client,long id){
        return clientRepository.findById(id)
                .map(c -> {
                    c.setRole(client.getRole());
                    c.setEmail(client.getEmail());
                    c.setFname(client.getFname());
                    c.setLname(client.getLname());
                    c.setPassword(client.getPassword());
                    c.setTel(client.getTel());
                    return clientRepository.save(client);
                })
                .orElseGet(()->null);
    }
    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }
}
