package jpa;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Lieu {

    @Id
    @GeneratedValue
    private Long iDLieu;
    private String nomLieu;

    @OneToOne(mappedBy = "lieuValide")
    private Reunion reunion;

    @OneToMany(mappedBy = "lieuChoisie")
    private Collection<ElementSondageLieu> mesElementsSondagesLieu;

    @OneToMany(mappedBy = "lieuChoisie_bis")
    private Collection<ElementSondageDateLieu> mesElementsSondagesDateLieu;





    public Lieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public Lieu() {

    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public Collection<ElementSondageLieu> getMesElementsSondagesLieu() {
        return mesElementsSondagesLieu;
    }

    public void setMesElementsSondagesLieu(Collection<ElementSondageLieu> mesElementsSondagesLieu) {
        this.mesElementsSondagesLieu = mesElementsSondagesLieu;
    }

    public Collection<ElementSondageDateLieu> getMesElementsSondagesDateLieu() {
        return mesElementsSondagesDateLieu;
    }

    public void setMesElementsSondagesDateLieu(Collection<ElementSondageDateLieu> mesElementsSondagesDateLieu) {
        this.mesElementsSondagesDateLieu = mesElementsSondagesDateLieu;
    }
}