package pl.edu.ur.javafxjdbcexample;

import java.util.List;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.ur.javafxjdbcexample.database.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.edu.ur.javafxjdbcexample.domain.Pacjent;
import pl.edu.ur.javafxjdbcexample.domain.Choroba;
import pl.edu.ur.javafxjdbcexample.domain.Lek;
import pl.edu.ur.javafxjdbcexample.domain.Lekarz;
import pl.edu.ur.javafxjdbcexample.domain.Porada;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaChoroba;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaLek;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaLekarz;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaPacjent;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaPorada;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaWynik;

public class FXMLController implements Initializable {
      
    @FXML
    private TableView<ObservableList> tableView;
    
    DatabaseHelper dbHelper = new DatabaseHelper();
    
    @FXML
    private TextField tf_por_id;
    @FXML
    private TextField tf_por_data;
    @FXML
    private TextField tf_por_godz;
    @FXML
    private TextField tf_por_pac;
    @FXML
    private TextField tf_por_lek;
    @FXML
    private TextField tf_pac_id;
    @FXML
    private TextField tf_pac_im;
    @FXML
    private TextField tf_pac_naz;
    @FXML
    private TextField tf_pac_pes;
    @FXML
    private TextField tf_lekarz_id;
    @FXML
    private TextField tf_lekarz_im;
    @FXML
    private TextField tf_lekarz_naz;
    @FXML
    private TextField tf_lekarz_pes;
    @FXML
    private TextField tf_lekarz_spec;
    @FXML
    private TextField tf_lekarz_zar;
    @FXML
    private TextField tf_cho_id;
    @FXML
    private TextField tf_cho_naz;
    @FXML
    private TextField tf_cho_typ;
    @FXML
    private TextField tf_lek_id;
    @FXML
    private TextField tf_lek_naz;
    @FXML
    private TextField tf_lek_daw;
    @FXML
    private TextField tf_por2_id;
    @FXML
    private TextField tf_por_cho_id;
    @FXML
    private TextField tf_por_lek_id;
    @FXML
    private TextField tf_wysz_id;
    @FXML
    private TableView<PokaPorada> tbe_porady;
    @FXML
    private TableColumn kol_por_id;
    @FXML
    private TableColumn kol_por_data;
    @FXML
    private TableColumn kol_por_godz;
    @FXML
    private TableColumn kol_por_pac;
    @FXML
    private TableColumn kol_por_lekarz;
    @FXML
    private TableView<PokaPacjent> tbe_pacjenci;
    @FXML
    private TableColumn kol_pac_id;
    @FXML
    private TableColumn kol_pac_imie;
    @FXML
    private TableColumn kol_pac_naz;
    @FXML
    private TableColumn kol_pac_pesel;
    @FXML
    private TableView<PokaLekarz> tbe_lekarze;
    @FXML
    private TableColumn kol_lekarz_id;
    @FXML
    private TableColumn kol_lekarz_imie;
    @FXML
    private TableColumn kol_lekarz_naz;
    @FXML
    private TableColumn kol_lekarz_pesel;
    @FXML
    private TableColumn kol_lekarz_spec;
    @FXML
    private TableColumn kol_lekarz_zar;
    @FXML
    private TableView<PokaChoroba> tbe_choroby;
    @FXML
    private TableColumn kol_cho_id;
    @FXML
    private TableColumn kol_cho_nazwa;
    @FXML
    private TableColumn kol_cho_typ;
    @FXML
    private TableView<PokaLek> tbe_lekarstwa;
    @FXML
    private TableColumn kol_lek_id;
    @FXML
    private TableColumn kol_lek_nazwa;
    @FXML
    private TableColumn kol_lek_dawka;
    @FXML
    private TableView<PokaChoroba> tbe_choroby2;
    @FXML
    private TableColumn kol_cho_id2;
    @FXML
    private TableColumn kol_cho_nazwa2;
    @FXML
    private TableColumn kol_cho_typ2;
    @FXML
    private TableView<PokaLek> tbe_lekarstwa2;
    @FXML
    private TableColumn kol_lek_id2;
    @FXML
    private TableColumn kol_lek_nazwa2;
    @FXML
    private TableColumn kol_lek_dawka2;
    @FXML
    private TableView<PokaWynik> tbe_wynik;
    @FXML
    private TableColumn kol_wynik_id;
    @FXML
    private TableColumn kol_wynik_imie;
    @FXML
    private TableColumn kol_wynik_naz;
    @FXML
    private TableColumn kol_wynik_wiek;
    
