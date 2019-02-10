package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Personne {

    @Id
    @GeneratedValue
    private long idPersonne;

    private String nom;
    private String prenom;
    private String mail;

    @OneToOne
    @JoinColumn
    FicheBouffe ficheBouffe;

    @ManyToOne
    private ElementSondage participeElementSondage;

    @OneToMany(mappedBy = "createur")
    private Collection<Reunion> mesReunionsCreees;
}
