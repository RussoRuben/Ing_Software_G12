package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import javafx.fxml.FXML;

import javafx.scene.control.Label;


public class ControllerVisualizzaPrestitoArchiviato implements ControllerService{
    
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
     * @brief Torna indietro alla pagina principale  
     */
    @FXML
    private void indietro() throws IOException {
        App.cambiaPaginaPrincipaleConTab(3, service);
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
