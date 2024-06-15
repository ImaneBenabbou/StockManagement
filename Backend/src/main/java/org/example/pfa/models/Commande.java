package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCommande;
	private Date date;
	private String statut;
	private String adresse;

	@ManyToOne
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Utilisateur client;

	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LigneCommande> ligne_commande;
}
