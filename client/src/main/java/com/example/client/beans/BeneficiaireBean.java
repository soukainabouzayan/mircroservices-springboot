package com.example.client.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaireBean {
    private Long id;
    private String nom;
    private String prenom;
    private String numGSM;
}
