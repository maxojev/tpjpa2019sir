package jpa;

import javax.persistence.*;

@Entity
public class ElementSondage {

    @Id
    @GeneratedValue
    private Long idElementSondage;

    @ManyToOne
    private Sdate dateChoisie;

    @ManyToOne
    private Personne participant;

    @ManyToOne
    private Reunion mareunion;

    public ElementSondage() {}

    public ElementSondage(Sdate dateChoisie, Personne participant, Reunion mareunion) {
        this.dateChoisie = dateChoisie;
        this.participant = participant;
        this.mareunion = mareunion;
    }

    public Sdate getDateChoisie() {
        return dateChoisie;
    }

    public void setDateChoisie(Sdate dateChoisie) {
        this.dateChoisie = dateChoisie;
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
