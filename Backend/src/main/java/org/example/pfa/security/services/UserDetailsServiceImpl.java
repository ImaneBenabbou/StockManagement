package org.example.pfa.security.services;


import jakarta.transaction.Transactional;
import org.example.pfa.Repository.UtilisateurRepo;
import org.example.pfa.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImpl.build(user);
    }
}
