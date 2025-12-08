package gruppo12.bibliotecaunisa.model;

import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;

/**
 * @brief Modello dati che rappresenta un Libro
 * @class Libro
 */
public class Libro implements Serializable{

    private String codice;
    private String titolo;
    private ObservableList<String> autori;
    private String editore;
    private int numeroEdizione;
    private int anno;
    private int copieDisponibili;
    
    /**
     * @brief Costruttore
     * @param codice Codice ISBN del libro
     * @param titolo Titolo del libro
     * @param autori Autore o autori del libro
     * @param editore Editore del libro
     * @param numeroEdizione Numero di edizione del libro
     * @param anno Anno di pubblicazione del libro
     * @param copieDisponibili Numero di copie disponibili in Biblioteca del libro
     */
    public Libro(String codice, String titolo, List<String> autori, String editore, int numeroEdizione, int anno, int copieDisponibili) {
        this.codice = codice;
        this.titolo = titolo;
        this.editore = editore;
        this.numeroEdizione = numeroEdizione;
        this.autori = FXCollections.observableArrayList(autori);
        this.anno = anno;
        this.copieDisponibili = copieDisponibili;
    }

    /**
     * @throws java.io.IOException
     * @brief Serializza l'oggetto su stream di Output
     * @param out Stream di Output
     */
    public void writeObject(ObjectOutputStream out) throws IOException {
        
        out.defaultWriteObject();
        out.writeObject(new ArrayList<>(autori));
    }
    
     /**
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @brief Deserializza l'oggetto in stream di Input
     * @param in Stream di Input
     */
    public void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
        
        in.defaultReadObject();
        List<String> lista = (List<String>) in.readObject();
        autori = FXCollections.observableArrayList(lista);
    }

    /**
     * @return Restituisce il codice ISBN del libro
     */
    public String getCodice() {
        return codice;
    }

    /**
     * @return Restituisce il titolo del libro
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @return Restituisce l'editore del libro
     */
    public String getEditore() {
        return editore;
    }

    /**
     * @return Restituisce il numero di edizione del libro
     */
    public int getNumeroEdizione() {
        return numeroEdizione;
    }

    /**
     * @return Restituisce l'elenco degli autori del libro
     */
    public ObservableList<String> getAutori() {
        return autori;
    }

    /**
     * @return Restituisce l'elenco degli autori del libro formattato come stringa
     */
    public String getAutoriString() {
        
        if(autori.isEmpty()) return "Nessun autore";
        
        StringBuffer sb = new StringBuffer();
        
        for(String autore : autori) {
            sb.append(autore).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    /**
     * @return  Restituisce l'anno di pubblicazione del libro
     */
    public int getAnno() {
      return anno;
    }

    /**
     * @return Restituisce il numero di copie disponibili in biblioteca di quel libro
     */
    public int getCopieDisponibili() {
      return copieDisponibili;
    }

    /**
     * @brief Imposta il codice ISBN del libro
     * @param codice ISBN
     */
    public void setCodice(String codice) {
        this.codice=codice;
    }

    /**
     * @brief Imposta il titolo del libro
     * @param titolo 
     */
    public void setTitolo(String titolo) {
        this.titolo=titolo;
    }

    /**
     * @brief Imposta editore del libro
     * @param editore 
     */
    public void setEditore(String editore) {
        this.editore=editore;
    }

    /**
     * @brief Imposta numero di edizione del libro
     * @param numeroEdizione 
     */
    public void setNumeroEdizione(int numeroEdizione) {
        this.numeroEdizione=numeroEdizione;
    }
    
    /**
     * @brief Imposta lista autori del libro
     * @param autori 
     */
    public void setAutori(List<String> autori) {
         this.autori.setAll(autori);
    }
    
    /**
     * @brief Imposta anno di pubblicazione del libro
     * @param anno 
     */
    public void setAnno(int anno) {
        this.anno=anno;
    }

    public void setCopieDisponibili(int copie) {
        this.copieDisponibili=copie;
    }

    /**
     * @brief Restituisce una stringa con tutte le informazioni del libro
     * @return Stringa con info libro
     */
    @Override
    public String toString() {
         return codice + ": " + titolo;
    }
}
