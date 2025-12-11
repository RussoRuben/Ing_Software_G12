package gruppo12.bibliotecaunisa.repository;

import java.util.List;
import javafx.collections.ObservableList;

/**
 * @brief Intefaccia che si occupa delle gestione dei dati attraverso operazioni standard
 * @param <K> Tipo della chiave per identificare l'oggetto
 * @param <T> Tipo dell'oggetto
 * @class Repository
 */
public interface Repository<K, T> {
    
    /**
     * @brief Aggiunge un entità al Repository
     * @param entity L'entità che deve essere aggiunta 
     */
    void add(T entity);
    
    /**
     * @brief Rimuove un entità dal Repository
     * @param entity L'entità che deve essere rimossa
     * @return true se l'entità è stato rimossa con successo,false altrimenti
    */
    boolean remove(T entity);
    
    /**
     * @brief Restituisce una lista osservabile delle entità presenti nel Repository
     * @return una ObservableList<T> di tutte le entità 
     */
    ObservableList<T> getAll();
    
    /**
     * @brief Cerca un entità attraverso la sua chiave
     * @param chiave La chiave dell'entità che deve essere cercata
     * @return L'entità corrispondente alla sua chiave,o null se non trovato
     */
    
    T findByKey(K chiave);
    
    /**
     * @brief Sostituisci la collezione di entità con una lista di nuovi dati 
     * @param nuoviDati La List<T> che contiene i nuovi dati
     */
    void replaceAll(List<T> nuoviDati);  
}