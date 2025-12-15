package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.BooleanBinding;
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

public class ControllerAggiungiLibro implements ControllerService {

    private BibliotecaService service;

    @FXML
    private TextField campoCodice;

    @FXML
    private TextField campoTitolo;

    @FXML
    private TextField campoAutori;

    @FXML
    private TextField campoEditore;

    @FXML
    private TextField campoNumeroEdizione;

    @FXML
    private TextField campoAnno;

    @FXML
    private TextField campoCopieDisponibili;

    @FXML
    private Label labelAvvisoCodice;

    @FXML
    private Label labelAvvisoInput;

    @FXML
    private Button bottoneConferma;

    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {

        // controllo per campo ISBN
        campoCodice.textProperty().addListener(
                (o, vecchioValore, nuovoValore) -> {
                    if (!nuovoValore.matches("^\\d*$")) {
                        labelAvvisoCodice.setTextFill(Color.RED);
                        labelAvvisoCodice.setText("Solo numeri ammessi!");
                        return;
                    }
                    if (nuovoValore.length() != 13) {
                        labelAvvisoCodice.setTextFill(Color.RED);
                        labelAvvisoCodice.setText("ISBN non valido");
                        return;
                    }

                    labelAvvisoCodice.setTextFill(Color.GREEN);
                    labelAvvisoCodice.setText("ISBN corretto!");
                    String codiceCerca = nuovoValore;
                    Libro libroCercato = service.trovaLibro(codiceCerca);
                    if (libroCercato != null) {
                        labelAvvisoCodice.setText("ISBN trovato!");
                        autocompilaCampi(libroCercato);
                    } else {
                        autorimuoviCampi();
                    }
                });

        // Binding per mostrare il tasto conferma solo se i campi sono compilati
        BooleanBinding campiValidi = campoCodice.textProperty().length().isEqualTo(13)
                .and(campoTitolo.textProperty().isNotEmpty()
                        .and(campoAutori.textProperty().isNotEmpty()
                                .and(campoEditore.textProperty().isNotEmpty()
                                        .and(campoNumeroEdizione.textProperty().isNotEmpty()
                                                .and(campoAnno.textProperty().isNotEmpty()
                                                        .and(campoCopieDisponibili.textProperty().isNotEmpty()))))));

        bottoneConferma.disableProperty().bind(campiValidi.not());
    }
    
    /**
    * @brief Conferma l'aggiunta di un libro verificando che i campi siano corretti
    * @throws IOException
    */
    @FXML
    private void conferma() throws IOException {

        try {
            int numEdizione = Integer.parseInt(campoNumeroEdizione.getText());
            int anno = Integer.parseInt(campoAnno.getText());
            int copie = Integer.parseInt(campoCopieDisponibili.getText());

            if (copie < 0) {
                labelAvvisoInput.setText("Copie non valide");
                return;
            }

            service.aggiungiLibro(new Libro(campoCodice.getText(), campoTitolo.getText(), Arrays.asList(campoAutori.getText().trim().split("\\s*,\\s*")), campoEditore.getText(), numEdizione, anno, copie));
            mostraPopup();
            App.cambiaPaginaPrincipaleConTab(0, service);


        } catch (NumberFormatException e) {
            labelAvvisoInput.setText("Campi numerici non corretti (Edizione, Anno, Copie)");
        }
    }
    
    /**
    * @brief Annulla l'operazione aggiungi libro
    * @throws IOException
    */
    @FXML
    private void annulla() throws IOException {
        App.cambiaPaginaPrincipaleConTab(0, service);
    }
    
    /**
    * @brief Rimuovi una copia dal campoCopieDisponibili (azionato da bottone apposito)
    * @throws IOException
    */
    @FXML
    private void rimuoviCopia() throws IOException {
        int copie = Integer.parseInt(campoCopieDisponibili.getText());
        if (copie != 0) {
            copie--;
            campoCopieDisponibili.setText(String.valueOf(copie));
        }
    }
    
    /**
    * @brief Aggiungi una copia al campoCopieDisponibili (azionato da bottone apposito)
    * @throws IOException
    */
    @FXML
    private void aggiungiCopia() throws IOException {
        int copie = Integer.parseInt(campoCopieDisponibili.getText());
        copie++;
        campoCopieDisponibili.setText(String.valueOf(copie));
    }
    
    /**
    * @brief Copila campi in automatico quando il codice del libro è già esistente nell'archivio
    */
    private void autocompilaCampi(Libro libro) {
        campoTitolo.setText(libro.getTitolo());
        campoAutori.setText(libro.getAutoriString());
        campoEditore.setText(libro.getEditore());
        campoNumeroEdizione.setText(String.valueOf(libro.getNumeroEdizione()));
        campoAnno.setText(String.valueOf(libro.getAnno()));
        campoCopieDisponibili.setText(String.valueOf(libro.getCopieDisponibili()));
    }
    
    /**
    * @brief Reimposta tutti i campi ad un valore nullo
    */
    private void autorimuoviCampi() {
        campoTitolo.setText("");
        campoAutori.setText("");
        campoEditore.setText("");
        campoNumeroEdizione.setText("");
        campoAnno.setText("");
        campoCopieDisponibili.setText("1");
    }
    
    /**
    * @brief Mostra popup per informare l'aggiunta di un libro
    */
    private void mostraPopup() {
        try {
            // prendo gli stili correnti (per caricare un popup con stile coerente)
            List<String> stiliCorrenti = new ArrayList<>();
            if (campoCodice.getScene() != null) {
                stiliCorrenti.addAll(campoCodice.getScene().getStylesheets());
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popupLibro.fxml"));
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
