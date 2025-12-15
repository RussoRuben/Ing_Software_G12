package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerPrestito implements ControllerService {

    private BibliotecaService service;

    private Parent miaRoot;

    private Studente studenteSelezionato;
    private Libro libroSelezionato;

    @FXML
    private DatePicker pickerInizioPrestito;
    @FXML
    private DatePicker pickerFinePrestito;
    @FXML
    private TextField campoDurataPrestito;

    @FXML
    private Label labelSelezioneStudente;
    @FXML
    private Label labelSelezioneLibro;
    @FXML
    private Label labelAvvisoInput;

    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {
        // ad ogni modifica di inizioPrestito o durataPrestito aggiornami fine prestito chiamando la funzione
        pickerInizioPrestito.setValue(LocalDate.now());
        pickerInizioPrestito.valueProperty().addListener((o, vecchioValore, nuovoValore) -> aggiornaDataFinePrestito());
        campoDurataPrestito.textProperty().addListener((o, vecchioValore, nuovoValore) -> aggiornaDataFinePrestito());
    }
    
    /*
    * @Brief Conferma l'aggiunta di un prestito
    */
    @FXML
    private void conferma() throws IOException {

        try {
            LocalDate inizioPrestito = pickerInizioPrestito.getValue();
            LocalDate finePrestito = pickerFinePrestito.getValue();
            
            if( !campoDurataPrestito.getProperties().isEmpty()) {              
                finePrestito = inizioPrestito.plusDays(Long.parseLong(campoDurataPrestito.getText()));
            }

            service.aggiungiPrestito(new Prestito(studenteSelezionato, libroSelezionato, inizioPrestito, finePrestito));

            App.cambiaPaginaPrincipaleConTab(2, service);
        } catch ( NumberFormatException e) {
            labelAvvisoInput.setText("Durata giorni non valida.");
        }
    }
    
    /*
    * @Brief Annulla l'operazione prestito
    */
    @FXML
    private void annulla() throws IOException {
        App.cambiaPaginaPrincipaleConTab(2, service);
    }
    
    /*
    * @Brief Carica la pagina di ricerca studente per il prestito
    */
    @FXML
    private void avviaRicercaStudente() throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaRicercaStudente.fxml"));
        Parent root = loader.load();

        ControllerRicercaStudente controllerRicerca = loader.getController();
        controllerRicerca.setControllerChiamante(this);
        controllerRicerca.initService(service);

        App.setRoot(root);
    }
    
    /*
    * @Brief Carica la pagina di ricerca libro per il prestito
    */
    @FXML
    private void avviaRicercaLibro() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaRicercaLibro.fxml"));
        Parent root = loader.load();
        ControllerRicercaLibro controllerRicerca = loader.getController();
        controllerRicerca.setControllerChiamante(this);
        controllerRicerca.initService(service);

        App.setRoot(root);
    }
    
    /*
    * @Brief Aggiorna automaticamente il campo fine prestito in base alla durata di giorni inserita
    */
    private void aggiornaDataFinePrestito() {
        LocalDate inizio = pickerInizioPrestito.getValue();

        try {
            int durata = Integer.parseInt(campoDurataPrestito.getText());
            if (inizio != null && durata > 0) {
                pickerFinePrestito.setValue(inizio.plusDays(durata));
            } else {
                pickerFinePrestito.setValue(null);
            }
        } catch (NumberFormatException e) {
            pickerFinePrestito.setValue(null);
        }

    }
    
    /*
    * @Brief Imposta lo studente del prestito
    */
    public void setStudenteSelezionato(Studente studente) {
        this.studenteSelezionato = studente;
        labelSelezioneStudente.setText(studenteSelezionato.toString());

    }
    
    /*
    * @Brief Imposta il libro del prestito
    */
    public void setLibroSelezionato(Libro libro) {
        this.libroSelezionato = libro;
        labelSelezioneLibro.setText(libroSelezionato.toString());
    }
    
    /*
    * @Brief Metodo getter per la root di controller prestito
    */
    public Parent getRoot() {
        return miaRoot;
    }
    
    /*
    * @Brief Metodo setter per la root di controller prestito
    */
    public void setRoot(Parent root) {
        this.miaRoot = root;
    }
}
