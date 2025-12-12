package gruppo12.bibliotecaunisa.model;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Studente
 * @brief Modello dati che rappresenta uno studente
 */
public class Studente implements Serializable{
   
    private String matricola;
    private String nome;
    private String cognome;
    private String email;
    
    /**
     * @brief Lista dei prestiti attivi dello studente
     */
    private transient ObservableList<Prestito> listaPrestiti;
    
    
    
     /**
     * @brief Costruttore
     * @param matricola Matricola dello studente
     * @param nome Nome dello studente
     * @param cognome Cognome dello studente
     * @param email Email dello studente
     */
    public Studente(String matricola, String nome, String cognome, String email) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.listaPrestiti = FXCollections.observableArrayList();
    }

    
    /**
     * @throws java.io.IOException
     * @brief Serializza l'oggetto su stream di Output
     * @param out Stream di Output
     */
    private void writeObject(ObjectOutputStream out) throws IOException{
        
        out.defaultWriteObject();
        out.writeObject(new ArrayList<>(listaPrestiti));
    }
    
    /**
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @brief Deserializza l'oggetto in stream di Input
     * @param in Stream di Input
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        
        in.defaultReadObject();
        List<Prestito> lista = (List<Prestito>) in.readObject();
        listaPrestiti = FXCollections.observableArrayList(lista);
        
    }
    
    /**
     * @return Restituisce la matricola
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * @return Restituisce il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Restituisce il cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @return Restituisce l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Restituisce la lista dei prestiti dello studente
     */
    public ObservableList<Prestito> getListaPrestiti() {
        return listaPrestiti;
    }
    
    /**
     * @return Restituisce la lista dei titoli dei libri in prestito formattata come String
     */
    public String getListaPrestitiString() {
        
        if (listaPrestiti == null || listaPrestiti.isEmpty()) {
            return "Nessun Libro";
        }

        StringBuffer sb = new StringBuffer();

        for (Prestito prestito : listaPrestiti) {
            sb.append(prestito.getLibro().getTitolo()).append(", ");
        }

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    /**
     * @brief Imposta la matricola
     * @param matricola  
     */
    public void setMatricola(String matricola) {
        this.matricola=matricola;
    }

    /**
     * @brief Imposta il nome
     * @param nome  
     */
    public void setNome(String nome) {
        this.nome=nome;
    }

    /**
     * @brief Imposta il cognome
     * @param cognome 
     */
    public void setCognome(String cognome) {
        this.cognome=cognome;
    }
    
    /**
     * @brief Imposta l'email
     * @param email  
     */
    public void setEmail(String email) {
        this.email=email;
    }

    /** 
     * @brief Imposta la lista dei prestiti
     * @param listaPrestiti 
     */
    public void setListaPrestiti(ObservableList<Prestito> listaPrestiti) {
        this.listaPrestiti = listaPrestiti;
    }

    /**
     * @brief Aggiunge un prestito alla lista prestiti dello studente
     * @pre listaPrestiti.size() < 3  lo studente non può avere più di 3 libri in prestito
     * @param prestito Oggetto Prestito
     * @return true se il prestito è stato aggiunto con successo
     */
    public boolean aggiungiListaPrestiti(Prestito prestito) {
        if(listaPrestiti.size() < 3){
            listaPrestiti.add(prestito);
            return true;
        }
        else return false;
    }

    /**
     * @brief Rimuove un prestito alla lista prestiti dello studente
     * @param prestito Oggetto Prestito
     * @return listaPrestiti con il prestito rimosso
     */
    public boolean rimuoviListaPrestiti(Prestito prestito) {
        return listaPrestiti.remove(prestito);
    }

    /**
     * @brief Restituisce una stringa con tutte le informazioni dello studente
     * @return Stringa con info studente
     */
    @Override
    public String toString() {
        return matricola + ": " + nome + " " + cognome;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Studente s = (Studente) o;
        return matricola.equals(s.matricola);
    }

    @Override
    public int hashCode() {
        return matricola.hashCode();
    }
}
