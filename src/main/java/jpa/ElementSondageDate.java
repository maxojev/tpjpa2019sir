package jpa;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idElementSondage")
public class ElementSondageDate extends ElementSondage {


    @ManyToOne
    private Sdate dateChoisie;


    public ElementSondageDate() {
    }

    public ElementSondageDate(Sdate dateChoisie,Personne personne,Reunion reunion) {
        super(personne,reunion);
        this.dateChoisie = dateChoisie;
    }

    public Sdate getDateChoisie() {
        return dateChoisie;
    }

    public void setDateChoisie(Sdate dateChoisie) {
        this.dateChoisie = dateChoisie;
    }
}
