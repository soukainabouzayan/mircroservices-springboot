package com.example.beneficiaire.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BeneficiaireDuplicatedException extends Exception{
    private  Long bid;
}
