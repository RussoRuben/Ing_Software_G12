package gruppo12.bibliotecaunisa.repository;


import gruppo12.bibliotecaunisa.model.Studente;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import javafx.collections.ObservableList;

class StudenteRepositoryTest {

    private StudenteRepository rep;
    private Studente s1;
    private Studente s2;

    @BeforeEach
    void setUp() {
        rep = new StudenteRepository();
        s1 = new Studente("0612700001", "Anna", "Pepe", "anna.pepe@studenti.unisa.it");
        s2 = new Studente("0612700002", "Fabio", "Marracash", "fabio.marracash@studenti.unisa.it");
    }
    
    

    /**
     * @test verifica che lo studente venga aggiunto correttamente
     * @brief Verifico che lo studente sia presente nella repository dopo averlo aggiunto
     */
    @Test
    void testAdd() {
        rep.add(s1);
        assertEquals(s1,rep.findByKey("0612700001"));
    }

    /**
     * @test verifica che lo studente venga rimosso correttamente
     * @brief verifico che lo studente venga rimosso dalla repository correttamente
     */
    @Test
    void testRemoveStudent() {
        rep.add(s1);
        assertTrue(rep.remove(s1));
        assertEquals(null, rep.findByKey("0612700001"));
    }
    
    /**
     * @test verifica che non ci siano errori nella rimozione
     * @brief Verifica che venga restituito il valore falso quando si prova a rimuovere uno studente 
     * non presente nella repository
     */
    @Test
    void testRemoveStudentNonEx() {
        rep.add(s1);
        assertFalse(rep.remove(s2));
    }

    
    /**
     * @test verifica che vengano presi tutti gli elementi dell'elenco
     * @brief Verifica che venga restituito tutto l'elenco degli alunni correttamente
     */
    @Test
    void testGetAll() {
        rep.add(s1);
        rep.add(s2);
        ObservableList<Studente> studenti = rep.getAll();
        
        assertEquals(2, studenti.size());
        assertEquals(s1,studenti.get(0));
        assertEquals(s2,studenti.get(1));
    }

    
    /**
     * @test verifica che la ricerca nella repository vada a buon fine
     * @brief Verifica che la ricerca di uno studente presente nella repository vada a buon fine, mentre
     * se viene ricercato uno studente non registrato, venga trovato il valore null
     */
    @Test
    void testFindByKey() {
        rep.add(s1);
        assertEquals(s1,rep.findByKey("0612700001"));
        assertEquals(null,rep.findByKey("0612700002"));  //è null se prova a cercare un valore non presente
    }

    
    /**
     * @test verifica che vengano rimpiazzati tutti gli elementi della lista
     * @brief Verifica che venga effettivamente realizzato lo scambio tra la lista di studenti vecchia e
     * la lista nuova di studenti passata come parametro
     */
    @Test
    void testReplaceAll() {
        
        rep.add(s1);
        
        List<Studente> nuoviS = new ArrayList<>();
        nuoviS.add(s2);
        
        rep.replaceAll(nuoviS);
        
        assertEquals(null,rep.findByKey("0612700001"));
        assertEquals(s2,rep.findByKey("0612700002"));
        
       
    }
}
