package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerVisualizzaStudente implements ControllerService{
    
    private BibliotecaService service;
    private Studente studenteSelezionato;
    
    @FXML
    private Button bottoneModifica;
    
    @FXML
    private TextField campoMatricola;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCognome;
    @FXML
    private TextField campoEmail;
    
    @FXML
    private TableView<Prestito> tabellaPrestitiStudente;
    
    @FXML
    private TableColumn<Prestito, String> colonnaCodicePrestito;
    
    @FXML
    private TableColumn<Prestito, String> colonnaLibroPrestito;
    
    @FXML
    private TableColumn<Prestito, String> colonnaDataFinePrestito;
    
    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }
    
    @FXML
    private void initialize() {
        // ASSEGNAZIONE COLONNE DELLA TABELLA PRESTITI STUDENTE
        colonnaCodicePrestito.setCellValueFactory(new PropertyValueFactory<Prestito, String>("codice"));
        colonnaLibroPrestito.setCellValueFactory(new PropertyValueFactory<Prestito, String>("libroString"));
        colonnaDataFinePrestito.setCellValueFactory(new PropertyValueFactory<Prestito, String>("dataFine"));
    }
    
     /**
     * @brief Metodo per modificare tutte le informazioni dello studente selezionato verificando che siano nel formato richiesto
     * @throws IOException 
     */
    @FXML
    private void modificaCampi() throws IOException {
        
        if (bottoneModifica.getText().equals("Modifica")) {
            campoMatricola.setDisable(false);
            campoNome.setDisable(false);
            campoCognome.setDisable(false);
            campoEmail.setDisable(false);
            bottoneModifica.setText("Salva");
        } else {
           
            String matricola = campoMatricola.getText();
            String nome = campoNome.getText();
            String cognome = campoCognome.getText();
            String email = campoEmail.getText();
            
            service.modificaStudente(matricola, nome, cognome, email);

            App.cambiaPaginaPrincipaleConTab(1, service);
        }
    }
    
    /**
     * @brief Metodo per ritornare alla pagina principale
     * @throws IOException 
     */
    @FXML
    private void indietro() throws IOException {
        
        App.cambiaPaginaPrincipaleConTab(1, service);
    }
    
    /**
     * @brief Metodo per eliminare e richiedere conferma di eliminazione dello studente
     * @throws IOException 
     */
    
    @FXML
    private void elimina() throws IOException {
        // dialog di alert per confermare la scelta di eliminazione
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questo studente?");
        alert.setContentText("L'operazione non può essere annullata.");

        Optional<ButtonType> risultato = alert.showAndWait();

        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            service.rimuoviStudente(studenteSelezionato);
            App.cambiaPaginaPrincipaleConTab(1, service);
        }
    }
   /**
    * @brief Metodo per compilare le caselle di testo con le e informazioni relative allo studente selezionato da mostrare tramite interfaccia
    * @param studente 
    */
    public void setStudente(Studente studente) {
        this.studenteSelezionato = studente;
        campoMatricola.setText(String.valueOf(studenteSelezionato.getMatricola()));
        campoNome.setText(String.valueOf(studenteSelezionato.getNome()));
        campoCognome.setText(String.valueOf(studenteSelezionato.getCognome()));
        campoEmail.setText(String.valueOf(studenteSelezionato.getEmail()));
        
        // SETTO GLI ITEMS DELLA TABELLA
        tabellaPrestitiStudente.setItems(studenteSelezionato.getListaPrestiti());
    }
}