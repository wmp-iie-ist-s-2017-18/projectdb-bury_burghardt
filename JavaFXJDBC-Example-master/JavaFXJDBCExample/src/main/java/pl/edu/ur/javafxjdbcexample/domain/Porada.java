package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "porady_lekarskie")
public class Porada {
    @Id
    @GeneratedValue
    private int id_porady;
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "godzina")
    private LocalTime godzina;
    @ManyToOne
    @JoinColumn(name = "id_lekarza")
    private Lekarz lekarz;
    @ManyToOne
    @JoinColumn(name = "id_pacjenta")
    private Pacjent pacjent;
    @ManyToMany
    private List<Choroba> choroby;
    @ManyToMany
    private List<Lek> leki;

    public int getId_porady() {
        return id_porady;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }
    
    public List<Choroba> getChoroby() {
        return choroby;
    }
    
    public List<Lek> getLeki() {
        return leki;
    }

    public void setId_porady(int id_porady) {
        this.id_porady = id_porady;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setGodzina(LocalTime godzina) {
        this.godzina = godzina;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
    
    public void setChoroby(List<Choroba> choroby) {
        this.choroby = choroby;
    }
    
    public void setLeki(List<Lek> leki) {
        this.leki = leki;
    }
}
