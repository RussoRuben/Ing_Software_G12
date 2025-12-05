package gruppo12.bibliotecaunisa.repository;

import java.util.List;
import javafx.collections.ObservableList;

public interface Repository<K, T> {
    
    void add(T entity);
    boolean remove(T entity);
    ObservableList<T> getAll();
    T findByKey(K chiave);
    void replaceAll(List<T> nuoviDati);  
}
