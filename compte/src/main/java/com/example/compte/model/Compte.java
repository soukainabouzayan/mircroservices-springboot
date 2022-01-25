package com.example.compte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String numCompte;
    private float solde;
    @Column(nullable = false)
    private String nomClient;
    @Column(nullable = false)
    private String typeCompte;
    private LocalDateTime createAt;

}
