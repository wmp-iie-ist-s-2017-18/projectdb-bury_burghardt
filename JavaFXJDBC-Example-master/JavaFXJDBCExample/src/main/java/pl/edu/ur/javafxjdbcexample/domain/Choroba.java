package pl.edu.ur.javafxjdbcexample.domain;

import java.util.List;
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
    @ManyToMany(mappedBy = "choroby")
    private List<Porada> porady;

    public int getId_choroby() {
        return id_choroby;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTyp() {
        return typ;
    }
    
    public List<Porada> getPorady() {
        return porady;
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
    
    public void setPorady(List<Porada> porady) {
        this.porady = porady;
    }
}
