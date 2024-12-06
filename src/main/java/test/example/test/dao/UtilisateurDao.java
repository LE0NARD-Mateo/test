package test.example.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.example.test.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByNom(String nom);

    @Query("SELECT u FROM Utilisateur u WHERE u.nom = :nom")
    Optional<Utilisateur> trouverParNom(@Param("nom") String nom);
}
