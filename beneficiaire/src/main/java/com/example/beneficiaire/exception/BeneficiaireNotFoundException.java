package com.example.beneficiaire.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeneficiaireNotFoundException extends RuntimeException {
    public BeneficiaireNotFoundException(String message) {
        super(message);
    }
}
