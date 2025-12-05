package gruppo12.bibliotecaunisa.service;

import gruppo12.bibliotecaunisa.data.BibliotecaData;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import java.util.List;
import javafx.collections.ObservableList;

public class BibliotecaService {

    private final BibliotecaData data;

    public BibliotecaService(BibliotecaData data) {
        this.data = data;
    }

    public void aggiungiLibro(Libro libro) {

    }

    public void modificaLibro(String codice, String titolo, List<String> autori, String editore, int numeroEdizione, int copie) {

    }

    public void rimuoviLibro(Libro libro) {

    }

    public ObservableList<Libro> getLibri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Libro trovaLibro(String codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiStudente(Studente studente) {

    }

    public void modificaStudente(String matricola, String nome, String cognome, String email) {

    }

    public void rimuoviStudente(Studente studente) {

    }

    public ObservableList<Studente> getStudenti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Studente trovaStudente(String matricola) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiPrestito(Prestito prestito) {

    }

    public void restituisciPrestito(Prestito prestito) {

    }

    public ObservableList<Prestito> getPrestiti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<Prestito> getPrestitiArchiviati() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Prestito trovaPrestito(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Prestito trovaPrestitoArchiviato(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
