package pl.edu.ur.javafxjdbcexample;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
