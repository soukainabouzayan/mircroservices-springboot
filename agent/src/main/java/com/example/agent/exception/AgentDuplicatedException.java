package com.example.agent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@ResponseStatus(HttpStatus.CONFLICT)

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AgentDuplicatedException extends Exception {
    public AgentDuplicatedException(String string) {
    	
		super(string);
         }


}
