package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Date {

    @Id
    @GeneratedValue
    private Long idDate;


    private java.sql.Date date;
    private Boolean pause;

    @ManyToOne
    private ElementSondage elementSondage;

    @ManyToMany
    @JoinColumn
    private Collection<Reunion> mesReunions;
}
