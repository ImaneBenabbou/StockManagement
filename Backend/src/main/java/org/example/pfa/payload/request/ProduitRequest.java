package org.example.pfa.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitRequest {
    private String nom;
    private String categorie;
    private String fournisseur;
    private float prixUnitaire;
    private Long quantite;
    private Long quantiteMax;
    private Long quantiteMin;
    private String entrepotNom;



}
