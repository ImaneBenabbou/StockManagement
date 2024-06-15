package org.example.pfa.payload.request;



import lombok.Getter;
import lombok.Setter;
import org.example.pfa.models.LigneCommande;

import java.util.List;

@Getter
@Setter
public class CommandeRequest {

    private String adresse;
    private List<LigneCommande> ligneCommande;
    private String statut;
}
