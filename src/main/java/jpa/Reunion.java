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

    @ManyToMany
    @JoinColumn
    private Collection<Lieu> lieuProposes;

    @OneToOne
    private Sdate dateValidee;

    @OneToOne
    private Lieu lieuValide;


    public Reunion() {
    }

    public Reunion(String intitule, String resume, Personne createur, Collection<Sdate> datesProposees, Collection<Lieu> lieuProposes) {
        this.intitule = intitule;
        this.resume = resume;
        this.createur = createur;
        this.datesProposees = datesProposees;
        this.lieuProposes = lieuProposes;
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

    public Collection<Lieu> getLieuProposes() {
        return lieuProposes;
    }

    public void setLieuProposes(Collection<Lieu> lieuProposes) {
        this.lieuProposes = lieuProposes;
    }

    public Sdate getDateValidee() {
        return dateValidee;
    }

    public void setDateValidee(Sdate dateValidee) {
        this.dateValidee = dateValidee;
    }

    public Lieu getLieuValide() {
        return lieuValide;
    }

    public void setLieuValide(Lieu lieuValide) {
        this.lieuValide = lieuValide;
    }
}
