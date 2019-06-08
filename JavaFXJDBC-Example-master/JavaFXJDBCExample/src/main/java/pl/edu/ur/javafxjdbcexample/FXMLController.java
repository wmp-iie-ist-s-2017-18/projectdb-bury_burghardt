package pl.edu.ur.javafxjdbcexample;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import pl.edu.ur.javafxjdbcexample.database.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextArea sqlQuery;
    
    @FXML
    private TableView<ObservableList> tableView;
    
    DatabaseHelper dbHelper = new DatabaseHelper();
    private ObservableList data = FXCollections.observableArrayList();
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
    private Button btn_wczytaj_porady;
    @FXML
    private Button btn_dodaj_porady;
    @FXML
    private Button btn_zmodyfikuj_porady;
    @FXML
    private Button btn_usun_porady;
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
    private Button btn_wczytaj_pacjenci;
    @FXML
    private Button btn_dodaj_pacjenci;
    @FXML
    private Button btn_zmodyfikuj_pacjenci;
    @FXML
    private Button btn_usun_pacjenci;
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
    private Button btn_wczytaj_lekarze;
    @FXML
    private Button btn_dodaj_lekarze;
    @FXML
    private Button btn_zmodyfikuj_lekarze;
    @FXML
    private Button btn_usun_lekarze;
    @FXML
    private TableView<PokaChoroba> tbe_choroby;
    @FXML
    private TableColumn kol_cho_id;
    @FXML
    private TableColumn kol_cho_nazwa;
    @FXML
    private TableColumn kol_cho_typ;
    @FXML
    private Button btn_wczytaj_choroby;
    @FXML
    private Button btn_dodaj_choroby;
    @FXML
    private Button btn_zmodyfikuj_choroby;
    @FXML
    private Button btn_usun_choroby;
    @FXML
    private TableView<PokaLek> tbe_lekarstwa;
    @FXML
    private TableColumn kol_lek_id;
    @FXML
    private TableColumn kol_lek_nazwa;
    @FXML
    private TableColumn kol_lek_dawka;
    @FXML
    private Button btn_wczytaj_lekarstwa;
    @FXML
    private Button btn_dodaj_lekarstwa;
    @FXML
    private Button btn_zmodyfikuj_lekarstwa;
    @FXML
    private Button btn_usun_lekarstwa;
    @FXML
    private Button btn_wczytaj_porada;
    @FXML
    private Button btn_dodaj_porada;
    @FXML
    private Button btn_zmodyfikuj_porada;
    @FXML
    private Button btn_usun_porada;
    @FXML
    private TableView tbe_pacjent;
    @FXML
    private TextField tf_pac_wie;
    @FXML
    private Button btn_wczytaj_pacjent;
    @FXML
    private Button btn_dodaj_pacjent;
    @FXML
    private Button btn_zmodyfikuj_pacjent;
    @FXML
    private Button btn_usun_pacjent;
    
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
        for (PokaPorada porada : pokaPorady) {
            System.out.println(porada.getId());
            System.out.println(porada.getData());
            System.out.println(porada.getGodzina());
            System.out.println(porada.getIdPacjenta());
            System.out.println(porada.getIdLekarza());
        }
        
    }
    
    @FXML
    void dodajPorade(ActionEvent event) throws SQLException {
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
    }
    
    @FXML 
    void modyfikujPorade(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_por_lek.getText()));
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_por_pac.getText()));
        
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por_id.getText()));//1 to ex. id
            porada.setData(tf_por_data.getText());
            porada.setGodzina(tf_por_godz.getText());
            porada.setLekarz(lekarz);
            porada.setPacjent(pacjent);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML
    void usunPorade(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por_id.getText()));
            MainApp.entityManager.remove(porada);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
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
        Pacjent pacjent = new Pacjent();
        pacjent.setImie(tf_pac_im.getText());
        pacjent.setNazwisko(tf_pac_naz.getText());
        pacjent.setPesel(Long.parseLong(tf_pac_pes.getText()));
        
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(pacjent);
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML 
    void modyfikujPacjenta(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_pac_id.getText()));
            pacjent.setImie(tf_pac_im.getText());
            pacjent.setNazwisko(tf_pac_naz.getText());
            pacjent.setPesel(Long.parseLong(tf_pac_pes.getText()));
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML
    void usunPacjenta(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_pac_id.getText()));
            MainApp.entityManager.remove(pacjent);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
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
        Lekarz lekarz = new Lekarz();
        lekarz.setImie(tf_lekarz_im.getText());
        lekarz.setNazwisko(tf_lekarz_naz.getText());
        lekarz.setPesel(Long.parseLong(tf_lekarz_pes.getText()));
        lekarz.setSpecjalizacja(tf_lekarz_spec.getText());
        lekarz.setZarobki(Integer.parseInt(tf_lekarz_zar.getText()));
        
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(lekarz);
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML 
    void modyfikujLekarza(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_lekarz_id.getText()));
            lekarz.setImie(tf_lekarz_im.getText());
            lekarz.setNazwisko(tf_lekarz_naz.getText());
            lekarz.setPesel(Long.parseLong(tf_lekarz_pes.getText()));
            lekarz.setSpecjalizacja(tf_lekarz_spec.getText());
            lekarz.setZarobki(Integer.parseInt(tf_lekarz_zar.getText()));
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML
    void usunLekarza(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_lekarz_id.getText()));
            MainApp.entityManager.remove(lekarz);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
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
        Lek lek = new Lek();
        lek.setDawka(Double.parseDouble(tf_lek_daw.getText()));
        lek.setNazwa(tf_lek_naz.getText());
        
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(lek);
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML 
    void modyfikujLek(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_lek_id.getText()));
            lek.setDawka(Double.parseDouble(tf_lek_daw.getText()));
            lek.setNazwa(tf_lek_naz.getText());
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML
    void usunLek(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lek lek = MainApp.entityManager.find(Lek.class, Integer.parseInt(tf_lek_id.getText()));
            MainApp.entityManager.remove(lek);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    
    @FXML
    void wczytajChoroby(ActionEvent event) throws SQLException {
        TypedQuery<Choroba> query = MainApp.entityManager.createQuery("select c from Choroba c", Choroba.class);   
        List<Choroba> choroby = query.getResultList();
        pokaChoroby.clear();
        choroby.forEach((choroba) -> {
            pokaChoroby.add(new PokaChoroba(choroba.getId_choroby(),choroba.getNazwa(),choroba.getTyp()));
        });
        for (PokaChoroba choroba : pokaChoroby) {
            System.out.println(choroba.getId());
            System.out.println(choroba.getNazwa());
            System.out.println(choroba.getTyp());
        }
    }
    
    @FXML
    void dodajChorobe(ActionEvent event) throws SQLException {
        Choroba choroba = new Choroba();
        choroba.setNazwa(tf_cho_naz.getText());
        choroba.setTyp(tf_cho_typ.getText());
        
        MainApp.entityManager.getTransaction().begin();
        MainApp.entityManager.persist(choroba);
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML 
    void modyfikujChorobe(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_cho_id.getText()));
            choroba.setNazwa(tf_cho_naz.getText());
            choroba.setTyp(tf_cho_typ.getText());
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML
    void usunChorobe(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Choroba choroba = MainApp.entityManager.find(Choroba.class, Integer.parseInt(tf_cho_id.getText()));
            MainApp.entityManager.remove(choroba);
        } catch (NumberFormatException ef) {
            System.err.println("Podaj id");
        }
        MainApp.entityManager.getTransaction().commit();
    }
    @FXML
    void LoadStudentsData(ActionEvent event) throws SQLException {
        
        cleanTable(tableView);
        
        Connection conn = dbHelper.getConnection();
        ResultSet rs = conn.prepareStatement("SELECT * FROM student").executeQuery();
        loadData(rs);
        conn.close();
        
    }
    
    @FXML
    void cleanStudentsData(ActionEvent event) {
        cleanTable(tableView);
    }
    
    @FXML
    void executeQuery(ActionEvent event) throws SQLException, IOException {
        
        cleanTable(tableView);
        Connection conn = dbHelper.getConnection();
        
        if (sqlQuery.getText().isEmpty()) {
            // populate dialog with controls.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Empty query detected!");
            alert.showAndWait();
        } else {
            try {
                ResultSet rs = conn.prepareStatement(sqlQuery.getText()).executeQuery();
                loadData(rs);
            } catch (SQLException sqlEx) {
                showExceptionDialog(sqlEx);
            }
        }
        conn.close();
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
        porada.getChoroby().add(MainApp.entityManager.find(Choroba.class, 6));
        porada.getLeki().add(MainApp.entityManager.find(Lek.class, 8));
        MainApp.entityManager.persist(porada);
        MainApp.entityManager.getTransaction().commit();
        
        MainApp.entityManager.getTransaction().begin();       
        Porada porada2 = new Porada();
        porada2.setData("25/04/2019");
        porada2.setGodzina("10:30");
        porada2.setLekarz(lekarz2);
        porada2.setPacjent(pacjent2);
        porada2.getChoroby().add(MainApp.entityManager.find(Choroba.class, 7));
        MainApp.entityManager.persist(porada2);
        MainApp.entityManager.getTransaction().commit();
        
        MainApp.entityManager.getTransaction().begin();       
        Porada porada3 = new Porada();
        porada3.setData("27/06/2019");
        porada3.setGodzina("8:45");
        porada3.setLekarz(lekarz);
        porada3.setPacjent(pacjent3);
        porada3.getChoroby().add(MainApp.entityManager.find(Choroba.class, 6));
        MainApp.entityManager.persist(porada3);
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
    }
    
    public void loadData(ResultSet rs) throws SQLException {
        
        cleanTable(tableView);
        
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            
            tableView.getColumns().addAll(col);
            System.out.println("Column [" + i + "] ");
        }
        
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(String.valueOf(rs.getObject(i)));
            }
            
            System.out.println("Row [1] added " + row.toString());
            data.add(row);  
        }

        //FINALLY ADDED TO TableView
        tableView.setItems(data);
    }
    
    public void cleanTable(TableView tableView) {
        if (!tableView.getItems().isEmpty()) {
            tableView.getItems().clear();
        }
        
        if (!tableView.getColumns().isEmpty()) {
            tableView.getColumns().clear();
        }
    }
    
    public void showExceptionDialog(Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an exception occured");
        alert.setContentText(ex.getMessage());
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        
        Label label = new Label("The exception stacktrace was:");
        
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
}
