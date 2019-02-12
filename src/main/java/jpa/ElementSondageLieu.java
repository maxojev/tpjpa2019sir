package jpa;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ESL")
public class ElementSondageLieu extends ElementSondage{

    @ManyToOne
    private Lieu lieuChoisie;


    public ElementSondageLieu(Lieu lieuChoisie, Personne personne, Reunion reunion) {
        this.lieuChoisie = lieuChoisie;
        this.participant = personne;
        this.mareunion = reunion;
    }

    public ElementSondageLieu() {
    }
}
