package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Prestito;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Repository utilizzata per gestire gli oggetti Prestito
 * Implementa l'interfaccia Repository<Long, Prestito>
 * @class PrestitoRepository
 */
public class PrestitoRepository implements Repository<Long, Prestito> {

    private final ObservableList<Prestito> prestiti = FXCollections.observableArrayList();
    private final Map<Long, Prestito> prestitiMap = new HashMap<>();

    private final ObservableList<Prestito> prestitiArchiviati = FXCollections.observableArrayList();
    private final Map<Long, Prestito> prestitiArchiviatiMap = new HashMap<>();
    
    /**
     * @brief Aggiunge un prestito alla collezione dei prestiti attivi 
     * @param prestito Il prestito che deve essere aggiunto
     */
    @Override
    public void add(Prestito prestito) {

    }
    /**
     * @brief Rimuove un prestito dalla collezione dei prestiti attivi 
     * @param prestito Oggetto che deve essere rimosso 
     * @return true se il prestito è stato rimosso con successo,false altrimenti
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
    */
    @Override
    public boolean remove(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Restituisce una lista osservabile di prestiti attivi presenti nel Repository
     * @return una ObservableList<Prestito> che contiene tutti i prestiti attivi
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public ObservableList<Prestito> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Cerca un prestito attivo attraverso il suo codice CUIP
     * @param codice Il codice CUIP del prestito che deve essere cercato
     * @return L'oggetto Prestito corrispondente al suo codice CUIP,o null se non trovato
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public Prestito findByKey(Long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Sostituisce la collezione dei prestiti attivi con una lista di nuovi dati 
     * @param nuoviDati La List<Prestito> che contiene i nuovi dati
     */
    
    @Override
    public void replaceAll(List<Prestito> nuoviDati) {
    
    }
    /**
     * @brief Aggiunge un prestito alla collezione dei prestiti archiviati
     * @param prestito Il prestito che deve essere archiviato
     */

    public void addArchiviato(Prestito prestito) {

    }
    
    /**
     * @brief Rimuove un prestito dalla collezione dei prestiti archiviati
     * @param prestito Il prestito che deve essere rimosso dall'archivio
     */
    public void removeArchiviato(Prestito prestito) {

    }
    
    /**
     * @brief Restituisce una lista osservabile di prestiti archiviati
     * @return una ObservableList<Prestito> che contiene tutti i prestiti archiviti
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    public ObservableList<Prestito> getAllArchiviati() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Cerca un prestito archiviato attraverso il suo codice CUIP
     * @param codice Il codice CUIP del prestito archiviato che deve essere cercato
     * @return L'oggetto Prestito archiviato corrispondente al suo codice CUIP,o null se non trovato
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    public Prestito findArchiviatoByKey(Long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @brief Sostituisce la collezione dei prestiti archiviati con una lista di nuovi dati 
     * @param nuoviDati La List<Prestito> che contiene i nuovi dati
     */
    public void replaceAllArchiviati(List<Prestito> nuoviDati) {

    }
}
