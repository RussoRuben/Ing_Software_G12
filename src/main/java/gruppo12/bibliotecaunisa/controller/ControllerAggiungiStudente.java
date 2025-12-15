package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ControllerAggiungiStudente implements ControllerService {

    private BibliotecaService service;

    @FXML
    private TextField campoMatricola;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCognome;
    @FXML
    private TextField campoEmail;

    @FXML
    private Button bottoneConferma;
    @FXML
    private Label labelAvvisoMatricola;

    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {

        BooleanProperty matricolaValida = new SimpleBooleanProperty(false);

        campoMatricola.textProperty().addListener(
                (o, vecchioValore, nuovoValore) -> {

                    matricolaValida.set(false);

                    if (!nuovoValore.matches("^\\d*$")) {
                        labelAvvisoMatricola.setTextFill(Color.RED);
                        labelAvvisoMatricola.setText("Solo numeri ammessi.");
                        return;
                    }
                    if (nuovoValore.length() != 10) {
                        labelAvvisoMatricola.setTextFill(Color.RED);
                        labelAvvisoMatricola.setText("Matricola non valida.");
                        return;
                    }

                    String matricolaCerca = nuovoValore;
                    Studente studenteCercato = service.trovaStudente(matricolaCerca);

                    if (studenteCercato != null) {
                        labelAvvisoMatricola.setText("Matricola già esistente.");
                        return;
                    }

                    labelAvvisoMatricola.setTextFill(Color.GREEN);
                    labelAvvisoMatricola.setText("Matricola corretta.");

                    matricolaValida.set(true);
                });

        BooleanBinding campiValidi = matricolaValida.and(campoNome.textProperty().isNotEmpty()
                .and(campoCognome.textProperty().isNotEmpty()
                        .and(campoEmail.textProperty().isNotEmpty())));

        bottoneConferma.disableProperty().bind(campiValidi.not());
    }
    /*
    * @Brief Conferma l'aggiunta di uno studente
    */
    @FXML
    private void conferma() throws IOException {

        service.aggiungiStudente(new Studente(campoMatricola.getText(), campoNome.getText(), campoCognome.getText(), campoEmail.getText()));
        mostraPopup();
        App.cambiaPaginaPrincipaleConTab(1, service);
    }
    
    /*
    * @Brief Annulla l'operazione aggiungi studente
    */
    @FXML
    private void annulla() throws IOException {
        App.cambiaPaginaPrincipaleConTab(1, service);
    }
    
    /*
    * @Brief Mostra popup per informare l'aggiunta di uno studente
    */
    private void mostraPopup() {
        try {
            // prendo gli stili correnti (per caricare un popup con stile coerente)
            List<String> stiliCorrenti = new ArrayList<>();
            if (campoMatricola.getScene() != null) {
                stiliCorrenti.addAll(campoMatricola.getScene().getStylesheets());
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popupStudente.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.setAlwaysOnTop(true);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            // applico gli stili al popup
            scene.getStylesheets().addAll(stiliCorrenti);

            popupStage.setScene(scene);
            popupStage.show();
            popupStage.centerOnScreen();
            
            // creo la transizione per far sparire il popup
            PauseTransition wait = new PauseTransition(Duration.seconds(1));
            wait.setOnFinished((e) -> {
            // fade out del popup
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> popupStage.close());
            fadeOut.play();
            });
            
            wait.play();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
