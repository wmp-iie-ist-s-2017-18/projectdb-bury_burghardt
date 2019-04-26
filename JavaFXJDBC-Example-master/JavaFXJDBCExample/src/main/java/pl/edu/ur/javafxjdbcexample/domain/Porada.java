package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(name = "id_lekarza")
    private int id_lekarza;
    @Column(name = "id_pacjenta")
    private int id_pacjenta;

    public int getId_porady() {
        return id_porady;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public int getId_lekarza() {
        return id_lekarza;
    }

    public int getId_pacjenta() {
        return id_pacjenta;
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

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }
}
