package org.example.pfa.Services;


import org.example.pfa.Repository.CommandeRepo;
import org.example.pfa.Repository.EntrepotRepo;
import org.example.pfa.Repository.SeuilProduitRepo;
import org.example.pfa.models.Commande;
import org.example.pfa.models.Seuil_produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class RespoVenteService {

    @Autowired
    private CommandeRepo commandeRepo;

    @Autowired
    private EntrepotRepo entrepotRepo;

    @Autowired
    private SeuilProduitRepo seuilProduitRepo;

    /*public Commande validerCommande(Long idCommande) {
        Optional <Commande> commandeOpt= commandeRepo.findById(idCommande);

        Commande commande=commandeOpt.get();
        commande.setStatut("Validée");
        return commandeRepo.save(commande);
    }
    */


    public List<Seuil_produit> consulterNiveauxStock() {
        return seuilProduitRepo.findAll();
    }

    /*
    public Commande cloturerCommande(Long idCommande) {
        Optional<Commande> commandeOpt = commandeRepo.findById(idCommande);
        Commande commande = commandeOpt.get();
        commande.setStatut("Cloturée");
        return commandeRepo.save(commande);
    }
    */

    public List<Commande> getCommandesByDate(Date date) {
        return commandeRepo.findByDate(date);
    }

    public Commande updateCommande(Long idCommande) {
        Commande commande = commandeRepo.findById(idCommande).get();
        commande.setStatut(commande.getStatut());
        return commande;
    }
}
