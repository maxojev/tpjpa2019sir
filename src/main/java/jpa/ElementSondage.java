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

    @OneToMany(mappedBy = "elementSondage")
    private Collection<Personne> mesParticipants;
}
