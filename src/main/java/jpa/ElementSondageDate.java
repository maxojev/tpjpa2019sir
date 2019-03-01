package jpa;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ESD")
public class ElementSondageDate extends ElementSondage {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Sdate dateChoisie;


    public ElementSondageDate() {
 //       super();
    }

    public ElementSondageDate(Sdate dateChoisie,Personne personne,Reunion reunion) {
        this.dateChoisie = dateChoisie;
        this.participant = personne;
        this.mareunion = reunion;
    }

    public Sdate getDateChoisie() {
        return dateChoisie;
    }

    public void setDateChoisie(Sdate dateChoisie) {
        this.dateChoisie = dateChoisie;
    }
}
