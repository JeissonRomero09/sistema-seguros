package co.edu.poli.Sw2.Examen2.Vista.App;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 * <p>
 * Inicializa la ventana principal y carga la vista ubicada en el recurso
 * {@code /co/edu/poli/Sw2/Examen2/Seguros.fxml}.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Punto de entrada de la aplicación JavaFX.
     *
     * @param primaryStage escenario principal
     * @throws IOException si no puede cargar el archivo FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/Sw2/Examen2/Seguros.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestión de Seguros");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}