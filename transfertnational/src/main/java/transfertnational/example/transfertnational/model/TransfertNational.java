package transfertnational.example.transfertnational.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(name= "transfert_national", schema = "targetSchemaName")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfertNational {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private Long idClient;
    private Long idAgent;
    private String pi;
    private String numGsm;
    private Long idBeneficiaire;
    private Long idCompte;
    private String status;
    private String codeTransfert;
    private float montant;
    private int nombreJours;

}
