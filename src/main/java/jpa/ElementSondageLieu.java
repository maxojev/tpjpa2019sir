package jpa;


import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idElementSondage")
public class ElementSondageLieu extends ElementSondage{


    @ManyToOne
    private Lieu lieuChoisie;


    public ElementSondageLieu(Lieu lieuChoisie, Personne personne, Reunion reunion) {
        super(personne,reunion);
        this.lieuChoisie = lieuChoisie;
    }

    public ElementSondageLieu() {
 //       super();
    }
}
