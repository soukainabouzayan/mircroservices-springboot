package com.example.client.controller;


import com.example.client.beans.BeneficiaireBean;
import com.example.client.model.Client;
import com.example.client.proxies.MicroserviceBeneficiaireProxy;
import com.example.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    MicroserviceBeneficiaireProxy microserviceBeneficiaireProxy;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients () {
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client) throws Exception{
        Client client1 = clientService.addClient(client);
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }
    @GetMapping("/find/{gsm}")
    public ResponseEntity<Client> findClientByGSM(@PathVariable("gsm") String gsm){
        Client client=clientService.findClientBynumGSM(gsm);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }
    @GetMapping("/find/{cin}")
    public ResponseEntity<Client> findClientByCin(@PathVariable("cin") String cin){
        Client client=clientService.findClientByCin(cin);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }
    @GetMapping("/beneficiaire/all")
    public ResponseEntity<List<BeneficiaireBean>> getAllBeneficiaires () {
        return  microserviceBeneficiaireProxy.getAllBeneficiaires();
    }

}
