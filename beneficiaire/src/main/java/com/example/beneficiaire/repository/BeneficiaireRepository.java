package com.example.beneficiaire.repository;

import com.example.beneficiaire.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire,Long> {
    void deleteBeneficiaireById(Long id);
    Beneficiaire findBeneficiaireById(Long id);

}
