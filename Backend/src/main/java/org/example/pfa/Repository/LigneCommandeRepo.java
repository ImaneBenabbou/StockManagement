package org.example.pfa.Repository;


import org.example.pfa.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepo extends JpaRepository<LigneCommande, Long> {
}
