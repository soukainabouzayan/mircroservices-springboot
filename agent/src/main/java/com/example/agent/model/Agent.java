package com.example.agent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(name= "agent", schema = "targetSchemaName")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
	private String role;
	public boolean isPresent() {
		return false;
	}

}
