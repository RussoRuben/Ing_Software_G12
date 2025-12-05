package gruppo12.bibliotecaunisa.model;

import javafx.collections.ObservableList;
import java.io.*;
import javafx.collections.FXCollections;

public class Studente {

    private String matricola;

    private String nome;

    private String cognome;

    private String email;
    
    private ObservableList<Prestito> listaPrestiti;
    
    public Studente(String matricola, String nome, String cognome, String email) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.listaPrestiti = FXCollections.observableArrayList();
    }

    public void writeObject(ObjectOutputStream out) {
    }

    public void readObject(ObjectInputStream in) {
    }

    public long getMatricola() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCognome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<Prestito> getListaPrestiti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getListaPrestitiString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMatricola(long matricola) {
    }

    public void setNome(String nome) {
    }

    public void setCognome(String cognome) {
    }

    public void setEmail(String email) {
    }

    public void setListaPrestiti(ObservableList<Prestito> listaPrestiti) {
    }

    public boolean aggiungiListaPrestiti(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rimuoviListaPrestiti(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
