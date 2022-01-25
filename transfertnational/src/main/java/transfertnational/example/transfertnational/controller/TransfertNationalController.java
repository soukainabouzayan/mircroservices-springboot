package transfertnational.example.transfertnational.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfertnational.example.transfertnational.beans.CompteBean;
import transfertnational.example.transfertnational.model.TransfertNational;
import transfertnational.example.transfertnational.proxies.MicroserviceCompteProxy;
import transfertnational.example.transfertnational.service.TransfertNationalService;

import java.util.List;

@RestController
@RequestMapping("transfert")
public class TransfertNationalController {
    private final TransfertNationalService transfertNationalService;
    @Autowired
    MicroserviceCompteProxy microserviceCompteProxy;
    public TransfertNationalController(TransfertNationalService transfertNationalService) {
        this.transfertNationalService = transfertNationalService;
    }
    ////get all transfert
    @GetMapping("/all")
    public ResponseEntity<List<TransfertNational>> getAllClients () {
        List<TransfertNational> transferts=transfertNationalService.getAllTransfers();
        return new ResponseEntity<>(transferts, HttpStatus.OK);
    }
    //get transfert by id Client
    @GetMapping("/all/client/{idClient}")
    public ResponseEntity<List<TransfertNational>> getTransfertByIdClient(@PathVariable("idClient") Long idClient) {
        List<TransfertNational> transferts=transfertNationalService.getTransfertByIdClient(idClient);
        return new ResponseEntity<>(transferts, HttpStatus.OK);
    }
    ///get transfert by id Beneficiaire
    @GetMapping("/all/beneficiaire/{idBeneficiaire}")
    public ResponseEntity<List<TransfertNational>> getTransfertByIdBeneficiaire(@PathVariable("idBeneficiaire") Long idBeneficiaire) {
        List<TransfertNational> transferts=transfertNationalService.getTransfertByIdBeneficiaire(idBeneficiaire);
        return new ResponseEntity<>(transferts, HttpStatus.OK);
    }
    ///get Transfert By Id client and Id Beneficiaire
    @GetMapping("/all/beneficiaire/{idClient}/{idBeneficiaire}")
    public ResponseEntity<List<TransfertNational>> getTransfertByIdClientAndIdBeneficiaire(@PathVariable("idClient") Long idClient,@PathVariable("idBeneficiaire") Long idBeneficiaire) {
        List<TransfertNational> transferts=transfertNationalService.getTransfertNationalByIdClientAndIdBeneficiaire(idClient,idBeneficiaire);
        return new ResponseEntity<>(transferts, HttpStatus.OK);
    }
    ///adding transfert
    @PostMapping("/add")
    public ResponseEntity<TransfertNational> addtransfert(@RequestBody TransfertNational transfert) throws Exception{
        TransfertNational newTransfert = transfertNationalService.addTransfert(transfert);
        return new ResponseEntity<>(newTransfert,HttpStatus.CREATED);
    }
    ////update nombre jours
    @PatchMapping("update/{id}/{nombreJours}")
    public ResponseEntity<TransfertNational> updateNombreJours(@PathVariable("id") Long id, @PathVariable("nombreJours") int nombreJours){
        TransfertNational transfertNational=transfertNationalService.updateTransfertNombreJours(id,nombreJours);
        return new ResponseEntity<>(transfertNational, HttpStatus.OK);
    }
    ///update Status
    @PatchMapping("update/status/{id}/{status}")
    public ResponseEntity<TransfertNational> updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
        TransfertNational transfertNational=transfertNationalService.updateTransfertStatus(id,status);
        return new ResponseEntity<>(transfertNational, HttpStatus.OK);
    }

    //get all comptes
    @GetMapping("/compte/all")
    public ResponseEntity<List<CompteBean>> getAllComptes () {
        return  microserviceCompteProxy.getAllCompte();
    }

    /// get compte by compte number

    @GetMapping("/compte/find/{numCompte}")
    ResponseEntity<CompteBean> findCompteBynumCompte(@PathVariable("numCompte") String numCompte){
        return microserviceCompteProxy.findCompteBynumCompte(numCompte);
    }
    // debiter le compte
    @PutMapping("/compte/update/{id}/{solde}")
    public ResponseEntity<CompteBean> updateCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde){
        return microserviceCompteProxy.updateCompte(id,solde);
    }





}
