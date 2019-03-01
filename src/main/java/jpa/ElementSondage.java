package jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class ElementSondage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue
    protected Long idElementSondage;
/*
    @ManyToOne
    private Sdate dateChoisie;  */

    @ManyToOne
    protected Personne participant;

    @ManyToOne
    protected Reunion mareunion;

    public ElementSondage() {}
/*
    public ElementSondage(Sdate dateChoisie, Personne participant, Reunion mareunion) {
//        this.dateChoisie = dateChoisie;
        this.participant = participant;
        this.mareunion = mareunion;
    }

    public Sdate getDateChoisie() {
        return dateChoisie;
    }

    public void setDateChoisie(Sdate dateChoisie) {
        this.dateChoisie = dateChoisie;
    }
*/
}
