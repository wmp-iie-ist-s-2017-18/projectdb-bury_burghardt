package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "choroby")
public class Choroba {
    @Id
    @GeneratedValue
    private int id_choroby;
    @Column(name ="nazwa")
    private String nazwa;
    @Column(name = "typ")
    private String typ;

    public int getId_choroby() {
        return id_choroby;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTyp() {
        return typ;
    }

    public void setId_choroby(int id_choroby) {
        this.id_choroby = id_choroby;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
