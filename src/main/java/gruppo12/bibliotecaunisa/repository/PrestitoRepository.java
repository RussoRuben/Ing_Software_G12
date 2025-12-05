package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Prestito;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrestitoRepository implements Repository<Long, Prestito> {

    private final ObservableList<Prestito> prestiti = FXCollections.observableArrayList();
    private final Map<Long, Prestito> prestitiMap = new HashMap<>();

    private final ObservableList<Prestito> prestitiArchiviati = FXCollections.observableArrayList();
    private final Map<Long, Prestito> prestitiArchiviatiMap = new HashMap<>();

    @Override
    public void add(Prestito prestito) {

    }

    @Override
    public boolean remove(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ObservableList<Prestito> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Prestito findByKey(Long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replaceAll(List<Prestito> nuoviDati) {

    }

    public void addArchiviato(Prestito prestito) {

    }

    public void removeArchiviato(Prestito prestito) {

    }

    public ObservableList<Prestito> getAllArchiviati() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Prestito findArchiviatoByKey(Long codice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void replaceAllArchiviati(List<Prestito> nuoviDati) {

    }
}
