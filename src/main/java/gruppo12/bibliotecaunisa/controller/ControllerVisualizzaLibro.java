package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerVisualizzaLibro implements ControllerService {

    private BibliotecaService service;
    private Libro libroSelezionato;

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
    private Button bottoneModifica;

    @FXML
    private Button bottoneAggiungiCopia;

    @FXML
    private Button bottoneRimuoviCopia;

    @FXML
    private Label labelAvvisoInput;

    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {

        BooleanBinding campiValidi = campoTitolo.textProperty().isNotEmpty()
                .and(campoAutori.textProperty().isNotEmpty()
                        .and(campoEditore.textProperty().isNotEmpty()
                                .and(campoNumeroEdizione.textProperty().isNotEmpty()
                                        .and(campoAnno.textProperty().isNotEmpty()
                                                .and(campoCopieDisponibili.textProperty().isNotEmpty())))));

        bottoneModifica.disableProperty().bind(campiValidi.not());
    }
    
    /**
     * @brief Metodo per modificare tutte le informazioni del libro selezionato verificando che siano nel formato richiesto
     * @throws IOException 
     */
    @FXML
    private void modificaCampi() throws IOException {

        if (bottoneModifica.getText().equals("Modifica")) {
            campoCodice.setDisable(false);
            campoTitolo.setDisable(false);
            campoAutori.setDisable(false);
            campoEditore.setDisable(false);
            campoNumeroEdizione.setDisable(false);
            campoAnno.setDisable(false);
            campoCopieDisponibili.setDisable(false);
            bottoneRimuoviCopia.setDisable(false);
            bottoneAggiungiCopia.setDisable(false);
            bottoneModifica.setText("Salva");
        } else {

            try {

                int anno = Integer.parseInt(campoAnno.getText());
                int copie = Integer.parseInt(campoCopieDisponibili.getText());
                int numEdizione = Integer.parseInt(campoNumeroEdizione.getText());
                
                if(copie < 0) {
                    labelAvvisoInput.setText("Copie non valide");
                    return;
                }

                service.modificaLibro(campoCodice.getText(), campoTitolo.getText(), Arrays.asList(campoAutori.getText().trim().split("\\s*,\\s*")), campoEditore.getText(), numEdizione, anno, copie);
                App.cambiaPaginaPrincipaleConTab(0, service);

            } catch (NumberFormatException e) {
                labelAvvisoInput.setText("Campi numerici non corretti (Edizione, Anno, Copie)");
            }
        }
    }
    
    
    /**
     * @brief Metodo per ritornare alla pagina principale
     * @throws IOException 
     */
    @FXML
    private void indietro() throws IOException {
        App.cambiaPaginaPrincipaleConTab(0, service);
    }
    
    
    /**
     * @brief Metodo per eliminare e chiedere conferma di eliminazione del libro
     * @throws IOException 
     */
    @FXML
    private void elimina() throws IOException {

        // dialog di alert per confermare la scelta di eliminazione
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questo libro?");
        alert.setContentText("L'operazione non può essere annullata.");

        Optional<ButtonType> risultato = alert.showAndWait();

        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            service.rimuoviLibro(libroSelezionato);
            App.cambiaPaginaPrincipaleConTab(0, service);
        }
    }
    /**
     * @brief Metodo per diminuire il numero di copie disponibili del libro
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
     * @brief Metodo per aumentare il numero di copie disponibili del libro
     * @throws IOException 
     */
    @FXML
    private void aggiungiCopia() throws IOException {
        int copie = Integer.parseInt(campoCopieDisponibili.getText());
        copie++;
        campoCopieDisponibili.setText(String.valueOf(copie));
    }
    
    /**
     * @brief Metodo per compilare le caselle di testo con le e informazioni relative al libro selezionato da mostrare tramite interfaccia
     * @param libro 
     */
    public void setLibro(Libro libro) {
        this.libroSelezionato = libro;
        campoCodice.setText(String.valueOf(libroSelezionato.getCodice()));
        campoTitolo.setText(libroSelezionato.getTitolo());
        campoAutori.setText(libroSelezionato.getAutoriString());
        campoEditore.setText(libroSelezionato.getEditore());
        campoNumeroEdizione.setText(String.valueOf(libroSelezionato.getNumeroEdizione()));
        campoAnno.setText(String.valueOf(libroSelezionato.getAnno()));
        campoCopieDisponibili.setText(String.valueOf(libroSelezionato.getCopieDisponibili()));
    }
}
