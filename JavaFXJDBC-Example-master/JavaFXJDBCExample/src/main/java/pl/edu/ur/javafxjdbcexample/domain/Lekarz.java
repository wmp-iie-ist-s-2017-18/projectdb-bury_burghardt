package pl.edu.ur.javafxjdbcexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "lekarze")
public class Lekarz {
    @Id
    @GeneratedValue
    private int id_lekarza;
    @Column(name = "imię")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "pesel",nullable = false, length = 11)
    private int pesel;
    @Column(name = "specjalizacja")
    private String specjalizacja;
    @Column(name = "zarobki")
    private double zarobki;

    public int getId_lekarza() {
        return id_lekarza;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getPesel() {
        return pesel;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public double getZarobki() {
        return zarobki;
    }

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public void setZarobki(double zarobki) {
        this.zarobki = zarobki;
    }
}
