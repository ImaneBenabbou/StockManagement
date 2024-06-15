package org.example.pfa.Repository;


import org.example.pfa.models.Commande;
import org.example.pfa.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Long> {
    List<Commande> findByClient(Utilisateur client);
    List<Commande> findByDate(Date date);
}
