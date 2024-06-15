package org.example.pfa.Services;


import org.example.pfa.Repository.CommandeRepo;
import org.example.pfa.Repository.LigneCommandeRepo;
import org.example.pfa.Repository.ProduitRepo;
import org.example.pfa.Repository.UtilisateurRepo;
import org.example.pfa.models.Commande;
import org.example.pfa.models.Produit;
import org.example.pfa.models.Utilisateur;
import org.example.pfa.payload.request.CommandeRequest;
import org.example.pfa.payload.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ProduitRepo produitRepo;

    @Autowired
    private CommandeRepo commandeRepo;

    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private LigneCommandeRepo ligneCommandeRepo;

    public List<Produit> getAllProducts(){
        return produitRepo.findAll();
    }
    
    public Optional<Produit> getProduct(Long idProduit) {
        return produitRepo.findById(idProduit);
    }

    public Optional<Utilisateur> findById(Long userId) {
        return utilisateurRepo.findById(userId);
    }

    public Commande createOrder(CommandeRequest commandeRequest, Utilisateur client){
        Commande commande=new Commande();
        commande.setStatut("Pending");
        commande.setAdresse(commandeRequest.getAdresse());
        commande.setDate(new Date());
        commande.setClient(client);
        commande.setLigne_commande(commandeRequest.getLigneCommande());
        return commandeRepo.save(commande);
    }

    public Optional<Commande> getOrder(Long idCommande) {
        return commandeRepo.findById(idCommande);
    }

    public Commande updateOrder(Long idCommande, CommandeRequest commandeRequest) {
        Optional<Commande> optionalCommande = commandeRepo.findById(idCommande);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            if ("Pending".equals(commande.getStatut())) {
                commande.setAdresse(commandeRequest.getAdresse());
                commande.setLigne_commande(commandeRequest.getLigneCommande());
                return commandeRepo.save(commande);
            }
        }
        return null;
    }

    public boolean cancelOrder(Long idCommande) {
        Optional<Commande> optionalCommande = commandeRepo.findById(idCommande);
        if (optionalCommande.isPresent()) {
            Commande commande =optionalCommande.get();
            if ("Pending".equals(commande.getStatut())) {
                commande.setStatut("Cancelled");
                commandeRepo.save(commande);
                return true;
            }
        }
        return false;
    }

    public Utilisateur updateProfile(Long userId, UserInfoResponse userInfoResponse) {
        Optional<Utilisateur> userOpt = utilisateurRepo.findById(userId);
        if (userOpt.isPresent()) {
            Utilisateur user = userOpt.get();
            user.setNom(userInfoResponse.getNom());
            user.setEmail(userInfoResponse.getEmail());
            user.setPhone(userInfoResponse.getPhone());
            return utilisateurRepo.save(user);
        }
        return null;

    }
}
