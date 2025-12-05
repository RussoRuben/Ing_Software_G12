package gruppo12.bibliotecaunisa.model;

import java.time.LocalDate;

public class Prestito {

    private long num;

    private long codice;

    private Studente studente;

    private Libro libro;

    private LocalDate dataInizio;

    private LocalDate dataFine;

    public void setNum(long nuovoNum) {
    }

    public long getNum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getCodice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Studente getStudente() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getStudenteString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Libro getLibro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLibroString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LocalDate getDataInizio() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LocalDate getDataFine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
