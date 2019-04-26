package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "leki")
public class Lek {
    @Id
    @GeneratedValue
    private int id_leku;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "dawka")
    private double dawka;

    public int getId_leku() {
        return id_leku;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getDawka() {
        return dawka;
    }

    public void setId_leku(int id_leku) {
        this.id_leku = id_leku;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setDawka(double dawka) {
        this.dawka = dawka;
    }
}
