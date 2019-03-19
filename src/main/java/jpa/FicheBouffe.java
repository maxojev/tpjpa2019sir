package jpa;

import javax.persistence.*;


@Entity
public class FicheBouffe {
    /*
        test
         */
    @Id
    @GeneratedValue
    private Long idFicheBouffe;
    private String preference;
    private String allergie;


    @OneToOne
    @JoinColumn
    private Personne personne;


    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }
}
