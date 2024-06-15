package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduit;
	private String Nom;
	private String Categorie;
	private String Fournisseur;
	private float Prix_Unitaire;

	
}
