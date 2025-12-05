package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Studente;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudenteRepository implements Repository<String, Studente> {

    private ObservableList<Studente> studenti = FXCollections.observableArrayList();
    private Map<String, Studente> studentiMap = new HashMap<>();

    @Override
    public void add(Studente studente) {

    }

    @Override
    public boolean remove(Studente studente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ObservableList<Studente> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Studente findByKey(String matricola) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replaceAll(List<Studente> nuoviDati) {

    }
}