    private final ObservableList<PokaWynik> pokaWynik = FXCollections.observableArrayList();
    private final ObservableList<PokaPorada> pokaPorady = FXCollections.observableArrayList();
    private final ObservableList<PokaPacjent> pokaPacjenci = FXCollections.observableArrayList();
    private final ObservableList<PokaLekarz> pokaLekarze = FXCollections.observableArrayList();
    private final ObservableList<PokaLek> pokaLeki = FXCollections.observableArrayList();
    private final ObservableList<PokaChoroba> pokaChoroby = FXCollections.observableArrayList();
    private final ObservableList<PokaLek> pokaLeki2 = FXCollections.observableArrayList();
    private final ObservableList<PokaChoroba> pokaChoroby2 = FXCollections.observableArrayList();
    
    @FXML
    void wczytajPorady(ActionEvent event) throws SQLException {
        TypedQuery<Porada> query = MainApp.entityManager.createQuery("select p from Porada p", Porada.class);   
        List<Porada> porady = query.getResultList();
        pokaPorady.clear();
        porady.forEach((porada) -> {
            pokaPorady.add(new PokaPorada(porada.getId_porady(),porada.getData(),porada.getGodzina(),porada.getPacjent().getId_pacjenta(),porada.getLekarz().getId_lekarza()));
        });
    }
    
