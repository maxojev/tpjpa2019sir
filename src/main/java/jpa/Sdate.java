package jpa;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Sdate {

    @Id
    @GeneratedValue
    private Long idDate;


    private java.sql.Date date;
    private Boolean pause;

    @OneToOne(mappedBy = "dateValidee")
    private Reunion reunion;

    @OneToMany(mappedBy = "dateChoisie")
    private Collection<ElementSondage> mesElementsSondages;


    public Sdate() {
    }

    public Sdate(java.sql.Date date, Boolean pause) {
        this.date = date;
        this.pause = pause;
    }

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

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public Collection<ElementSondage> getMesElementsSondages() {
        return mesElementsSondages;
    }

    public void setMesElementsSondages(Collection<ElementSondage> mesElementsSondages) {
        this.mesElementsSondages = mesElementsSondages;
    }
}
