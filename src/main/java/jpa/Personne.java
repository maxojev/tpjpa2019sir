package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Personne {

    @Id
    @GeneratedValue
    private long idPersonne;

    private String nom;
    private String prenom;
    private String mail;

    @OneToOne
    @JoinColumn
    FicheBouffe ficheBouffe;

    @ManyToOne
    private ElementSondage participeElementSondage;

    @OneToMany(mappedBy = "createur")
    private Collection<Reunion> mesReunionsCreees;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public FicheBouffe getFicheBouffe() {
        return ficheBouffe;
    }

    public void setFicheBouffe(FicheBouffe ficheBouffe) {
        this.ficheBouffe = ficheBouffe;
    }

    public ElementSondage getParticipeElementSondage() {
        return participeElementSondage;
    }

    public void setParticipeElementSondage(ElementSondage participeElementSondage) {
        this.participeElementSondage = participeElementSondage;
    }

    public Collection<Reunion> getMesReunionsCreees() {
        return mesReunionsCreees;
    }

    public void setMesReunionsCreees(Collection<Reunion> mesReunionsCreees) {
        this.mesReunionsCreees = mesReunionsCreees;
    }
}
