package com.isil.eco.Services;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.User;
import com.isil.eco.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository clientRepository;

    public UserService(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<User> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<User> getByEmail(String email){
        return clientRepository.findByEmail(email);
    }
    public Optional<User> getByUsername(String username){
        return clientRepository.findByUsername(username);
    }

    public Boolean existsByEmail(String email){
        return clientRepository.existsByEmail(email);
    }

    public User getOneClient(long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Client" ,id));
    }

    public User saveClient(User user){
        return clientRepository.save(user);
    }

    public User updateClient(User user, long id){
        return clientRepository.findById(id)
                .map(c -> {
                    c.setRole(user.getRole());
                    c.setEmail(user.getEmail());
                    c.setFname(user.getFname());
                    c.setLname(user.getLname());
                    c.setPassword(user.getPassword());
                    c.setTel(user.getTel());
                    return clientRepository.save(user);
                })
                .orElse(null);
    }

    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }
}
