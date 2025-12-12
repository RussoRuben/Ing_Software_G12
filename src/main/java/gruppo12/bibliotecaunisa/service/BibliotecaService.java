package gruppo12.bibliotecaunisa.service;

import gruppo12.bibliotecaunisa.model.*;
import gruppo12.bibliotecaunisa.data.BibliotecaData;
import java.time.LocalDate;
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
    
    // Metodi repo libro

    /**
     * @brief Aggiunge un nuovo libro al sistema
     * @param libro Oggetto di tipo Libro
     */
    public void aggiungiLibro(Libro libro) {
        data.getLibroRepo().add(libro);
        data.salvaStato();
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
        Libro libro = data.getLibroRepo().findByKey(codice);
        
        if(libro == null) return;
        
        libro.setTitolo(titolo);
        libro.setAutori(autori);
        libro.setEditore(editore);
        libro.setNumeroEdizione(numeroEdizione);
        libro.setAnno(anno);
        libro.setCopieDisponibili(copie);
        
        data.salvaStato();

    }
    
    /**
     * @brief Rimuovi un libro dal sistema
     * @param libro Oggetto di tipo Libro
     */
    public void rimuoviLibro(Libro libro) {
        boolean haPrestito = data.getPrestitoRepo().getAll().stream().anyMatch(p -> p.getLibro().getCodice().equals(libro.getCodice()));
        
        data.getLibroRepo().remove(libro);
        
        data.salvaStato();

    }
    
    /**
     * @return Restituisce una lista osservabile dei libri
     */
    public ObservableList<Libro> getLibri() {
         return data.getLibroRepo().getAll();
    }
    
    /**
    * @brief Cerca un libro tramite codice ISBN
    * @param codice 
    * @return Restituisce il libro relativo al codice 
    */
    public Libro trovaLibro(String codice) {
        return data.getLibroRepo().findByKey(codice);
    }

    
    // Metodi repo studente

    /**
     * @brief Aggiunge uno studente al sistema
     * @param studente oggetto di tipo Studente
     */
    public void aggiungiStudente(Studente studente) {
        data.getStudenteRepo().add(studente);
        data.salvaStato();
    }

    /**
     * @brief Modifica determinati attributi di Studente
     * @param matricola
     * @param nome
     * @param cognome
     * @param email 
     */
    public void modificaStudente(String matricola, String nome, String cognome, String email) {
        Studente studente = data.getStudenteRepo().findByKey(matricola);
        
        if(studente == null) return;
        
        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setEmail(email);
        
        data.salvaStato();

    }

    /**
     * @brief Rimuove uno studente dal sistema
     * @param studente Oggetto di tipo Studente
     */
    public void rimuoviStudente(Studente studente) {
        data.getStudenteRepo().remove(studente);
        data.salvaStato();

    }

    /**
     * @return Restituisce una lista osservabile di studenti
     */
    public ObservableList<Studente> getStudenti() {
        return data.getStudenteRepo().getAll();

    }
    
    /**
     * @brief Cerca studente in base alla Matricola
     * @param matricola 
     * @return Restituisce lo studente relativo alla matricola
     */
    public Studente trovaStudente(String matricola) {
        return data.getStudenteRepo().findByKey(matricola);
    }

    /**
     * @brief Aggiungi un prestito al sistema 
     * @param prestito oggetto di tipo Prestito
     */
    public void aggiungiPrestito(Prestito prestito) {
        int copie = prestito.getLibro().getCopieDisponibili();
        prestito.getLibro().setCopieDisponibili(--copie);
        
        prestito.getStudente().aggiungiListaPrestiti(prestito);

        data.getPrestitoRepo().add(prestito);
        
        data.salvaStato();

    }

    /**
     * @brief Segna un prestito come restituito
     * @param prestito 
     */
    public void restituisciPrestito(Prestito prestito) {
        int copie = prestito.getLibro().getCopieDisponibili();
        prestito.getLibro().setCopieDisponibili(copie+1);
        
        data.getPrestitoRepo().remove(prestito);
        
        prestito.setDataFine(LocalDate.now());
        data.getPrestitoRepo().addArchiviato(prestito);
        
        prestito.getStudente().rimuoviListaPrestiti(prestito);
        
        data.salvaStato();

    }

    /**
     * @return Restituisce una lista osservabile dei prestiti
     */
    public ObservableList<Prestito> getPrestiti() {
         return data.getPrestitoRepo().getAll();

    }
    /**
     * 
     * @return Restituisce una lista osservabile dei prestiti archiviati
     */
    public ObservableList<Prestito> getPrestitiArchiviati() {
         return data.getPrestitoRepo().getAllArchiviati();

    }
    /**
     * @brief Cerca prestito in base al codice
     * @param codice univoco del Prestito
     * @return Restituisce il prestito corrispondente al codice
     */
    public Prestito trovaPrestito(long codice) {
        return data.getPrestitoRepo().findByKey(codice);

    }
    /**
     * @brief Cerca un prestito archiviato in base al codice
     * @param codice univoco del Prestito
     * @return Restituisce il prestito archiviato corrispondente al codice
     */
    public Prestito trovaPrestitoArchiviato(long codice) {
        return data.getPrestitoRepo().findArchiviatoByKey(codice);

    }

}
