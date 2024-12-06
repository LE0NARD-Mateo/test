package test.example.test.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.example.test.model.Tache;
import test.example.test.model.Tache;
import test.example.test.dao.TacheDao;
import test.example.test.view.TacheView;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TacheController {

    @Autowired
    private TacheDao tacheDao;

    // Get all tasks
    @GetMapping("/tache")
    public List<Tache> getAllTaches() {
        return tacheDao.findAll();
    }

    // Get a single task by ID
    @GetMapping("/tache/{id}")
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> getTacheById(@PathVariable Integer id) {
        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Tache> optionalCompetence = tacheDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalCompetence.get(),HttpStatus.OK);
    }

    // Create a new task
    @PostMapping("/tache")
    public ResponseEntity<Tache> create(@RequestBody Tache tache) {

        //on force l'id à null au cas où le client en aurait fourni un
        tache.setId(null);
        tacheDao.save(tache);

        return new ResponseEntity<>(tache, HttpStatus.CREATED);
    }

    // Update an existing task
    @PutMapping("/tache/{id}")
    public ResponseEntity<Tache> update(@RequestBody Tache tache, @PathVariable Integer id) {

        //on force le changement de l'id de l'competence à enregitrer à l'id passé en paramètre
        tache.setId(id);

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Tache> optionalCompetence = tacheDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tacheDao.save(tache);

        return new ResponseEntity<>(tache, HttpStatus.OK);
    }

    // Delete a task
    @DeleteMapping("/tache/{id}")
    public ResponseEntity<Tache> delete(@PathVariable Integer id) {

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Tache> optionalCompetence = tacheDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tacheDao.deleteById(id);

        return new ResponseEntity<>(optionalCompetence.get(), HttpStatus.OK);

    }
}
