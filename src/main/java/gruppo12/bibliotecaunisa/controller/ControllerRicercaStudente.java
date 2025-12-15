package gruppo12.bibliotecaunisa.controller;

import gruppo12.bibliotecaunisa.App;
import gruppo12.bibliotecaunisa.model.Studente;
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
 * @brief Controller per la schermata di ricerca degli studenti
 * @class ControllerRicercaStudente
 */

public class ControllerRicercaStudente implements ControllerService {

    private BibliotecaService service;
    private ControllerPrestito chiamante;

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
    @FXML
    private Label labelAvvisoRicerca;
    @FXML
    private TextField cercaStudente;

     /**
     * @brief Inizializza il servizio e imposta il filtro di ricerca
     * @param service servizio della biblioteca utilizzato dal controller
     */
    @Override
    public void initService(BibliotecaService service) {
        this.service = service;
        setupFiltroRicerca(service.getStudenti(), tabellaStudenti, cercaStudente, studente -> studente.getCognome() + " " + studente.getMatricola() + " " + studente.getNome());
    }

    /**
     * @brief Metodo chiamato automaticamente all'inizializzazione del controller
     * Imposta i value factory delle colonne della tabella degli studenti.
     */
    @FXML
    public void initialize() {
        // ASSEGNAZIONE CAMPI TABELLA STUDENTI
        colonnaMatricola.setCellValueFactory(new PropertyValueFactory<Studente, String>("matricola"));
        colonnaNome.setCellValueFactory(new PropertyValueFactory<Studente, String>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<Studente, String>("cognome"));
        colonnaEmail.setCellValueFactory(new PropertyValueFactory<Studente, String>("email"));

    }

     /**
     * @brief Imposta il controller chiamante.
     * @param chiamante controller del prestito che ha aperto la schermata
     */
    public void setControllerChiamante(ControllerPrestito chiamante) {
        this.chiamante = chiamante;
    }
    /**
     * @brief Conferma la selezione dello studente.
     * @throws IOException 
     */
    @FXML
    private void conferma() throws IOException {
        Studente studenteSel = tabellaStudenti.getSelectionModel().getSelectedItem();
        if (studenteSel == null) {
            labelAvvisoRicerca.setText("Seleziona uno studente.");
            return;
        } else if (studenteSel.getListaPrestiti().size() >= 3) {
            labelAvvisoRicerca.setText("Lo studente non può richiedere altri prestiti.");
            return;
        }

        chiamante.setStudenteSelezionato(studenteSel);

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
     * @brief Imposta un filtro di ricerca testuale
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
