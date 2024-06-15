package org.example.pfa.Repository;


import org.example.pfa.models.Entrepot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepotRepo extends JpaRepository<Entrepot, Long> {
    Entrepot findByNom(String nom);
}
