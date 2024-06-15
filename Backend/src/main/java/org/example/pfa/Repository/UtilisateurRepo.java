package org.example.pfa.Repository;

import org.example.pfa.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findByEmail(String Email);
    public List<Utilisateur> findByRole(String Role);

    Boolean existsByEmail(String Email);
}

