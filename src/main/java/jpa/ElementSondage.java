package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class ElementSondage {

    @Id
    @GeneratedValue
    private Long idElementSondage;

    @OneToMany(mappedBy = "elementSondage")
    private Collection<Date> datesPossibles;

    @OneToMany(mappedBy = "participeElementSondage")
    private Collection<Personne> mesParticipants;

    @OneToMany(mappedBy = "monElementSondage")
    private Collection<Reunion> mesReunions;


    public Collection<Date> getDatesPossibles() {
        return datesPossibles;
    }

    public void setDatesPossibles(Collection<Date> datesPossibles) {
        this.datesPossibles = datesPossibles;
    }

    public Collection<Personne> getMesParticipants() {
        return mesParticipants;
    }

    public void setMesParticipants(Collection<Personne> mesParticipants) {
        this.mesParticipants = mesParticipants;
    }

    public Collection<Reunion> getMesReunions() {
        return mesReunions;
    }

    public void setMesReunions(Collection<Reunion> mesReunions) {
        this.mesReunions = mesReunions;
    }
}
