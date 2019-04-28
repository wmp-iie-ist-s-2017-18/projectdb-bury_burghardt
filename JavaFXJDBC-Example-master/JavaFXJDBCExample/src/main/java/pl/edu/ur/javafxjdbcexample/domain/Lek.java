package pl.edu.ur.javafxjdbcexample.domain;

import java.util.List;
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
    @ManyToMany(mappedBy = "leki")
    private List<Porada> porady;

    public int getId_leku() {
        return id_leku;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getDawka() {
        return dawka;
    }
    
    public List<Porada> getPorady() {
        return porady;
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
    
    public void setPorady(List<Porada> porady) {
        this.porady = porady;
    }
}
