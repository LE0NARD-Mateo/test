package test.example.test.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.example.test.view.PrioriteView;
import test.example.test.view.TacheView;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Priorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PrioriteView.class)
    Integer id;

    @JsonView(PrioriteView.class)
    String status;

    // One Priority can belong to multiple tasks
    @OneToMany(mappedBy = "priorites")
    @JsonIgnore
    @JsonView(TacheView.class)
    List<Tache> taches = new ArrayList<>();


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

}
