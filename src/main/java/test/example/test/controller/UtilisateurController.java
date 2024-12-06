package test.example.test.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import test.example.test.model.Utilisateur;
import test.example.test.dao.UtilisateurDao;
import test.example.test.security.AppUserDetails;
import test.example.test.security.IsAdmin;
import test.example.test.security.IsUser;
import test.example.test.view.UtilisateurView;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    // Get all priorities
    @IsUser
    @GetMapping("/utilisateur")
    public List<Utilisateur> getAll() {
        return utilisateurDao.findAll();
    }
    // Get a single priority by ID
    @IsUser
    @GetMapping("/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> get(@PathVariable Integer id) {
        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Utilisateur> optionalCompetence = utilisateurDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalCompetence.get(),HttpStatus.OK);
    }

    // Create a new priority
    @IsAdmin
    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur utilisateur) {

        //on force l'id à null au cas où le client en aurait fourni un
        utilisateur.setId(null);
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateur.setAdministrateur(false);
        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    // Update an existing priority*
    @IsAdmin
    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> update(@RequestBody Utilisateur utilisateur, @PathVariable Integer id) {

        //on force le changement de l'id de l'competence à enregitrer à l'id passé en paramètre
        utilisateur.setId(id);

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Utilisateur> optionalCompetence = utilisateurDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    // Delete a priority
    @IsAdmin
    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id) {

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Utilisateur> optionalCompetence = utilisateurDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.deleteById(id);

        return new ResponseEntity<>(optionalCompetence.get(), HttpStatus.OK);

    }

    @IsUser
    @GetMapping("/profil")
    public ResponseEntity<Utilisateur> Profil(@AuthenticationPrincipal AppUserDetails userDetails) {

        return ResponseEntity.ok(userDetails.getUtilisateur());
    }
}