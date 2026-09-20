package co.edu.uniquindio.poo.taller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BibliotecaApplication extends Application {
    @Override
    /** Carga la vista FXML y muestra la ventana principal de la aplicacion. */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BibliotecaApplication.class.getResource("biblioteca-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 980, 640);
        stage.setTitle("Biblioteca universitaria");
        stage.setScene(scene);
        stage.show();
    }

    /** Punto de entrada que inicia el ciclo de vida de JavaFX. */
    public static void main(String[] args) {
        launch(args);
    }
}
