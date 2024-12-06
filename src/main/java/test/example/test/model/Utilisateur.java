package test.example.test.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.example.test.view.TacheView;
import test.example.test.view.UtilisateurView;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    Integer id;

    @JsonView(UtilisateurView.class)
    String nom;

    @JsonView(UtilisateurView.class)
    String password;

    boolean administrateur;

    // One user can create many tasks
    @OneToMany(mappedBy = "createurs")
    @JsonIgnore
    @JsonView(TacheView.class)
    List<Tache> createdTaches = new ArrayList<>();

    // Many users can be assigned to many tasks
    @ManyToMany(mappedBy = "utilisateurs")
    @JsonIgnore
    @JsonView(TacheView.class)
    List<Tache> assignedTaches = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<Tache> getAssignedTaches() {
        return assignedTaches;
    }

    public void setAssignedTaches(List<Tache> assignedTaches) {
        this.assignedTaches = assignedTaches;
    }

    public List<Tache> getCreatedTaches() {
        return createdTaches;
    }

    public void setCreatedTaches(List<Tache> createdTaches) {
        this.createdTaches = createdTaches;
    }
}
