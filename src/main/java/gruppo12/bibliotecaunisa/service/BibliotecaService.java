package gruppo12.bibliotecaunisa.service;

import gruppo12.bibliotecaunisa.data.BibliotecaData;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import java.util.List;
import javafx.collections.ObservableList;


/**
 * @brief Gestione delle operazioni su Libri, Studenti e Prestiti
 * @class BibliotecaService 
 */
public class BibliotecaService {

    private final BibliotecaData data;

    /**
     * @brief Costruttore
     * @param data Oggetto di tipo BibliotecaData contenente i repository
     */
    public BibliotecaService(BibliotecaData data) {
        this.data = data;
    }
    
    /**
     * @brief Aggiunge un nuovo libro al sistema
     * @param libro Oggetto di tipo Libro
     */
    public void aggiungiLibro(Libro libro) {

    }

    /**
     * @brief Modifica determinati attributi del libro
     * @param codice 
     * @param titolo
     * @param autori
     * @param editore
     * @param numeroEdizione
     * @param anno
     * @param copie 
     */
    public void modificaLibro(String codice, String titolo, List<String> autori, String editore, int numeroEdizione, int anno, int copie) {

    }
    
    /**
     * @brief Rimuovi un libro dal sistema
     * @param libro Oggetto di tipo Libro
     */
    public void rimuoviLibro(Libro libro) {

    }
    
    /**
     * @return Restituisce una lista osservabile dei libri
     */
    public ObservableList<Libro> getLibri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
    * @brief Cerca un libro tramite codice ISBN
    * @param codice 
    * @return Restituisce il libro relativo al codice 
    */
    public Libro trovaLibro(String codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @brief Aggiunge uno studente al sistema
     * @param studente oggetto di tipo Studente
     */
    public void aggiungiStudente(Studente studente) {

    }

    /**
     * @brief Modifica determinati attributi di Studente
     * @param matricola
     * @param nome
     * @param cognome
     * @param email 
     */
    public void modificaStudente(String matricola, String nome, String cognome, String email) {

    }

    /**
     * @brief Rimuove uno studente dal sistema
     * @param studente Oggetto di tipo Studente
     */
    public void rimuoviStudente(Studente studente) {

    }

    /**
     * @return Restituisce una lista osservabile di studenti
     */
    public ObservableList<Studente> getStudenti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Cerca studente in base alla Matricola
     * @param matricola 
     * @return Restituisce lo studente relativo alla matricola
     */
    public Studente trovaStudente(String matricola) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @brief Aggiungi un prestito al sistema 
     * @param prestito oggetto di tipo Prestito
     */
    public void aggiungiPrestito(Prestito prestito) {

    }

    /**
     * @brief Segna un prestito come restituito
     * @param prestito 
     */
    public void restituisciPrestito(Prestito prestito) {

    }

    /**
     * @return Restituisce una lista osservabile dei prestiti
     */
    public ObservableList<Prestito> getPrestiti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * 
     * @return Restituisce una lista osservabile dei prestiti archiviati
     */
    public ObservableList<Prestito> getPrestitiArchiviati() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * @brief Cerca prestito in base al codice
     * @param codice univoco del Prestito
     * @return Restituisce il prestito corrispondente al codice
     */
    public Prestito trovaPrestito(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * @brief Cerca un prestito archiviato in base al codice
     * @param codice univoco del Prestito
     * @return Restituisce il prestito archiviato corrispondente al codice
     */
    public Prestito trovaPrestitoArchiviato(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
