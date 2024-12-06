package test.example.test.security;

import test.example.test.dao.UtilisateurDao;
import test.example.test.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByNom(nom);

        if (optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("Nom introuvable");
        }

        return new AppUserDetails(optionalUtilisateur.get());
    }
}
