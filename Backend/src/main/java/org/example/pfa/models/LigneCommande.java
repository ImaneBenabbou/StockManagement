package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCommande;
    @OneToOne
    @JoinColumn(referencedColumnName = "idProduit")
    private Produit produit;
    private Double quantité;

    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "idCommande")
    private Commande commande;
}
