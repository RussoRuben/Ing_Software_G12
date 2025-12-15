package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;


public class ControllerRestituzione implements ControllerService{
    
    private BibliotecaService service;
    
    private Prestito prestitoSelezionato;
    
    @FXML
    private Label labelMatricola;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelCognome;
    @FXML
    private Label labelCodice;
    @FXML
    private Label labelTitolo;
    @FXML
    private Label labelAutori;
    @FXML
    private Label labelDataInizioPrestito;
    @FXML
    private Label labelDataFinePrestito;
    
    
    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }

    /**
     * @brief Conferma la restituzione del prestito selezionato
     * Presenta un dialogo di conferma prima di procedere all'eliminazione
     */
    @FXML
    private void conferma() throws IOException {
       // dialog di alert per confermare la scelta di eliminazione
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma restituzione");
        alert.setHeaderText("Sei sicuro di voler confermare la restituzione?");
        alert.setContentText("L'operazione non può essere annullata.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            service.restituisciPrestito(prestitoSelezionato);
            App.cambiaPaginaPrincipaleConTab(2, service);
        }
    }
    
    /**
     * @brief Annulla l'operazione di restituzione del prestito
     */
    @FXML
    private void cancella() throws IOException {
        App.cambiaPaginaPrincipaleConTab(2, service);
    }
    
    /**
     * @brief Metodo set per impostare i campi dell'interfaccia
     * @param prestito I dati del prestito
     */
    public void setCampiPrestito(Prestito prestito) {
        this.prestitoSelezionato = prestito;
        labelMatricola.setText(String.valueOf(prestitoSelezionato.getStudente().getMatricola()));
        labelNome.setText(prestitoSelezionato.getStudente().getNome());
        labelCognome.setText(prestitoSelezionato.getStudente().getCognome());
        labelCodice.setText(String.valueOf(prestitoSelezionato.getLibro().getCodice()));
        labelTitolo.setText(prestitoSelezionato.getLibro().getTitolo());
        labelAutori.setText(prestitoSelezionato.getLibro().getAutoriString());
        labelDataInizioPrestito.setText(prestitoSelezionato.getDataInizio().toString());
        labelDataFinePrestito.setText(prestitoSelezionato.getDataFine().toString());
    }
}

