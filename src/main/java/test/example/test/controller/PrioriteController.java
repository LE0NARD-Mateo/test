package test.example.test.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.example.test.model.Priorite;
import test.example.test.dao.PrioriteDao;
import test.example.test.view.PrioriteView;
import test.example.test.view.TacheView;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PrioriteController {

    @Autowired
    private PrioriteDao prioriteDao;

    // Get all priorities
    @GetMapping("/priorite")
    public List<Priorite> getAll() {
        return prioriteDao.findAll();
    }
    // Get a single priority by ID
    @GetMapping("/priorite/{id}")
    @JsonView(PrioriteView.class)
    public ResponseEntity<Priorite> get(@PathVariable Integer id) {
        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Priorite> optionalCompetence = prioriteDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalCompetence.get(),HttpStatus.OK);
    }

    // Create a new priority
    @PostMapping("/priorite")
    public ResponseEntity<Priorite> create(@RequestBody Priorite priorite) {

        //on force l'id à null au cas où le client en aurait fourni un
        priorite.setId(null);
        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.CREATED);
    }

    // Update an existing priority
    @PutMapping("/priorite/{id}")
    public ResponseEntity<Priorite> update(@RequestBody Priorite priorite, @PathVariable Integer id) {

        //on force le changement de l'id de l'competence à enregitrer à l'id passé en paramètre
        priorite.setId(id);

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Priorite> optionalCompetence = prioriteDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.OK);
    }

    // Delete a priority
    @DeleteMapping("/competence/{id}")
    public ResponseEntity<Priorite> delete(@PathVariable Integer id) {

        //On vérifie que l'competence existe bien dans la base de donnée
        Optional<Priorite> optionalCompetence = prioriteDao.findById(id);

        //si l'competence n'existe pas
        if(optionalCompetence.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.deleteById(id);

        return new ResponseEntity<>(optionalCompetence.get(), HttpStatus.OK);

    }
}