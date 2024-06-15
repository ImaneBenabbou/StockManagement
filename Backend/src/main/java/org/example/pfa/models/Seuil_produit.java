package org.example.pfa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Seuil_produit {

    @Id
    private Long idSeuilProduit;
    @OneToOne
    @JoinColumn( referencedColumnName = "idProduit")
    private Produit produit;
    @OneToOne
    @JoinColumn(referencedColumnName = "idEntrepot")
    private Entrepot entrepot;
    private long Quantité_max;
    private long Qunatité_min;


}