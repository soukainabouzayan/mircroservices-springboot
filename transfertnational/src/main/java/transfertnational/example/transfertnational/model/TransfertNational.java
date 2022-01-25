package transfertnational.example.transfertnational.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Long idBeneficiaire;
    private Long idCompte;
    private String status;
    private String codeTransfert;
    private float montant;
    private int nombreJours;

}
