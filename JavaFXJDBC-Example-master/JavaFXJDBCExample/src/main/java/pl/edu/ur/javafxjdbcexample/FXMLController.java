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
import javax.persistence.TypedQuery;
import static pl.edu.ur.javafxjdbcexample.MainApp.entityManager;
import pl.edu.ur.javafxjdbcexample.domain.Pacjent;
import pl.edu.ur.javafxjdbcexample.domain.Choroba;
import pl.edu.ur.javafxjdbcexample.domain.Lek;
import pl.edu.ur.javafxjdbcexample.domain.Lekarz;
import pl.edu.ur.javafxjdbcexample.domain.Porada;
import pl.edu.ur.javafxjdbcexample.observabledata.PokaChoroba;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button loadButton;
    
    @FXML
    private Button cleanButton;
    
    @FXML
    private Button executeButton;
    
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
    private Button btn_wczytaj_porady;
    @FXML
    private Button btn_dodaj_porady;
    @FXML
    private Button btn_zmodyfikuj_porady;
    @FXML
    private Button btn_usun_porady;
    @FXML
    private TableView<?> tbe_porady;
    @FXML
    private TableView<?> tbe_pacjenci;
    @FXML
    private Button btn_wczytaj_pacjenci;
    @FXML
    private Button btn_dodaj_pacjenci;
    @FXML
    private Button btn_zmodyfikuj_pacjenci;
    @FXML
    private Button btn_usun_pacjenci;
    @FXML
    private TableView<?> tbe_lekarze;
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
    private TableColumn<PokaChoroba,Number> kol_cho_id;
    @FXML
    private TableColumn<PokaChoroba,String> kol_cho_nazwa;
    @FXML
    private TableColumn<PokaChoroba,String> kol_cho_typ;
    @FXML
    private Button btn_wczytaj_choroby;
    @FXML
    private Button btn_dodaj_choroby;
    @FXML
    private Button btn_zmodyfikuj_choroby;
    @FXML
    private Button btn_usun_choroby;
    @FXML
    private TableView<?> tbe_lekarstwa;
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
    private TableView<?> tbe_pacjent;
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
    
    @FXML
    void wczytajPorady(ActionEvent event) throws SQLException {
        
    }
    
    @FXML
    void dodajPorade(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_por_lek.getText()));
        Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_por_pac.getText()));
        
        Porada porada = new Porada();
        porada.setData(LocalDate.MAX);//pobrać z tf_por_data.getText()
        porada.setGodzina(LocalTime.MIN);//pobrać z tf_por_godz.getText()
        porada.setLekarz(lekarz);
        porada.setPacjent(pacjent);
        
        MainApp.entityManager.persist(pacjent);
        MainApp.entityManager.getTransaction().commit();
    }
    
    @FXML 
    void modyfikujPorade(ActionEvent event) throws SQLException {
        MainApp.entityManager.getTransaction().begin();
        try {
            Lekarz lekarz = MainApp.entityManager.find(Lekarz.class, Integer.parseInt(tf_por_lek.getText()));
            Pacjent pacjent = MainApp.entityManager.find(Pacjent.class, Integer.parseInt(tf_por_pac.getText()));
        
            Porada porada = MainApp.entityManager.find(Porada.class, Integer.parseInt(tf_por_id.getText()));//1 to ex. id
            porada.setData(LocalDate.MAX);//pobrać z tf_por_data.getText()
            porada.setGodzina(LocalTime.MIN);//pobrać z tf_por_godz.getText()
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
        ObservableList<PokaChoroba> pokaChoroby = FXCollections.observableArrayList();
        choroby.forEach((choroba) -> {
            pokaChoroby.add(new PokaChoroba(choroba.getId_choroby(),choroba.getNazwa(),choroba.getTyp()));
        });
        cleanTable(tbe_choroby);
      //  tbe_choroby.setItems(pokaChoroby);
        tbe_choroby.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        kol_cho_id.setCellValueFactory(cell -> cell.getValue().getIdProperty());
        kol_cho_nazwa.setCellValueFactory(cell -> cell.getValue().getNazwaProperty());
        kol_cho_typ.setCellValueFactory(cell -> cell.getValue().getTypProperty());
        
        tbe_choroby.setItems(pokaChoroby);
        //tbe_choroby.getColumns().addAll(kol_cho_id,kol_cho_nazwa,kol_cho_typ);
        for (Choroba choroba : choroby) {
            System.out.println(choroba.getId_choroby());
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
