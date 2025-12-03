package gruppo12.bibliotecaunisa.model;

public class BibliotecaData {

    private ObservableList<Libro> libri;

    private ObservableList<Studente> studenti;

    private ObservableList<Prestito> prestiti;

    private ObservableList<Prestito> prestitiArchiviati;

    public void salvaStato() {
    }

    public void caricaStato() {
    }

    public ObservableList<Libro> getLibri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiLibro(Libro libro) {
    }

    public Libro trovaLibroCodice(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rimuoviLibro(Libro libro) {
    }

    public ObservableList<Studente> getStudenti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiStudente(Studente studente) {
    }

    public Studente trovaStudenteMatricola(long matricola) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rimuoviStudente(Studente studente) {
    }

    public ObservableList<Prestito> getPrestiti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiPrestito(Prestito prestito) {
    }

    public Prestito trovaPrestitoCodice(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rimuoviPrestito(Prestito prestito) {
    }

    public ObservableList<Prestito> getPrestitiArchiviati() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void aggiungiPrestitoArchiviato(Prestito prestito) {
    }

    public Prestito trovaPrestitoArchiviatoCodice(long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rimuoviPrestitoArchiviato(Prestito prestito) {
    }

    public void restituisciPrestito(Prestito prestito) {
    }

    public void aggiornaNumPrestiti() {
    }
}
