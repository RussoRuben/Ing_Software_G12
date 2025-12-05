package gruppo12.bibliotecaunisa.model;

import javafx.collections.ObservableList;
import java.io.*;
import java.util.List;
import javafx.collections.FXCollections;

public class Libro {

    private String codice;

    private String titolo;

    private ObservableList<String> autori;

    private String editore;

    private int numeroEdizione;

    private int anno;

    private int copieDisponibili;
    
    public Libro(String codice, String titolo, List<String> autori, String editore, int numeroEdizione, int anno, int copieDisponibili) {
        this.codice = codice;
        this.titolo = titolo;
        this.editore = editore;
        this.numeroEdizione = numeroEdizione;
        this.autori = FXCollections.observableArrayList(autori);
        this.anno = anno;
        this.copieDisponibili = copieDisponibili;
    }

    public void writeObject(ObjectOutputStream out) {
    }

    public void readObject(ObjectInputStream in) {
    }

    public long getCodice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTitolo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEditore() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getNumeroEdizione() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<String> getAutori() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAutoriString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getAnno() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getCopieDisponibili() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCodice(long codice) {
    }

    public void setTitolo(String titolo) {
    }

    public void setEditore(String editore) {
    }

    public void setNumeroEdizione(int numeroEdizione) {
    }

    public void setAutori(List<String> autori) {
    }

    public void setAnno(int anno) {
    }

    public void setCopieDisponibili(int copie) {
    }

    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
