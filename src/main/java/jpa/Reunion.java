package jpa;

import javax.persistence.*;

@Entity
public class Reunion {

    @Id
    @GeneratedValue
    private Long idReunion;

    private String intitule;
    private String resume;

    @ManyToOne
    private Personne createur;

    @ManyToOne
    private ElementSondage monElementSondage;


}
