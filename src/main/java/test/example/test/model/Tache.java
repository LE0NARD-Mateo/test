package test.example.test.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.example.test.view.PrioriteView;
import test.example.test.view.TacheView;
import test.example.test.view.UtilisateurView;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TacheView.class)
    Integer id;

    @JsonView(TacheView.class)
    String description;

    @JsonView(TacheView.class)
    String titre;

    @JsonView(TacheView.class)
    Boolean valide;

    // Many tasks can belong to one priority
    @ManyToOne
    @JoinColumn(name = "priorite_id")
    @JsonView(PrioriteView.class)
    Priorite priorites;

    // Many tasks can be created by one user
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonView(UtilisateurView.class)
    Utilisateur createurs;

    // Many users can be assigned to a task, and a user can have multiple tasks
    @ManyToMany
    @JoinTable(
            name = "utilisateur_tache",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    @JsonView(UtilisateurView.class)
    List<Utilisateur> utilisateurs = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Priorite getPriorites() {
        return priorites;
    }

    public void setPriorites(Priorite priorites) {
        this.priorites = priorites;
    }

    public Utilisateur getCreateurs() {
        return createurs;
    }

    public void setCreateurs(Utilisateur createurs) {
        this.createurs = createurs;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
