package com.example.client.service;


import com.example.client.exception.ClientNotFoundException;
import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }
    public Client addClient(Client client){         
    	client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
    	return clientRepository.save(client);
    }
    
    public Client findClientBynumGSM(String numGSM){
        return clientRepository.findBynumGSM(numGSM);
    }
    public Client findClientByCin(String cin){
        return clientRepository.findBycin(cin);
    }
    public Client updateClient(Long id,Client client)throws ClientNotFoundException {
    	Client clientFromDB = clientRepository.findByClientId(id).orElse(null);
        if (clientFromDB == null)
            throw new ClientNotFoundException("aucun client avec cette id");
        client.setClientId(clientFromDB.getClientId());
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        return clientRepository.save(client);
    }
    
    
   
}
