package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Reunion {

    @Id
    @GeneratedValue
    private Long idReunion;

    private String intitule;
    private String resume;

    @ManyToOne
    private Personne createur;

    @OneToMany(mappedBy = "mareunion")
    private Collection<ElementSondage> mesElementSondage;

    @ManyToMany
    @JoinColumn
    private Collection<Sdate> datesProposees;

    @OneToOne
    private Sdate dateValidee;


    public Reunion(String intitule) {
    }

    public Reunion(String intitule, String resume, Personne createur, Collection<Sdate> datesProposees) {
        this.intitule = intitule;
        this.resume = resume;
        this.createur = createur;
        this.datesProposees = datesProposees;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Personne getCreateur() {
        return createur;
    }

    public void setCreateur(Personne createur) {
        this.createur = createur;
    }

    public Collection<ElementSondage> getMesElementSondage() {
        return mesElementSondage;
    }

    public void setMesElementSondage(Collection<ElementSondage> mesElementSondage) {
        this.mesElementSondage = mesElementSondage;
    }

    public Collection<Sdate> getDatesProposees() {
        return datesProposees;
    }

    public void setDatesProposees(Collection<Sdate> datesProposees) {
        this.datesProposees = datesProposees;
    }

    public Sdate getDateValidee() {
        return dateValidee;
    }

    public void setDateValidee(Sdate dateValidee) {
        this.dateValidee = dateValidee;
    }
}
