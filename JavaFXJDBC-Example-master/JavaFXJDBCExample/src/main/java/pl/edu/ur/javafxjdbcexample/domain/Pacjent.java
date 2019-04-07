package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "pacjenci")
public class Pacjent {
    @Id
    @GeneratedValue
    private int id_pacjenta;
    @Column(name = "imię")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "pesel",nullable = false, length = 11)
    private long pesel;

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public long getPesel() {
        return pesel;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }
}

