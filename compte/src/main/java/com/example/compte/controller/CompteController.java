package com.example.compte.controller;

import com.example.compte.exception.NotFoundException;
import com.example.compte.model.Compte;
import com.example.compte.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("compte")
public class CompteController {
    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Compte>> getAllCompte () {
        List<Compte>comptes = compteService.findAllComptes();
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Compte> addCompte(@RequestBody Compte compte) throws Exception{
        Compte compte2 = compteService.addCompte(compte);
        return new ResponseEntity<>(compte2, HttpStatus.CREATED);
    }
    @GetMapping("/find/{numCompte}")
    public ResponseEntity<Compte> findCompteBynumCompte(@PathVariable("numCompte") String numCompte){
        Compte compte=compteService.getCompteByNumero(numCompte);
        return new ResponseEntity<>(compte,HttpStatus.OK);
    }
    @GetMapping("/find/client/{nomClient}")
    public ResponseEntity<Compte> findCompteByNomClient(@PathVariable("nomClient") String nomClient){
        Compte compte=compteService.getCompteByNumero(nomClient);
        return new ResponseEntity<>(compte,HttpStatus.OK);
    }
    @PutMapping("update/{id}/{solde}")
    public ResponseEntity<Compte> updateCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde){
       Compte compte= compteService.updateCompte(id,solde);
        return new ResponseEntity<>(compte,HttpStatus.OK);
    }



}
