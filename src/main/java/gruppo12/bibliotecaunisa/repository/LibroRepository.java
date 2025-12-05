package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Libro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibroRepository implements Repository<String, Libro>{
    private final ObservableList<Libro> libri = FXCollections.observableArrayList();
    private final Map<String, Libro> libriMap = new HashMap<>();
    
    @Override
    public void add(Libro libro) {
        
    }
    
    @Override
    public boolean remove(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public ObservableList<Libro> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");       
    }
    
    @Override
    public Libro findByKey(String codice) {
        throw new UnsupportedOperationException("Not supported yet.");      
    }
    
    @Override
    public void replaceAll(List<Libro> nuoviDati) {

    }
    
}
