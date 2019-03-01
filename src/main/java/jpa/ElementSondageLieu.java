package jpa;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ESL")
public class ElementSondageLieu extends ElementSondage{

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Lieu lieuChoisie;


    public ElementSondageLieu(Lieu lieuChoisie, Personne personne, Reunion reunion) {
        this.lieuChoisie = lieuChoisie;
        this.participant = personne;
        this.mareunion = reunion;
    }

    public ElementSondageLieu() {
 //       super();
    }
}
