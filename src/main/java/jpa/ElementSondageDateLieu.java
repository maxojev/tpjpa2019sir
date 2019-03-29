package jpa;


import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idElementSondage")
public class ElementSondageDateLieu extends ElementSondage {


    @ManyToOne
    private Lieu lieuChoisie_bis;

    @ManyToOne
    private Sdate dateChoisie_bis;


    public ElementSondageDateLieu() {
 //       super();

    }

    public ElementSondageDateLieu(Lieu lieuChoisie_bis, Sdate dateChoisie_bis, Personne personne, Reunion reunion) {

        super(personne,reunion);
        this.lieuChoisie_bis = lieuChoisie_bis;
        this.dateChoisie_bis = dateChoisie_bis;
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
