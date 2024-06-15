package org.example.pfa.Services;


import org.example.pfa.Repository.EntrepotRepo;
import org.example.pfa.Repository.ProduitRepo;
import org.example.pfa.Repository.SeuilProduitRepo;
import org.example.pfa.Repository.UtilisateurRepo;
import org.example.pfa.models.Entrepot;
import org.example.pfa.models.Produit;
import org.example.pfa.models.Seuil_produit;
import org.example.pfa.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UtilisateurRepo userRepo;

    @Autowired
    private EntrepotRepo entrepotRepo;

    @Autowired
    private ProduitRepo produitRepo;

    @Autowired
    private SeuilProduitRepo seuilProduitRepo;

    public Utilisateur createResponsableVente(Utilisateur responsablevente) {
        responsablevente.setRole("RESPONSABLE_VENTE");
        return userRepo.save(responsablevente);
    }

    public Utilisateur createResponsableStock(Utilisateur responsablestock) {
        responsablestock.setRole("RESPONSABLE_STOCK");
        return userRepo.save(responsablestock);
    }

    public Utilisateur updateAccount(Utilisateur utilisateur) {
        return userRepo.save(utilisateur);
    }

    public List<Utilisateur> getAllClients() {
        return userRepo.findByRole("CLIENT");
    }

    public List<Utilisateur> getResponsableVente(){
        return userRepo.findByRole("RESPONSABLE_VENTE");
    }

    public List<Utilisateur> getResponsableStock(){
        return userRepo.findByRole("RESPONSABLE_STOCK");
    }

    public List<Produit> getAllProduits (){
        return produitRepo.findAll();
    }

    public Produit updateProduit(Produit produit){
        return produitRepo.save(produit);
    }
    public Produit addProduit(Produit produit, Long quantite, Long quantiteMax, Long quantiteMin, String entrepotNom) {
        Entrepot entrepot = entrepotRepo.findByNom(entrepotNom);  // Correction ici
        if (entrepot == null) {
            entrepot = new Entrepot();
            entrepot.setNom(entrepotNom);
            entrepot.setProduit(produit.getNom());
            entrepot.setQuantité_stockée(quantite);
            entrepot = entrepotRepo.save(entrepot);  // Correction ici
        }

        produit = produitRepo.save(produit);

        Seuil_produit seuilProduit = new Seuil_produit();
        seuilProduit.setProduit(produit);
        seuilProduit.setEntrepot(entrepot);
        seuilProduit.setQuantité_max(quantiteMax);
        seuilProduit.setQunatité_min(quantiteMin);
        seuilProduitRepo.save(seuilProduit);

        return produit;
    }


    public void deleteProduit (Long idProduit) {
        produitRepo.deleteById(idProduit);
    }

    /*public List<Seuil_produit> consulterNiveauxStock() {
        return seuilProduitRepo.findAll();
    }
    */

    public List<Seuil_produit> getProductsByWarehouseId(Long warehouseId) {
        return seuilProduitRepo.findByEntrepot_IdEntrepot(warehouseId);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

}
