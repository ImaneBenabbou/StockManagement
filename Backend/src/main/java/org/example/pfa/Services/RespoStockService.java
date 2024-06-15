package org.example.pfa.Services;

import org.example.pfa.Repository.EntrepotRepo;
import org.example.pfa.Repository.SeuilProduitRepo;
import org.example.pfa.models.Entrepot;
import org.example.pfa.models.Seuil_produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespoStockService {

    @Autowired
    private EntrepotRepo entrepotRepo;

    @Autowired
    private SeuilProduitRepo seuilProduitRepo;

    public List<Seuil_produit> getStockStatus(){
        return seuilProduitRepo.findAll();
    }

    public Entrepot updateProductQuantity(Long idEntrepot, long newQuantite){
        Optional<Entrepot> entrepotOpt=entrepotRepo.findById(idEntrepot);
        Entrepot entrepot=entrepotOpt.get();
        entrepot.setQuantité_stockée(newQuantite);
        return entrepotRepo.save(entrepot);
    }
}
