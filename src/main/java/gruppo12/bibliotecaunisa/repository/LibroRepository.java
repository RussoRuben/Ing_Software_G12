package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Libro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Repository utilizzata per gestire gli oggetti Libro
 * Implementa l'interfaccia Repository<String, Libro>
 * @class LibroRepository
 */

public class LibroRepository implements Repository<String, Libro>{
    private final ObservableList<Libro> libri = FXCollections.observableArrayList();
    private final Map<String, Libro> libriMap = new HashMap<>();
    
    /**
     * @brief Aggiunge un libro al Repository
     * @param libro Oggetto che deve essere aggiunto 
     */
    @Override
    public void add(Libro libro) {
        
    }
    
    /**
     * @brief Rimuove un libro dal Repository
     * @param libro Oggetto che deve essere rimosso 
     * @return true se il libro è stato rimosso con successo,false altrimenti
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
    */
    @Override
    public boolean remove(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
     * @brief Restituisce una lista osservabile dei libri presenti nel Repository
     * @return una ObservableList<Libro> che contiene tutti i libri 
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public ObservableList<Libro> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");       
    }
    
    /**
     * @brief Cerca un libro attraverso il suo codice ISBN 
     * @param codice Il codice ISBN del libro che deve essere cercato
     * @return L'oggetto Libro corrispondente al suo codice,o null se non trovato
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public Libro findByKey(String codice) {
        throw new UnsupportedOperationException("Not supported yet.");      
    }
    
    /**
     * @brief Sostituisce la collezione dei libri con una lista di nuovi dati 
     * @param nuoviDati La List<Libro> che contiene i nuovi dati
     */
    @Override
    public void replaceAll(List<Libro> nuoviDati) {

    }
    
}
