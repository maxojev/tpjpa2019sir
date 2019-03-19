package jpa;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ESDL")
public class ElementSondageDateLieu extends ElementSondage {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Lieu lieuChoisie_bis;

    @ManyToOne
    private Sdate dateChoisie_bis;
    //tpjpa2019sir

    public ElementSondageDateLieu() {
 //       super();

    }

    public ElementSondageDateLieu(Lieu lieuChoisie_bis, Sdate dateChoisie_bis, Personne personne, Reunion reunion) {
        this.lieuChoisie_bis = lieuChoisie_bis;
        this.dateChoisie_bis = dateChoisie_bis;
        this.participant = personne;
        this.mareunion = reunion;
    }

    public Lieu getLieuChoisie_bis() {
        return lieuChoisie_bis;
    }

    public void setLieuChoisie_bis(Lieu lieuChoisie_bis) {
        this.lieuChoisie_bis = lieuChoisie_bis;
    }

    public Sdate getDateChoisie_bis() {
        return dateChoisie_bis;
    }

    public void setDateChoisie_bis(Sdate dateChoisie_bis) {
        this.dateChoisie_bis = dateChoisie_bis;
    }
}
