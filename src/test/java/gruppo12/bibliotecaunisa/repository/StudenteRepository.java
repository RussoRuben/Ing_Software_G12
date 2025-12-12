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
        Studente esistente = studentiMap.get(studente.getMatricola());
        if (esistente == null) {
            studenti.add(studente);
            studentiMap.put(studente.getMatricola(), studente);
        } else {
            esistente.setNome(studente.getNome());
            esistente.setCognome(studente.getCognome());
            esistente.setEmail(studente.getEmail());
            esistente.setListaPrestiti(studente.getListaPrestiti());
        }

    }

    /**
     * @brief Rimuove uno Studente dal Repository
     * @param studente Oggetto che deve essere rimosso 
     * @return true se lo Studente è stato rimosso con successo,false altrimenti
    */
    @Override
    public boolean remove(Studente studente) {
        if (studenti.remove(studente)) {
            studentiMap.remove(studente.getMatricola());
            return true;
        }
        return false;
    }

    /**
     * @brief Restituisce una lista osservabile degli studenti presenti nel Repository
     * @return una ObservableList<Studente> che contiene tutti gli studenti 
     */
    @Override
    public ObservableList<Studente> getAll() {
        return studenti;
    }

    /**
     * @brief Cerca uno studente attraverso la matricola 
     * @param matricola La matricola dello studente che deve essere cercato
     * @return L'oggetto Studente corrispondente alla sua matricola,o null se non trovato
     */
    @Override
    public Studente findByKey(String matricola) {
        return studentiMap.get(matricola);
    }

    /**
     * @brief Sostituisce la collezione degli studenti con una lista di nuovi dati 
     * @param nuoviDati La List<Studente> che contiene i nuovi dati
     */
    @Override
    public void replaceAll(List<Studente> nuoviDati) {
        studenti.setAll(nuoviDati);
        studentiMap.clear();
        for (Studente s : nuoviDati) {
            studentiMap.put(s.getMatricola(), s);
        }
    }
}
