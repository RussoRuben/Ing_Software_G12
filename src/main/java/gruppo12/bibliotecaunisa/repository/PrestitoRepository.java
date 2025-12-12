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
        // assegno il prestito alla lista prestiti dello studente
        prestito.getStudente().aggiungiListaPrestiti(prestito);

        prestiti.add(prestito);
        prestitiMap.put(prestito.getCodice(), prestito);

    }
    /**
     * @brief Rimuove un prestito dalla collezione dei prestiti attivi 
     * @param prestito Oggetto che deve essere rimosso 
     * @return true se il prestito è stato rimosso con successo,false altrimenti
    */
    @Override
    public boolean remove(Prestito prestito) {
        if (prestiti.remove(prestito)) {
            prestitiMap.remove(prestito.getCodice());
            return true;
        }
        return false;
    }
    
    /**
     * @brief Restituisce una lista osservabile di prestiti attivi presenti nel Repository
     * @return una ObservableList<Prestito> che contiene tutti i prestiti attivi
     */
    @Override
    public ObservableList<Prestito> getAll() {
        return prestiti;
    }
    
    /**
     * @brief Cerca un prestito attivo attraverso il suo codice CUIP
     * @param codice Il codice CUIP del prestito che deve essere cercato
     * @return L'oggetto Prestito corrispondente al suo codice CUIP,o null se non trovato
     */
    @Override
    public Prestito findByKey(Long codice) {
        return prestitiMap.get(codice);
    }
    
    /**
     * @brief Sostituisce la collezione dei prestiti attivi con una lista di nuovi dati 
     * @param nuoviDati La List<Prestito> che contiene i nuovi dati
     */
    
    @Override
    public void replaceAll(List<Prestito> nuoviDati) {
        prestiti.setAll(nuoviDati);
        prestitiMap.clear();
        for (Prestito p : nuoviDati) {
            prestitiMap.put(p.getCodice(), p);
        }
    }
    /**
     * @brief Aggiunge un prestito alla collezione dei prestiti archiviati
     * @param prestito Il prestito che deve essere archiviato
     */

    public void addArchiviato(Prestito prestito) {
        prestitiArchiviati.add(prestito);
        prestitiArchiviatiMap.put(prestito.getCodice(), prestito);
    }
    
    /**
     * @brief Rimuove un prestito dalla collezione dei prestiti archiviati
     * @param prestito Il prestito che deve essere rimosso dall'archivio
     */
    public void removeArchiviato(Prestito prestito) {
        prestitiArchiviati.remove(prestito);
        prestitiArchiviatiMap.remove(prestito.getCodice());
    }
    
    /**
     * @brief Restituisce una lista osservabile di prestiti archiviati
     * @return una ObservableList<Prestito> che contiene tutti i prestiti archiviti
     */
    public ObservableList<Prestito> getAllArchiviati() {
        return prestitiArchiviati;
    }
    
    /**
     * @brief Cerca un prestito archiviato attraverso il suo codice CUIP
     * @param codice Il codice CUIP del prestito archiviato che deve essere cercato
     * @return L'oggetto Prestito archiviato corrispondente al suo codice CUIP,o null se non trovato
     */
    public Prestito findArchiviatoByKey(Long codice) {
        return prestitiArchiviatiMap.get(codice);
    }
    
    /**
     * @brief Sostituisce la collezione dei prestiti archiviati con una lista di nuovi dati 
     * @param nuoviDati La List<Prestito> che contiene i nuovi dati
     */
    public void replaceAllArchiviati(List<Prestito> nuoviDati) {
        prestitiArchiviati.setAll(nuoviDati);
        prestitiArchiviatiMap.clear();
        for (Prestito p : nuoviDati) {
            prestitiArchiviatiMap.put(p.getCodice(), p);
        }
    }
}
