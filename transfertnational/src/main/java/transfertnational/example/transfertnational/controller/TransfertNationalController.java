package transfertnational.example.transfertnational.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transfertnational.example.transfertnational.beans.CompteBean;
import transfertnational.example.transfertnational.model.TransfertNational;
import transfertnational.example.transfertnational.proxies.MicroserviceCompteProxy;
import transfertnational.example.transfertnational.service.TransfertExcelExporter;
import transfertnational.example.transfertnational.service.TransfertNationalService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transfert")
public class TransfertNationalController {
    private final TransfertNationalService transfertNationalService;
    @Autowired
    MicroserviceCompteProxy microserviceCompteProxy;
    public TransfertNationalController(TransfertNationalService transfertNationalService) {
        this.transfertNationalService = transfertNationalService;
    }
    ////get all transfert
    @GetMapping("/alltransferts")
    public ResponseEntity<List<TransfertNational>> getAllTransferts () {
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


    @PostMapping("/TranSearch")
    public ResponseEntity<List<TransfertNational>> getTransCrit (    		
    		@RequestParam(required = false) Long idAgent,
    		@RequestParam(required = false) Long idClient,
    		@RequestParam(required = false) String pi,
    		@RequestParam(required = false) String numGsm,
    		@RequestParam(required = false) String codeTransfert,
    		@RequestParam(required = false) String status) {
        List<TransfertNational> transferts=transfertNationalService.GetTransfertNationalByIdAgentAndIdClientAndPiAndNumGsmAndCodeTransfertAndStatus(idAgent, idClient, pi, numGsm, codeTransfert, status);
        return new ResponseEntity<>(transferts, HttpStatus.OK);
    }

    
    @GetMapping("/transferts/export/excel")
    public ResponseEntity<List<TransfertNational>> exportToExcel(HttpServletResponse response,    		
    		@RequestParam(required = false) Long idAgent,
    		@RequestParam(required = false) Long idClient,
    		@RequestParam(required = false) String pi,
    		@RequestParam(required = false) String numGsm,
    		@RequestParam(required = false) String codeTransfert,
    		@RequestParam(required = false) String status) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transferts_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<TransfertNational> listTransferts = transfertNationalService.GetTransfertNationalByIdAgentAndIdClientAndPiAndNumGsmAndCodeTransfertAndStatus(idAgent, idClient, pi, numGsm, codeTransfert, status);
         
        TransfertExcelExporter excelExporter = new TransfertExcelExporter(listTransferts);
         
        excelExporter.export(response);
        return new ResponseEntity<>(listTransferts, HttpStatus.OK);
  
        
    }  
}