    @FXML
    void dodajPorade(ActionEvent event) throws SQLException {
        try {
            MainApp.entityManager.getTransaction().begin();
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_por_lek.getText()));
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_por_pac.getText()));

            Porada porada = new Porada();
            porada.setData(tf_por_data.getText());
            porada.setGodzina(tf_por_godz.getText());
            porada.setLekarz(lekarz);
            porada.setPacjent(pacjent);

            MainApp.entityManager.persist(porada);
            MainApp.entityManager.getTransaction().commit();
        } catch (Exception e) {
            showExceptionDialog("Wpisz dane");
        }
    }
    
    @FXML 
    void modyfikujPorade(ActionEvent event) throws SQLException {       
        try {
            MainApp.entityManager.getTransaction().begin();
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_por_lek.getText()));
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_por_pac.getText()));
        
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por_id.getText()));//1 to ex. id
            porada.setData(tf_por_data.getText());
            porada.setGodzina(tf_por_godz.getText());
            porada.setLekarz(lekarz);
            porada.setPacjent(pacjent);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }       
    }
    
    @FXML
    void usunPorade(ActionEvent event) throws SQLException {      
        try {
            MainApp.entityManager.getTransaction().begin();
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por_id.getText()));
            MainApp.entityManager.remove(porada);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }      
    }
    
    @FXML
    void wczytajPacjentow(ActionEvent event) throws SQLException {
        TypedQuery<Pacjent> query = MainApp.entityManager.createQuery("select p from Pacjent p", Pacjent.class);   
        List<Pacjent> pacjenci = query.getResultList();
        pokaPacjenci.clear();
        pacjenci.forEach((pacjent) -> {
            pokaPacjenci.add(new PokaPacjent(pacjent.getId_pacjenta(),pacjent.getImie(),pacjent.getNazwisko(),pacjent.getPesel()));
        });
    }
    
    @FXML
    void dodajPacjenta(ActionEvent event) throws SQLException {
        try {
            Pacjent pacjent = new Pacjent();
            pacjent.setImie(tf_pac_im.getText());
            pacjent.setNazwisko(tf_pac_naz.getText());
            pacjent.setPesel(Long.parseLong(tf_pac_pes.getText()));

            MainApp.entityManager.getTransaction().begin();
            MainApp.entityManager.persist(pacjent);
            MainApp.entityManager.getTransaction().commit();
        } catch (Exception e) {
            showExceptionDialog("Wpisz dane");
        }
    }
    
    @FXML 
    void modyfikujPacjenta(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_pac_id.getText()));
            pacjent.setImie(tf_pac_im.getText());
            pacjent.setNazwisko(tf_pac_naz.getText());
            pacjent.setPesel(Long.parseLong(tf_pac_pes.getText()));
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }        
    }
    
    @FXML
    void usunPacjenta(ActionEvent event) throws SQLException {    
        try {
            MainApp.entityManager.getTransaction().begin();
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_pac_id.getText()));
            MainApp.entityManager.remove(pacjent);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }        
    }
    
    @FXML
    void wczytajLekarzy(ActionEvent event) throws SQLException {
        TypedQuery<Lekarz> query = MainApp.entityManager.createQuery("select l from Lekarz l", Lekarz.class);   
        List<Lekarz> lekarze = query.getResultList();
        pokaLekarze.clear();
        lekarze.forEach((lekarz) -> {
            pokaLekarze.add(new PokaLekarz(lekarz.getId_lekarza(),lekarz.getImie(),lekarz.getNazwisko(),lekarz.getPesel(),lekarz.getSpecjalizacja(),lekarz.getZarobki()));
        });
    }
    
    @FXML
    void dodajLekarza(ActionEvent event) throws SQLException {
        try { 
            Lekarz lekarz = new Lekarz();
            lekarz.setImie(tf_lekarz_im.getText());
            lekarz.setNazwisko(tf_lekarz_naz.getText());
            lekarz.setPesel(Long.parseLong(tf_lekarz_pes.getText()));
            lekarz.setSpecjalizacja(tf_lekarz_spec.getText());
            lekarz.setZarobki(Integer.parseInt(tf_lekarz_zar.getText()));

            MainApp.entityManager.getTransaction().begin();
            MainApp.entityManager.persist(lekarz);
            MainApp.entityManager.getTransaction().commit();
        } catch (Exception e) {
            showExceptionDialog("Wpisz dane");
        }
    }
    
    @FXML 
    void modyfikujLekarza(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_lekarz_id.getText()));
            lekarz.setImie(tf_lekarz_im.getText());
            lekarz.setNazwisko(tf_lekarz_naz.getText());
            lekarz.setPesel(Long.parseLong(tf_lekarz_pes.getText()));
            lekarz.setSpecjalizacja(tf_lekarz_spec.getText());
            lekarz.setZarobki(Integer.parseInt(tf_lekarz_zar.getText()));
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }       
    }
    
    @FXML
    void usunLekarza(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_lekarz_id.getText()));
            MainApp.entityManager.remove(lekarz);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }       
    }
    
    @FXML
    void wczytajLeki(ActionEvent event) throws SQLException {
        TypedQuery<Lek> query = MainApp.entityManager.createQuery("select l from Lek l", Lek.class);   
        List<Lek> leki = query.getResultList();
        pokaLeki.clear();
        leki.forEach((lek) -> {
            pokaLeki.add(new PokaLek(lek.getId_leku(),lek.getNazwa(),lek.getDawka()));
        });
    }
    
    @FXML
    void dodajLek(ActionEvent event) throws SQLException {
        try {
            Lek lek = new Lek();
            lek.setDawka(Double.parseDouble(tf_lek_daw.getText()));
            lek.setNazwa(tf_lek_naz.getText());

            MainApp.entityManager.getTransaction().begin();
            MainApp.entityManager.persist(lek);
            MainApp.entityManager.getTransaction().commit();
        } catch (Exception e) {
            showExceptionDialog("Wpisz dane");
        }
    }
    
    @FXML 
    void modyfikujLek(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_lek_id.getText()));
            lek.setDawka(Double.parseDouble(tf_lek_daw.getText()));
            lek.setNazwa(tf_lek_naz.getText());
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }       
    }
    
    @FXML
    void usunLek(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_lek_id.getText()));
            MainApp.entityManager.remove(lek);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }        
    }
    
    
    @FXML
    void wczytajChoroby(ActionEvent event) throws SQLException {
        TypedQuery<Choroba> query = MainApp.entityManager.createQuery("select c from Choroba c", Choroba.class);   
        List<Choroba> choroby = query.getResultList();
        pokaChoroby.clear();
        choroby.forEach((choroba) -> {
            pokaChoroby.add(new PokaChoroba(choroba.getId_choroby(),choroba.getNazwa(),choroba.getTyp()));
        });
    }
    
    @FXML
    void dodajChorobe(ActionEvent event) throws SQLException {
        try {
            Choroba choroba = new Choroba();
            choroba.setNazwa(tf_cho_naz.getText());
            choroba.setTyp(tf_cho_typ.getText());

            MainApp.entityManager.getTransaction().begin();
            MainApp.entityManager.persist(choroba);
            MainApp.entityManager.getTransaction().commit();
        } catch (Exception e) {
            showExceptionDialog("Wpisz dane");
        }
    }
    
    @FXML 
    void modyfikujChorobe(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_cho_id.getText()));
            choroba.setNazwa(tf_cho_naz.getText());
            choroba.setTyp(tf_cho_typ.getText());
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }       
    }
    
    @FXML
    void usunChorobe(ActionEvent event) throws SQLException {        
        try {
            MainApp.entityManager.getTransaction().begin();
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_cho_id.getText()));
            MainApp.entityManager.remove(choroba);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }        
    }
    
    @FXML
    void wczytajTabelePorady(ActionEvent event) throws SQLException {
        try {
            TypedQuery<Choroba> query = MainApp.entityManager.createQuery("select c from Choroba c join c.porady p where p.id_porady = :id", Choroba.class);   
            query.setParameter("id", Integer.parseInt(tf_por2_id.getText()));
            List<Choroba> choroby = query.getResultList();
            pokaChoroby2.clear();
            choroby.forEach((choroba) -> {
                pokaChoroby2.add(new PokaChoroba(choroba.getId_choroby(),choroba.getNazwa(),choroba.getTyp()));
            });
            TypedQuery<Lek> query2 = MainApp.entityManager.createQuery("select l from Lek l join l.porady p where p.id_porady = :id", Lek.class);   
            query2.setParameter("id", Integer.parseInt(tf_por2_id.getText()));
            List<Lek> leki = query2.getResultList();
            pokaLeki2.clear();
            leki.forEach((lek) -> {
                pokaLeki2.add(new PokaLek(lek.getId_leku(),lek.getNazwa(),lek.getDawka()));
            });
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }
    }
    
    @FXML
    void dodajChorobePorady(ActionEvent event) throws SQLException {
        try {
            MainApp.entityManager.getTransaction().begin();
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por2_id.getText()));//1 to ex. id
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_por_cho_id.getText()));
            List<Choroba> choroby = porada.getChoroby();
            try {
                choroby.add(choroba);
            } catch (NullPointerException e) {
                choroby = new ArrayList<>();
                choroby.add(choroba);
            }    
            porada.setChoroby(choroby);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }   
    }
    
    @FXML
    void usunChorobePorady(ActionEvent event) throws SQLException {       
        try {
            MainApp.entityManager.getTransaction().begin();
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por2_id.getText()));//1 to ex. id
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_por_cho_id.getText()));
            List<Choroba> choroby = porada.getChoroby();
            try {
                choroby.remove(choroba);
                porada.setChoroby(choroby);
            } catch (Exception e) {
                System.err.println("Lista pusta");
            }
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }    
    }
    
    @FXML
    void dodajLekPorady(ActionEvent event) throws SQLException {
        try {
            MainApp.entityManager.getTransaction().begin();
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por2_id.getText()));//1 to ex. id
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_por_lek_id.getText()));
            List<Lek> leki = porada.getLeki();
            try {
                leki.add(lek);
            } catch (NullPointerException e) {
                leki = new ArrayList<>();
                leki.add(lek);
            }
            porada.setLeki(leki);
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        }  
    }
    
    @FXML
    void usunLekPorady(ActionEvent event) throws SQLException {   
        try {
            MainApp.entityManager.getTransaction().begin();
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por2_id.getText()));
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_por_lek_id.getText()));
            List<Lek> leki = porada.getLeki();
            try {
                leki.remove(lek);
                porada.setLeki(leki);
            } catch (Exception e) {
                showExceptionDialog("Lista pusta");
            }
            MainApp.entityManager.getTransaction().commit();
        } catch (NumberFormatException ef) {
            showExceptionDialog("Podaj id");
        } 
    }

    @FXML
    void wczytajWynik(ActionEvent event) throws SQLException {
        try {
            Query query = MainApp.entityManager.createNativeQuery("select p.id_pacjenta, p.imię, p.nazwisko, ObliczWiek(p.pesel)"
                    + " from pacjenci p, porady_lekarskie po, choroby_porady cp, choroby c "
                    + "where c.id_choroby = :id and c.id_choroby = cp.id_choroby and cp.id_porady = po.id_porady and po.id_pacjenta = p.id_pacjenta"); 

            query.setParameter("id", Integer.parseInt(tf_wysz_id.getText()));
            Iterator<?> iterator = query.getResultList().iterator();
            pokaWynik.clear();
            while (iterator.hasNext()) {
                Object[] wynik = (Object[]) iterator.next();
                Integer id = (Integer) wynik[0];
                String imie = (String) wynik[1];
                String nazwisko = (String) wynik[2];
                Integer wiek = (Integer) wynik[3];
                pokaWynik.add(new PokaWynik(id,imie,nazwisko,wiek));
            }  
        } catch (Exception e) {
            showExceptionDialog("Podaj id choroby");
        }
    }
       
    @FXML
    void cleanStudentsData(ActionEvent event) {
        cleanTable(tableView);
    }
    
    @FXML
    void autoWypelnianie(ActionEvent event) {
        Pacjent pacjent = new Pacjent();
        pacjent.setImie("Zygmunt");
        pacjent.setNazwisko("Szymański");
        pacjent.setPesel(35011358719L);
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(pacjent);
        MainApp.entityManager.getTransaction().commit();
        
        Pacjent pacjent2 = new Pacjent();
        pacjent2.setImie("Weronika");
        pacjent2.setNazwisko("Woźniak");
        pacjent2.setPesel(75030269522L);
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(pacjent2);
        MainApp.entityManager.getTransaction().commit();
        
        Pacjent pacjent3 = new Pacjent();
        pacjent3.setImie("Eustachy");
        pacjent3.setNazwisko("Kozłowski");
        pacjent3.setPesel(10282687211L);
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(pacjent3);
        MainApp.entityManager.getTransaction().commit();
        
        Lekarz lekarz = new Lekarz();
        lekarz.setImie("Celina");
        lekarz.setNazwisko("Borkowska");
        lekarz.setPesel(74060803822L);
        lekarz.setSpecjalizacja("Dermatolog");
        lekarz.setZarobki(3800);
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(lekarz);
        MainApp.entityManager.getTransaction().commit();
        
        Lekarz lekarz2 = new Lekarz();
        lekarz2.setImie("Lesław");
        lekarz2.setNazwisko("Dąbrowski");
        lekarz2.setPesel(57102707936L);
        lekarz2.setSpecjalizacja("Neurolog");
        lekarz2.setZarobki(4100);
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(lekarz2);
        MainApp.entityManager.getTransaction().commit();
        
                
        Choroba choroba = new Choroba();
        choroba.setNazwa("Grzybica");
        choroba.setTyp("skórne");
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(choroba);
        MainApp.entityManager.getTransaction().commit();
        
        Choroba choroba2 = new Choroba();
        choroba2.setNazwa("Miastenia");
        choroba2.setTyp("autoimmunologiczne");
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(choroba2);
        MainApp.entityManager.getTransaction().commit();
        
        Lek lek = new Lek();
        lek.setDawka(250);
        lek.setNazwa("Atratex");
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(lek);
        MainApp.entityManager.getTransaction().commit();
        
        MainApp.entityManager.getTransaction().begin();       
        Porada porada = new Porada();
        porada.setData("09/05/2019");
        porada.setGodzina("11:00");
        porada.setLekarz(lekarz);
        porada.setPacjent(pacjent);
        List<Choroba> choroby = new ArrayList<>();
        choroby.add(choroba);
        porada.setChoroby(choroby);
        List<Lek> leki = new ArrayList<>();
        leki.add(lek);
        porada.setLeki(leki);
        MainApp.entityManager.persist(porada);
        MainApp.entityManager.getTransaction().commit();
        
        MainApp.entityManager.getTransaction().begin();       
        Porada porada2 = new Porada();
        porada2.setData("25/04/2019");
        porada2.setGodzina("10:30");
        porada2.setLekarz(lekarz2);
        porada2.setPacjent(pacjent2);
        List<Choroba> choroby2 = new ArrayList<>();
        choroby2.add(choroba2);
        porada2.setChoroby(choroby2);
        MainApp.entityManager.persist(porada2);
        MainApp.entityManager.getTransaction().commit();
        
        MainApp.entityManager.getTransaction().begin();       
        Porada porada3 = new Porada();
        porada3.setData("27/06/2019");
        porada3.setGodzina("8:45");
        porada3.setLekarz(lekarz);
        porada3.setPacjent(pacjent3);
        List<Choroba> choroby3 = new ArrayList<>();
        choroby3.add(choroba);
        porada3.setChoroby(choroby3);
        MainApp.entityManager.persist(porada3);
        MainApp.entityManager.getTransaction().commit();    
    }
    
    @FXML
    void zalaczFunkcje(ActionEvent event) {   
        MainApp.entityManager.getTransaction().begin();
        Query query = MainApp.entityManager.createNativeQuery(  
            "CREATE OR REPLACE FUNCTION ObliczWiek(pesel BIGINT)" +
                    "RETURNS INTEGER AS $$" +
                    "DECLARE " +
                        "stulecie INTEGER = MOD(pesel/100000000,10);" +
                    "BEGIN " +
                        "IF stulecie > 7 THEN " +
                            "RETURN 219 - pesel/1000000000;" +
                        "END IF;" +
                        "IF stulecie > 1 THEN " +
                            "RETURN 19 - pesel/1000000000;" +
                        "END IF;" +
                        "RETURN 119 - pesel/1000000000;" +
                    "END; $$" +
                    "LANGUAGE PLPGSQL");
        query.executeUpdate();
        MainApp.entityManager.getTransaction().commit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        kol_por_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_por_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        kol_por_godz.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        kol_por_pac.setCellValueFactory(new PropertyValueFactory<>("idPacjenta"));
        kol_por_lekarz.setCellValueFactory(new PropertyValueFactory<>("idLekarza"));
        
        tbe_porady.setItems(pokaPorady);
        
        kol_pac_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_pac_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        kol_pac_naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        kol_pac_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        
        tbe_pacjenci.setItems(pokaPacjenci);
        
        kol_lekarz_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_lekarz_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        kol_lekarz_naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        kol_lekarz_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        kol_lekarz_spec.setCellValueFactory(new PropertyValueFactory<>("specjalizacja"));
        kol_lekarz_zar.setCellValueFactory(new PropertyValueFactory<>("zarobki"));
        
        tbe_lekarze.setItems(pokaLekarze);
        
        kol_cho_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_cho_nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kol_cho_typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
        
        tbe_choroby.setItems(pokaChoroby);
        
        kol_lek_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_lek_nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kol_lek_dawka.setCellValueFactory(new PropertyValueFactory<>("dawka"));
        
        tbe_lekarstwa.setItems(pokaLeki);
        
        kol_cho_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_cho_nazwa2.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kol_cho_typ2.setCellValueFactory(new PropertyValueFactory<>("typ"));
        
        tbe_choroby2.setItems(pokaChoroby2);
        
        kol_lek_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_lek_nazwa2.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kol_lek_dawka2.setCellValueFactory(new PropertyValueFactory<>("dawka"));
        
        tbe_lekarstwa2.setItems(pokaLeki2);
        
        kol_wynik_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        kol_wynik_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        kol_wynik_naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        kol_wynik_wiek.setCellValueFactory(new PropertyValueFactory<>("wiek"));
        
        tbe_wynik.setItems(pokaWynik);
    }
       
    public void cleanTable(TableView tableView) {
        if (!tableView.getItems().isEmpty()) {
            tableView.getItems().clear();
        }
        
        if (!tableView.getColumns().isEmpty()) {
            tableView.getColumns().clear();
        }
    }
    
    public void showExceptionDialog(String tekst) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Uwaga");
        alert.setHeaderText(tekst);
        alert.showAndWait();
    }
}
