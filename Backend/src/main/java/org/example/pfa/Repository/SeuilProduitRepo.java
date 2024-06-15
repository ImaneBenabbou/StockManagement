package org.example.pfa.Repository;


import org.example.pfa.models.Produit;
import org.example.pfa.models.Seuil_produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeuilProduitRepo extends JpaRepository<Seuil_produit, Long> {
    List<Seuil_produit> findByEntrepot_IdEntrepot(Long idEntrepot);
    List<Seuil_produit>getSeuilProduitByProduit(Produit produit);
}
