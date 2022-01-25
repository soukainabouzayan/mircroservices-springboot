package transfertnational.example.transfertnational.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import transfertnational.example.transfertnational.model.TransfertNational;
import transfertnational.example.transfertnational.repository.TransfertNationalRepository;

import java.util.List;

@Service
public class TransfertNationalService {
    private final TransfertNationalRepository transfertNationalRepository;

    public TransfertNationalService(TransfertNationalRepository transfertNationalRepository) {
        this.transfertNationalRepository = transfertNationalRepository;
    }
    /// get all transferts
    public List<TransfertNational> getAllTransfers(){
        return transfertNationalRepository.findAll();
    }
    /////get transfert by id
    public TransfertNational findTransfertNationalByID(Long id){
        return transfertNationalRepository.findTransfertNationalById(id);
    }
    ////get transferts by idClient
    public List<TransfertNational> getTransfertByIdClient(Long idClient){
        return transfertNationalRepository.findTransfertNationalByIdClient(idClient);
    }
    ///get transfers by id Beneficiaire
    public List<TransfertNational> getTransfertByIdBeneficiaire(Long idBeneficiaire){
        return transfertNationalRepository.findTransfertNationalByIdBeneficiaire(idBeneficiaire);
    }
    ///get transfert by id beneficiaire and id client
    public List<TransfertNational> getTransfertNationalByIdClientAndIdBeneficiaire(Long idClient,Long idBeneficiaire){
        return transfertNationalRepository.findTransfertNationalByIdClientAndIdBeneficiaire(idClient,idBeneficiaire);
    }
    ////add transfert
    public TransfertNational addTransfert(TransfertNational transfertNational){
        return transfertNationalRepository.save(transfertNational);
    }
    ///update nombre jours
    public TransfertNational updateTransfertNombreJours(Long id,int nombreJours){
        TransfertNational transfertNational= transfertNationalRepository.findTransfertNationalById(id);
        transfertNational.setNombreJours(nombreJours);
        return transfertNationalRepository.save(transfertNational);
    }
    ///update status
    public TransfertNational updateTransfertStatus(Long id,String status){
        TransfertNational transfertNational= transfertNationalRepository.findTransfertNationalById(id);
        transfertNational.setStatus(status);
        return transfertNationalRepository.save(transfertNational);
    }


}

