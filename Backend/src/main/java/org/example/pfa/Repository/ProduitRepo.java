package org.example.pfa.Repository;


import org.example.pfa.models.Produit;
import org.example.pfa.models.Seuil_produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Long> {
}
