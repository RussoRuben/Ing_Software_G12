package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.service.BibliotecaService;
import java.io.IOException;
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * @brief Controller per la schermata di ricerca dei libri
 * @class ControllerRicercaLibro
*/
public class ControllerRicercaLibro implements ControllerService {

    private BibliotecaService service;
    private ControllerPrestito chiamante;

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
    @FXML
    private Label labelAvvisoRicerca;
    @FXML
    private TextField cercaLibro;

      /**
     * @brief Inizializza il servizio e imposta il filtro di ricerca
     * @param service servizio della biblioteca utilizzato dal controller
     */
    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
        setupFiltroRicerca(service.getLibri(), tabellaLibri, cercaLibro, libro -> libro.getTitolo() + " " + libro.getCodice() + " " + libro.getAutori());

    }
    
    /**
     * @brief Metodo chiamato automaticamente all'inizializzazione del controller
     * Imposta i value factory delle colonne della tabella dei libri.
     */
    
    @FXML
    public void initialize() {
        // ASSEGNAZIONE CAMPI TABELLA LIBRI
        colonnaCodice.setCellValueFactory(new PropertyValueFactory<Libro, String>("codice"));
        colonnaTitolo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titolo"));
        colonnaAutori.setCellValueFactory(new PropertyValueFactory<Libro, String>("autoriString"));
        colonnaAnno.setCellValueFactory(new PropertyValueFactory<Libro, String>("anno"));
        colonnaCopieDisponibili.setCellValueFactory(new PropertyValueFactory<Libro, String>("copieDisponibili"));
    }

      /**
     * @brief Imposta il controller chiamante.
     * @param chiamante controller del prestito che ha aperto la schermata
     */
    public void setControllerChiamante(ControllerPrestito chiamante) {
        this.chiamante = chiamante;
    }

     /**
     * @brief Conferma la selezione del libro.
     * @throws IOException 
     */
    
    @FXML
    private void conferma() throws IOException {
        Libro libroSel = tabellaLibri.getSelectionModel().getSelectedItem();
        if (libroSel == null) {
            labelAvvisoRicerca.setText("Seleziona un libro.");
            return;
        } else if (libroSel.getCopieDisponibili() < 1) {
            labelAvvisoRicerca.setText("Libro non disponibile.");
            return;
        }

        chiamante.setLibroSelezionato(libroSel);

        App.setRoot(chiamante.getRoot());
    }

     /**
     * @brief Annulla l'operazione e ritorna alla schermata precedente
     * @throws IOException 
     */
    
    @FXML
    private void annulla() throws IOException {

        App.setRoot(chiamante.getRoot());
    }

     /**
     * @brief Imposta un filtro di ricerca testuale su una TableView
     * @param <T> tipo degli elementi della tabella
     * @param listaOriginale lista originale degli elementi
     * @param tabella TableView su cui applicare il filtro
     * @param campoRicerca campo di testo usato per la ricerca
     * @param filtroRicerca funzione che restituisce la stringa da confrontare
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
}
