package com.example.agent.proxies;

import com.example.agent.beans.CompteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "compte-service",url = "localhost:8082/compte")
public interface MicroserviceCompteProxy{
    @GetMapping("/all")
    ResponseEntity<List<CompteBean>> getAllCompte ();
    @GetMapping("/find/{numCompte}")
    ResponseEntity<CompteBean> findCompteBynumCompte(@PathVariable("numCompte") String numCompte);
    @GetMapping("/find/client/{nomClient}")
    ResponseEntity<CompteBean> findCompteByNomClient(@PathVariable("nomClient") String nomClient);
    //add compte from agent
    @PostMapping("/add")
    ResponseEntity<CompteBean> addCompte(@RequestBody CompteBean compte);

    @PatchMapping("update/{id}/{solde}")
    public ResponseEntity<CompteBean> updateCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde);

}
