package jpa;

import javax.persistence.*;

@Entity
public class Reunion {

    @Id
    @GeneratedValue
    private Long idReunion;

    private String intitule;
    private String resume;

    @ManyToOne
    private Personne createur;

    @ManyToOne
    private ElementSondage monElementSondage;

    @OneToOne
    private Date dateValide;

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

    public ElementSondage getMonElementSondage() {
        return monElementSondage;
    }

    public void setMonElementSondage(ElementSondage monElementSondage) {
        this.monElementSondage = monElementSondage;
    }

    public Date getDateValide() {
        return dateValide;
    }

    public void setDateValide(Date dateValide) {
        this.dateValide = dateValide;
    }
}
