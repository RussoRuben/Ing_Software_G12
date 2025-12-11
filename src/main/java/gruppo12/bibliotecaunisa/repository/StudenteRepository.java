package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Studente;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @brief Repository utilizzata per gestire gli oggetti Studente 
 * Implementa l'interfaccia Repository<String, Studente>
 * @class StudenteRepository
 */
public class StudenteRepository implements Repository<String, Studente> {

    private ObservableList<Studente> studenti = FXCollections.observableArrayList();
    private Map<String, Studente> studentiMap = new HashMap<>();

    /**
     * @brief Aggiunge uno Studente al Repository
     * @param studente Oggetto che deve essere aggiunto 
     */
    @Override
    public void add(Studente studente) {

    }

    /**
     * @brief Rimuove uno Studente dal Repository
     * @param studente Oggetto che deve essere rimosso 
     * @return true se lo Studente è stato rimosso con successo,false altrimenti
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
    */
    @Override
    public boolean remove(Studente studente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @brief Restituisce una lista osservabile degli studenti presenti nel Repository
     * @return una ObservableList<Studente> che contiene tutti gli studenti 
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public ObservableList<Studente> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @brief Cerca uno studente attraverso la matricola 
     * @param matricola La matricola dello studente che deve essere cercato
     * @return L'oggetto Studente corrispondente alla sua matricola,o null se non trovato
     * @throws UnsupportedOperationException Se l'operazione non è ancora realizzata 
     */
    @Override
    public Studente findByKey(String matricola) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @brief Sostituisce la collezione degli studenti con una lista di nuovi dati 
     * @param nuoviDati La List<Studente> che contiene i nuovi dati
     */
    @Override
    public void replaceAll(List<Studente> nuoviDati) {

    }
}