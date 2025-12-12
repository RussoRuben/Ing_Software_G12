package gruppo12.bibliotecaunisa;

import gruppo12.bibliotecaunisa.controller.ControllerPrincipale;
import gruppo12.bibliotecaunisa.data.BibliotecaData;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/paginaPrincipale.fxml"));
        Parent root = loader.load();

        scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        // setto larghezza e altezza minime pari a quelle minime di TabPane
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        stage.setTitle("BibliotecaUniSa");

        // imposto i datePicker in italiano
        Locale.setDefault(Locale.ITALIAN);

        // carico il tema scuro di default alla scena
        scene.getStylesheets().add(getClass().getResource("/stili/moderno-chiaro.css").toExternalForm());
        
        // creo data
        BibliotecaData data = new BibliotecaData();
        // carico stato
        data.caricaStato();
        // creo il service
        BibliotecaService service = new BibliotecaService(data);
                
        ControllerPrincipale controller = loader.getController();
        controller.initService(service);

        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // overload di setRoot per poter usare la funzione anche col tipo Parent (serve per lo switch delle tabs)
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void cambiaPaginaPrincipaleConTab(int indiceTab, BibliotecaService service) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaPrincipale.fxml"));
        Parent root = loader.load();

        ControllerPrincipale controller = loader.getController();
        controller.selezionaTab(indiceTab);
        controller.initService(service);
        
        App.setRoot(root);
    }
}
