package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Entrepot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntrepot;
	private String nom;
	private String produit;
	private long quantité_stockée;

}