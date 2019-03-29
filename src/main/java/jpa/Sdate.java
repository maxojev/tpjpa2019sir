package jpa;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Sdate {


    @Id
    @GeneratedValue
    private Long idDate;


    private java.sql.Date madate;
    private Boolean pause;

    @OneToOne(mappedBy = "dateValidee", cascade = CascadeType.PERSIST)
    private Reunion reunion;

    @OneToMany(mappedBy = "dateChoisie", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Collection<ElementSondageDate> mesElementsSondagesDate;

    @OneToMany(mappedBy = "dateChoisie_bis", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Collection<ElementSondageDateLieu> mesElementsSondagesDateLieu;


    public Sdate() {
    }

    public Sdate(java.sql.Date date, Boolean pause) {
        this.madate = date;
        this.pause = pause;
    }

    public Long getIdDate() {
        return idDate;
    }

    public Date getDate() {
        return madate;
    }

    public void setDate(Date date) {
        this.madate = date;
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

    public Collection<ElementSondageDate> getMesElementsSondagesDate() {
        return mesElementsSondagesDate;
    }

    public void setMesElementsSondagesDate(Collection<ElementSondageDate> mesElementsSondagesDate) {
        this.mesElementsSondagesDate = mesElementsSondagesDate;
    }

    public Collection<ElementSondageDateLieu> getMesElementsSondagesDateLieu() {
        return mesElementsSondagesDateLieu;
    }

    public void setMesElementsSondagesDateLieu(Collection<ElementSondageDateLieu> mesElementsSondagesDateLieu) {
        this.mesElementsSondagesDateLieu = mesElementsSondagesDateLieu;
    }
}
