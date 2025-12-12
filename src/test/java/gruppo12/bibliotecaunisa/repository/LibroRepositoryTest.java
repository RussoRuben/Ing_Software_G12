
package gruppo12.bibliotecaunisa.repository;


import gruppo12.bibliotecaunisa.model.Libro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import javafx.collections.ObservableList;

public class LibroRepositoryTest {
    
    private LibroRepository rep;
    private Libro l1;
    private Libro l2;
    
    @BeforeEach
    void setUp(){
        rep = new LibroRepository();
        l1 = new Libro("9781492856347", "La Divina Commedia", Arrays.asList("Dante Alighieri"), "Mondadori", 3, 1321, 10);
        l2 = new Libro("9780376491823", "Il Giardino delle Ombre Silenziose", Arrays.asList("Marco D’Alessandro"), "Grifone Letterario", 1, 2022, 7);
    }
     /**
     * @test verifica che il libro venga aggiunto correttamente
     * @brief Verifica che il libro sia presente nella repository dopo averlo aggiunto
     */
    @Test
    void testAdd() {
        rep.add(l1);
        assertEquals(l1,rep.findByKey("9781492856347"));
    }

    /**
     * @test verifica che il libro venga rimosso correttamente
     * @brief Verifica che il libro venga rimosso dalla repository correttamente
     */
    @Test
    void testRimuoviLibro() {
        rep.add(l1);
        assertTrue(rep.remove(l1));
        assertEquals(null, rep.findByKey("9781492856347"));
    }

    /**
     * @test verifica che non ci siano errori nella rimozione
     * @brief Verifica che venga restituito il valore falso quando si prova a rimuovere un libro 
     * non presente nella repository
     */
    @Test
    void testRimuoviLibroNonEx() {
        rep.add(l1);
        assertFalse(rep.remove(l2));
    }

    
    /**
     * @test verifica che vengano presi tutti gli elementi dell'elenco
     * @brief Verifica che venga restituito tutto l'elenco dei libri correttamente
     */
    @Test
    void testGetAll() {
        rep.add(l1);
        rep.add(l2);
        ObservableList<Libro> libri = rep.getAll();
        
        assertEquals(2, libri.size());
        assertEquals(l1, libri.get(0));
        assertEquals(l2,libri.get(1));
    }
    
     /**
     * @test verifica che la ricerca nella repository vada a buon fine
     * @brief Verifica che la ricerca di un libro presente nella repository vada a buon fine, mentre
     * se viene ricercato un libro non registrato, venga trovato il valore null
     */
    @Test
    void testFindByKey() {
        rep.add(l1);
        assertEquals(l1,rep.findByKey("9781492856347"));
        assertEquals(null,rep.findByKey("9780376491823"));  //è null se prova a cercare un valore non presente
    }

    /**
     * @test verifica che vengano rimpiazzati tutti gli elementi della lista
     * @brief Verifica che venga effettivamente realizzato lo scambio tra la lista di libri vecchia e
     * la lista nuova di libri passata come parametro
     */
    @Test
    void testReplaceAll() {
        
        rep.add(l1);
        
        List<Libro> nuoviL = new ArrayList<>();
        nuoviL.add(l2);
        
        rep.replaceAll(nuoviL);
        
        assertEquals(null,rep.findByKey("9781492856347"));
        assertEquals(l2,rep.findByKey("9780376491823"));    
    }
    
    
}

