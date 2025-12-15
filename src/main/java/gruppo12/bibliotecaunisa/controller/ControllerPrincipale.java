package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerPrincipale implements ControllerService{
    
    BibliotecaService service;

    @FXML
    private TabPane tabPane;
    @FXML
    private Button bottoneVisualizzaLibro;
    @FXML
    private Button bottoneVisualizzaStudente;
    @FXML
    private Button bottoneRestituzionePrestito;
    @FXML
    private Button bottoneVisualizzaPrestitoArchiviato;

    private boolean temaScuro = false;

    @FXML
    private TextField cercaLibro;
    @FXML
    private TextField cercaStudente;
    @FXML
    private TextField cercaPrestito;
    @FXML
    private TextField cercaPrestitoArchiviato;

    // TABELLA LIBRI
    @FXML
    private TableView<Libro> tabellaLibri;
    @FXML
    private TableColumn<Libro, String> colonnaCodice;
    @FXML
    private TableColumn<Libro, String> colonnaTitolo;
    @FXML
    private TableColumn<Libro, String> colonnaAutori;
    @FXML
    private TableColumn<Libro, String> colonnaAnno;
    @FXML
    private TableColumn<Libro, String> colonnaCopieDisponibili;

    // TABELLA STUDENTI
    @FXML
    private TableView<Studente> tabellaStudenti;
    @FXML
    private TableColumn<Studente, String> colonnaMatricola;
    @FXML
    private TableColumn<Studente, String> colonnaNome;
    @FXML
    private TableColumn<Studente, String> colonnaCognome;
    @FXML
    private TableColumn<Studente, String> colonnaEmail;

    // TABELLA PRESTITI
    @FXML
    private TableView<Prestito> tabellaPrestiti;
    @FXML
    private TableColumn<Prestito, String> colonnaCodicePrestito;
    @FXML
    private TableColumn<Prestito, String> colonnaDataInizioPrestito;
    @FXML
    private TableColumn<Prestito, String> colonnaDataFinePrestito;
    @FXML
    private TableColumn<Prestito, String> colonnaStudentePrestito;
    @FXML
    private TableColumn<Prestito, String> colonnaLibroPrestito;

    // TABELLA PRESTITI ARCHIVIATI
    @FXML
    private TableView<Prestito> tabellaPrestitiArchiviati;
    @FXML
    private TableColumn<Prestito, String> colonnaCodicePrestitoArchiviato;
    @FXML
    private TableColumn<Prestito, String> colonnaDataInizioPrestitoArchiviato;
    @FXML
    private TableColumn<Prestito, String> colonnaDataFinePrestitoArchiviato;
    @FXML
    private TableColumn<Prestito, String> colonnaStudentePrestitoArchiviato;
    @FXML
    private TableColumn<Prestito, String> colonnaLibroPrestitoArchiviato;
    
    @Override
    public void initService(BibliotecaService service) {
        
        this.service = service;
        
        setupFiltroRicerca(service.getLibri(), tabellaLibri, cercaLibro, libro -> libro.getTitolo() + " " + libro.getCodice() + " " + libro.getAutori());
        setupFiltroRicerca(service.getStudenti(), tabellaStudenti, cercaStudente, studente -> studente.getCognome() + " " + studente.getMatricola());
        setupFiltroRicerca(service.getPrestiti(), tabellaPrestiti, cercaPrestito, prestito -> prestito.getCodice() + " " + prestito.getStudente().getMatricola());
        setupFiltroRicerca(service.getPrestitiArchiviati(), tabellaPrestitiArchiviati, cercaPrestitoArchiviato, prestito -> prestito.getCodice() + " " + prestito.getStudente().getMatricola());

    }

    @FXML
    public void initialize() {
        
        inizializzaCampiTabelle();
        inizializzaBottoni();

        // imposto di default l'ordinamento della tabella dei prestiti, settandolo su DataFinePrestito
        colonnaDataFinePrestito.setSortType(TableColumn.SortType.ASCENDING);
        tabellaPrestiti.getSortOrder().add(colonnaDataFinePrestito);
        tabellaPrestiti.sort();

        // imposto di default l'ordinamento della tabella dei prestiti archiviati, settandolo su DataFinePrestito
        colonnaDataFinePrestitoArchiviato.setSortType(TableColumn.SortType.ASCENDING);
        tabellaPrestitiArchiviati.getSortOrder().add(colonnaDataFinePrestitoArchiviato);
        tabellaPrestitiArchiviati.sort();        
    }
    
    /**
    * @brief Seleziona una tab del TabPane principale
    * @param i Tab da selezionare
    */
    public void selezionaTab(int i) {
        tabPane.getSelectionModel().select(i);
    }
    
    /**
    * @brief Carica la pagina aggiungi libro
    * @throws IOException
    */
    @FXML
    private void mostraPaginaAggiungiLibro() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaAggiungiLibro.fxml"));
        Parent root = loader.load();

        // prendo il controller di visualizza libro
        ControllerAggiungiLibro controller = loader.getController();
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief Carica la pagina visualizza libro
    * @throws IOException
    */
    @FXML
    private void mostraPaginaVisualizzaLibro() throws IOException {
        Libro libroSelezionato = tabellaLibri.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaVisualizzaLibro.fxml"));
        Parent root = loader.load();

        // prendo il controller di visualizza libro
        ControllerVisualizzaLibro controller = loader.getController();
        controller.setLibro(libroSelezionato);
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief Carica la pagina aggiungi studente
    * @throws IOException
    */
    @FXML
    private void mostraPaginaAggiungiStudente() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaAggiungiStudente.fxml"));
        Parent root = loader.load();

        // prendo il controller di visualizza libro
        ControllerAggiungiStudente controller = loader.getController();
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief Carica la pagina visualizza studente
    * @throws IOException
    */
    @FXML
    private void mostraPaginaVisualizzaStudente() throws IOException {
        Studente studenteSelezionato = tabellaStudenti.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaVisualizzaStudente.fxml"));
        Parent root = loader.load();

        // prendo il controller di visualizza libro
        ControllerVisualizzaStudente controller = loader.getController();
        
        controller.setStudente(studenteSelezionato);
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief Carica la pagina di creazione prestito
    * @throws IOException
    */
    @FXML
    private void mostraPaginaPrestito() throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaPrestito.fxml"));
        Parent root = loader.load();

        ControllerPrestito controller = loader.getController();
        controller.setRoot(root);
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief carica la pagina di restituzione del prestito
    * @throws IOException
    */
    @FXML
    private void mostraPaginaRestituzione() throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaRestituzione.fxml"));
        Parent root = loader.load();

        ControllerRestituzione controller = loader.getController();
        Prestito prestitoSelezionato = tabellaPrestiti.getSelectionModel().getSelectedItem();
        controller.setCampiPrestito(prestitoSelezionato);
        controller.initService(service);

        App.setRoot(root);
    }
    
    /**
    * @brief Carica la pagina per visualizzare il prestito archiviato
    * @throws IOException
    */
    @FXML
    private void mostraVisualizzaPrestitoArchiviato() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/paginaVisualizzaPrestitoArchiviato.fxml"));
        Parent root = loader.load();

        ControllerVisualizzaPrestitoArchiviato controller = loader.getController();
        Prestito prestitoSelezionato = tabellaPrestitiArchiviati.getSelectionModel().getSelectedItem();
        controller.setCampiPrestito(prestitoSelezionato);
        controller.initService(service);
        
        App.setRoot(root);
    }
    
    /**
    * @brief Metodo helper per raggruppare l'inizializzazione delle tabelle
    */
    @FXML
    private void inizializzaCampiTabelle() {
        // ASSEGNAZIONE CAMPI TABELLA LIBRI
        colonnaCodice.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colonnaAutori.setCellValueFactory(new PropertyValueFactory<>("autoriString"));
        colonnaAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));
        colonnaCopieDisponibili.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));

        // ASSEGNAZIONE CAMPI TABELLA STUDENTI
        colonnaMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // ASSEGNAZIONE CAMPI TABELLA PRESTITI
        colonnaCodicePrestito.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaDataInizioPrestito.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
        colonnaDataFinePrestito.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
        colonnaStudentePrestito.setCellValueFactory(new PropertyValueFactory<>("studenteString"));
        colonnaLibroPrestito.setCellValueFactory(new PropertyValueFactory<>("libroString"));

        // ASSEGNAZIONE CAMPI TABELLA PRESTITI ARCHIVIATI
        colonnaCodicePrestitoArchiviato.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colonnaDataInizioPrestitoArchiviato.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
        colonnaDataFinePrestitoArchiviato.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
        colonnaStudentePrestitoArchiviato.setCellValueFactory(new PropertyValueFactory<>("studenteString"));
        colonnaLibroPrestitoArchiviato.setCellValueFactory(new PropertyValueFactory<>("libroString"));

        tabellaPrestiti.setRowFactory(tv -> new TableRow<Prestito>() {
            @Override
            protected void updateItem(Prestito prestito, boolean vuoto) {
                super.updateItem(prestito, vuoto);

                if (vuoto || prestito == null) {
                    setStyle("");
                    return;
                }
                LocalDate oggi = LocalDate.now();
                LocalDate fine = prestito.getDataFine();
                if (oggi.isEqual(fine)) {
                    // prestito sta scadendo, setta arancione
                    setStyle("-fx-background-color: rgba(255,165,0,0.4);");
                } else if (oggi.isAfter(fine)) {
                    // prestito scaduto, setta rosso
                    setStyle("-fx-background-color: rgba(255,0,0,0.4);");
                } else {
                    setStyle("");
                }
            }
        });
    }
    
    /**
    * @brief Metodo helper per inizializzare i binding dei bottoni della pagina principale
    */
    @FXML
    private void inizializzaBottoni() {

        // abilita bottoni visualizza e restituzione solo quando una riga è selezionata
        bottoneVisualizzaLibro.disableProperty().bind(tabellaLibri.getSelectionModel().selectedItemProperty().isNull());
        bottoneVisualizzaStudente.disableProperty().bind(tabellaStudenti.getSelectionModel().selectedItemProperty().isNull());
        bottoneRestituzionePrestito.disableProperty().bind(tabellaPrestiti.getSelectionModel().selectedItemProperty().isNull());
        bottoneVisualizzaPrestitoArchiviato.disableProperty().bind(tabellaPrestitiArchiviati.getSelectionModel().selectedItemProperty().isNull());
    }
    
    /**
    * @brief Metodo generico per impostare il comportamento di ricerca per ogni tabella
    */
    @FXML
    private <T> void setupFiltroRicerca(ObservableList<T> listaOriginale, TableView<T> tabella, TextField campoRicerca, Function<T, String> filtroRicerca) {

        FilteredList<T> listaFiltrata = new FilteredList<>(listaOriginale, t -> true);

        // wrappo la listaFiltrata (che serve per filtrare la ricerca) con una sortedList
        // serve a permettere l'ordinamento crescente e decrescente delle colonne
        SortedList<T> listaOrdinata = new SortedList<>(listaFiltrata);
        listaOrdinata.comparatorProperty().bind(tabella.comparatorProperty());

        tabella.setItems(listaOrdinata);

        listaFiltrata.predicateProperty().bind(
                Bindings.createObjectBinding(() -> elemento -> {

            String search = campoRicerca.getText();
            if (search == null || search.isEmpty()) {
                return true;
            }

            search = search.toLowerCase();

            String testo = filtroRicerca.apply(elemento);
            return testo.toLowerCase().contains(search);

        }, campoRicerca.textProperty())
        );
    }
    
    /**
    * @brief Cambia tema chiaro/scuro
    */
    @FXML
    private void cambiaTema() {
        Scene scene = tabPane.getScene();
        scene.getStylesheets().clear();
        temaScuro = !temaScuro;

        if (temaScuro) {
            scene.getStylesheets().add(getClass().getResource("/stili/moderno-scuro.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("/stili/moderno-chiaro.css").toExternalForm());
        }
    }
}
