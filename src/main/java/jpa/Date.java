package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Date {

    @Id
    @GeneratedValue
    private Long idDate;


    private java.sql.Date date;
    private Boolean pause;

    @ManyToOne
    private ElementSondage elementSondage;

    @ManyToMany
    @JoinColumn
    private Collection<Reunion> mesReunions;

    @OneToOne(mappedBy = "dateValide")
    private Reunion reunion;


    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Boolean getPause() {
        return pause;
    }

    public void setPause(Boolean pause) {
        this.pause = pause;
    }

    public ElementSondage getElementSondage() {
        return elementSondage;
    }

    public void setElementSondage(ElementSondage elementSondage) {
        this.elementSondage = elementSondage;
    }

    public Collection<Reunion> getMesReunions() {
        return mesReunions;
    }

    public void setMesReunions(Collection<Reunion> mesReunions) {
        this.mesReunions = mesReunions;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }
}
