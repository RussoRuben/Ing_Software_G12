package gruppo12.bibliotecaunisa.repository;

import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Studente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import javafx.collections.ObservableList;

class PrestitoRepositoryTest {

    private PrestitoRepository rep;
    private Prestito p1;
    private Prestito p2;
    private Studente s1;
    private Studente s2;
    private Libro l1;
    private Libro l2;
    private Long codice_p1;
    private Long codice_p2;
    
    
    

    @BeforeEach
    void setUp() {
        rep= new PrestitoRepository();
        s1 = new Studente("0612700001", "Anna", "Pepe", "anna.pepe@studenti.unisa.it");
        s2 = new Studente("0612900054", "Claudio", "Rinaldi", "claudio.rinaldi@studenti.unisa.it");
        l1 = new Libro("9781492856347", "La Divina Commedia", Arrays.asList("Dante Alighieri"), "Mondadori", 1, 2025, 3);
        l2 = new Libro("8781499858332", "L'inverno delle stelle", Arrays.asList("Nicoletta Verna"), "Rizzoli", 3, 1321, 10);
        LocalDate dataInizio1 = LocalDate.now();
        LocalDate dataInizio2 = LocalDate.now().plusDays(1);
        LocalDate dataFine1 = dataInizio1.plusWeeks(2); 
        LocalDate dataFine2 = dataInizio2.plusWeeks(2);
        p1 = new Prestito( s1, l1, LocalDate.now(),dataFine1);
        p2 = new Prestito(s2, l2, LocalDate.now().plusDays(1),dataFine2);
        codice_p1 = p1.getCodice(); 
        codice_p2 = p2.getCodice();
    }
    
    

    /**
     * @test verifica che il prestiti venga aggiunto correttamente 
     * @brief Verifica che il prestito sia presente nella repository dopo averlo aggiunto
     */
    @Test
    void testAdd() {
        rep.add(p1);
        assertEquals(p1,rep.findByKey(codice_p1));
        assertTrue(s1.getListaPrestiti().contains(p1));
    }

    /**
     * @test verifica che il prestito venga rimosso correttamente
     * @brief Verifica che il prestito venga rimosso dalla repository correttamente
     */
    @Test
    void testRemovePrestito() {
        rep.add(p1);
        assertTrue(rep.remove(p1));
        assertNull(rep.findByKey(codice_p1));
    }
    
    /**
     * @test verifica che non ci siano errori nella rimozione
     * @brief Verifica che venga restituito il valore falso quando si prova a rimuovere un prestito
     * non presente nella repository
     */
    @Test
    void testRemovePrestitoNonEx() {
        rep.add(p1);
        assertFalse(rep.remove(p2));
        assertNotNull(rep.findByKey(codice_p1)); 
        
    }

    
    /**
     * @test verifica che vengano presi tutti gli elementi dell'elenco
     * @brief Verifica che venga restituito tutto l'elenco dei prestiti correttamente
     */
    @Test
    void testGetAll() {
        rep.add(p1);
        rep.add(p2);
        ObservableList<Prestito> prestiti = rep.getAll();
        
        assertEquals(2, prestiti.size());
        assertEquals(p1,prestiti.get(0));
        assertEquals(p2,prestiti.get(1));
    }

    
    /**
     * @test verifica tramite codice che la ricerca nella repository vada a buon fine
     * @brief Verifica che la ricerca di un prestito presente nella repository vada a buon fine, mentre
     * se viene ricercato un prestito non registrato, venga trovato il valore null
     */
    @Test
    void testFindByKey() {
        rep.add(p1);
        assertEquals(p1,rep.findByKey(codice_p1));
        assertNull(rep.findByKey(codice_p2));  //è null se prova a cercare un valore non presente
    }

    
    /**
     * @test verifica che vengano rimpiazzati tutti gli elementi della lista
     * @brief Verifica che venga effettivamente realizzato lo scambio tra la lista di prestiti vecchia e
     * la lista nuova di prestiti passata come parametro
     */
    @Test
    void testReplaceAll() {
        
        rep.add(p1);
        
        List<Prestito> nuoviPrestiti = new ArrayList<>();
        nuoviPrestiti.add(p2);
        
        rep.replaceAll(nuoviPrestiti);
        
        assertNull(rep.findByKey(codice_p1));
        assertEquals(p2,rep.findByKey(codice_p2));
        
       
    }
    
     /**
     * @test verifica che il prestiti venga aggiunto correttamente alla collezione dei prestiti archiviati
     * @brief Verifica che il prestito sia presente nella alla collezione dei prestiti archiviati dopo averlo aggiunto
     */
    @Test
    void testAddArchiviato() {
        rep.addArchiviato(p1);
        assertEquals(p1,rep.findArchiviatoByKey(codice_p1));

    }
    
    /**
     * @test verifica che il prestito venga rimosso correttamente dalla collezione dei prestiti archiviati
     * @brief Verifica che il prestito venga rimosso dalla collezione dei prestiti archiviati correttamente
     */
    @Test
    void testRemoveArchiviato() {
        rep.addArchiviato(p1);
        rep.removeArchiviato(p1);
        assertNull(rep.findArchiviatoByKey(codice_p1));
    
    }
    
  
    /**
     * @test verifica che vengano restituiti tutti i prestiti archiviati
     * @brief Verifica che vengano restituiti tutti i prestiti archiviti correttamente
     */
    @Test
    void testGetAllArchiviati() {
        rep.addArchiviato(p1);
        rep.addArchiviato(p2);
        ObservableList<Prestito>prestiti=rep.getAllArchiviati();
        assertEquals(2,prestiti.size());
        assertTrue(prestiti.contains(p1));
        assertTrue(prestiti.contains(p2));
        
    }
    
    /**
     * @brief Cerca un prestito archiviato attraverso il suo codice CUIP
     * @param codice Il codice CUIP del prestito archiviato che deve essere cercato
     * @return L'oggetto Prestito archiviato corrispondente al suo codice CUIP,o null se non trovato
     */
    /**
     * @test verifica che la ricerca di un  prestito archiviato tramite codice vada a buon fine
     * @brief Verifica che la ricerca di un prestito archiviato vada a buon fine, mentre
     * se viene ricercato un prestito non registrato, venga trovato il valore null
     */
    @Test
    void testFindArchiviatoByKey() {
        rep.addArchiviato(p1);
        assertEquals(p1,rep.findArchiviatoByKey(codice_p1));
        assertNull(rep.findArchiviatoByKey(codice_p2));
    }
    
    /**
     * @brief Sostituisce la collezione dei prestiti archiviati con una lista di nuovi dati 
     * @param nuoviDati La List<Prestito> che contiene i nuovi dati
     */
    /**
     * @test verifica che venga rimpiazzata l'intera lista dei prestiti archiviata
     * @brief Verifica che venga sostituita la cllezione archiviata
     */
    @Test
    void testReplaceAllArchiviati() {
        rep.addArchiviato(p1);
        List<Prestito> nuoviPrestiti = new ArrayList<>();
        nuoviPrestiti.add(p2);
        rep.replaceAllArchiviati(nuoviPrestiti);
        assertNull(rep.findArchiviatoByKey(codice_p1));
        assertEquals(p2,rep.findArchiviatoByKey(codice_p2));
        assertEquals(1,rep.getAllArchiviati().size());
        
    }
}


