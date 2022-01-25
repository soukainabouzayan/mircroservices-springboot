package com.example.client.proxies;

import com.example.client.beans.BeneficiaireBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "beneficiaire-service",url = "localhost:8080/beneficiaire")
public interface MicroserviceBeneficiaireProxy {
    @GetMapping("/all")
    ResponseEntity<List<BeneficiaireBean>> getAllBeneficiaires ();
}
