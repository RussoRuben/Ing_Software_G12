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
        Libro esistente = libriMap.get(libro.getCodice());
        if (esistente == null) {
            libri.add(libro);
            libriMap.put(libro.getCodice(), libro);
        } else {
            esistente.setTitolo(libro.getTitolo());
            esistente.setAutori(libro.getAutori());
            esistente.setAnno(libro.getAnno());
            esistente.setCopieDisponibili(libro.getCopieDisponibili());
        }
    }
    
    /**
     * @brief Rimuove un libro dal Repository
     * @param libro Oggetto che deve essere rimosso 
     * @return true se il libro è stato rimosso con successo,false altrimenti 
    */
    @Override
    public boolean remove(Libro libro) {
        if (libri.remove(libro)) {
            libriMap.remove(libro.getCodice());
            return true;
        }
        return false;
    }
    
    
    /**
     * @brief Restituisce una lista osservabile dei libri presenti nel Repository
     * @return una ObservableList<Libro> che contiene tutti i libri 
     */
    @Override
    public ObservableList<Libro> getAll() {
        return libri;
    }
    
    /**
     * @brief Cerca un libro attraverso il suo codice ISBN 
     * @param codice Il codice ISBN del libro che deve essere cercato
     * @return L'oggetto Libro corrispondente al suo codice,o null se non trovato
     */
    @Override
    public Libro findByKey(String codice) {
        return libriMap.get(codice);      
    }
    
    /**
     * @brief Sostituisce la collezione dei libri con una lista di nuovi dati 
     * @param nuoviDati La List<Libro> che contiene i nuovi dati
     */
    @Override
    public void replaceAll(List<Libro> nuoviDati) {
        libri.setAll(nuoviDati);
        libriMap.clear();
        for (Libro l : nuoviDati) {
            libriMap.put(l.getCodice(), l);
        }
    }
    
}
