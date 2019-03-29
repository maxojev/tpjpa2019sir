package jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class ElementSondage implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idElementSondage;

    @ManyToOne
    protected Personne participant;

    @ManyToOne
    protected Reunion mareunion;


    public ElementSondage(Personne participant,Reunion mareunion) {
        this.participant= participant;
        this.mareunion= mareunion;
    }

    public ElementSondage(){}
/*
    public ElementSondage(Sdate dateChoisie, Personne participant, ReunionService mareunion) {
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

    public Long getIdElementSondage() {
        return idElementSondage;
    }

    public void setIdElementSondage(Long idElementSondage) {
        this.idElementSondage = idElementSondage;
    }

    public Personne getParticipant() {
        return participant;
    }

    public void setParticipant(Personne participant) {
        this.participant = participant;
    }

    public Reunion getMareunion() {
        return mareunion;
    }

    public void setMareunion(Reunion mareunion) {
        this.mareunion = mareunion;
    }
}
