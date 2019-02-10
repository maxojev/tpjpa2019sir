package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Date {

    @Id
    @GeneratedValue
    private Long idDate;


    private java.sql.Date date;
    private Boolean pause;

    @ManyToOne
    private ElementSondage elementSondage;
}
