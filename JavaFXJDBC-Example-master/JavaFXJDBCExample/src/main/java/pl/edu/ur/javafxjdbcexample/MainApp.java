package pl.edu.ur.javafxjdbcexample;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.ur.javafxjdbcexample.domain.Pacjent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Przychodnia lekarska");
        stage.setScene(scene);
        stage.show();

       /* EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Przychodnia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

       /* Pacjent pacjent = new Pacjent();
        pacjent.setImie("Jan");
        pacjent.setNazwisko("Nowak");
        pacjent.setPesel(98041700032L);*/

        /*entityManager.getTransaction().begin();
        entityManager.persist(pacjent);
        entityManager.getTransaction().commit();*/
/*
        entityManager.close();
        entityManagerFactory.close();*/
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
