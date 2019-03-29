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

    public Personne() { }

    public Personne(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    @OneToMany(mappedBy = "participant", cascade = CascadeType.PERSIST)
    private Collection<ElementSondage> mesparticipations;

    @OneToMany(mappedBy = "createur",cascade = CascadeType.PERSIST)
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

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Collection<ElementSondage> getMesparticipations() {
        return mesparticipations;
    }

    public void setMesparticipations(Collection<ElementSondage> mesparticipations) {
        this.mesparticipations = mesparticipations;
    }

    public Collection<Reunion> getMesReunionsCreees() {
        return mesReunionsCreees;
    }

    public void setMesReunionsCreees(Collection<Reunion> mesReunionsCreees) {
        this.mesReunionsCreees = mesReunionsCreees;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
