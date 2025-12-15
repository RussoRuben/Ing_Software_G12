package com.mycompany.mavenproject1;

import gruppo12.bibliotecaunisa.data.BibliotecaData;
import gruppo12.bibliotecaunisa.model.*;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaServiceTest {

    private BibliotecaService service;
    private BibliotecaData data;
    private Libro libro;
    private Studente studente;
    private Prestito prestito;

    @BeforeEach
    public void setUp() {
        data = new BibliotecaData();
        service = new BibliotecaService(data);

        libro = new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"), "Editore", 2, 2020, 5);
        studente = new Studente("0612700000", "Luigi", "Bianchi", "l.bianchi@studenti.unisa.it");
        prestito = new Prestito(studente, libro, LocalDate.now(), LocalDate.now().plusDays(30));
    }

    /**
     * @test Test del metodo aggiugni libro di BibliotecaService
     * @brief Verifico che il libro sia stato correttamente aggiunto alla lista
     */
    @Test
    public void testAggiungiLibro() {
        service.aggiungiLibro(libro);
        ObservableList<Libro> libri = service.getLibri();
        assertTrue(libri.contains(libro));
    }

    
    /**
     * @test Test del metodo Modifica Libro di BibliotecaService
     * @brief Verifico che il libro precedentemente aggiunto venga modificato come richiesto, e che le informazioni
     * del libro vengano aggiornate correttamente
     */
    @Test
    public void testModificaLibro() {

        service.aggiungiLibro(libro);
        service.modificaLibro("1234567890111", "Nuovo Titolo", Arrays.asList("Autore3"), "Nuovo Editore", 2, 2021, 10);

        Libro modificato = service.trovaLibro("1234567890111");
        assertEquals("Nuovo Titolo", modificato.getTitolo());
        assertEquals("Nuovo Editore", modificato.getEditore());
        assertEquals(2, modificato.getNumeroEdizione());
        assertEquals(2021, modificato.getAnno());
        assertEquals(10, modificato.getCopieDisponibili());
        assertEquals(Arrays.asList("Autore3"), modificato.getAutori());
    }
    
    /**
     * @test Test del metodo Rimuovi libro di BibliotecaService
     * @brief Verifico che il libro venga rimosso correttamente dalla lista
     */
    @Test
    public void testRimuoviLibro() {
        service.aggiungiLibro(libro);
        service.rimuoviLibro(libro);
        assertFalse(service.getLibri().contains(libro));
    }
    
    /**
     * @test Test del metodo getLibri di BibliotecaService
     * @brief Verifico che venga presa per intero la lista dei libri presenti nella biblioteca
     */

    @Test
    public void testGetLibri() {
        service.aggiungiLibro(libro);
        ObservableList<Libro> libri = service.getLibri();
        assertEquals(1, libri.size());
        assertTrue(libri.contains(libro));
    }

    /**
     *@test Test del metodo Trova Libro di BibliotecaService
     *@brief Verifico che la ricerca di un libro tramite codice identificativo vada a buon fine
     * e che non venga restituito valore nullo quando il libro è effettivamente presente nell'elenco
     */
    @Test
    public void testTrovaLibro() {
        service.aggiungiLibro(libro);
        Libro trovato = service.trovaLibro("1234567890111");
        assertNotNull(trovato);
        assertEquals(libro, trovato);
    }
    
     /**
     * @test Test del metodo aggiugni Studente di BibliotecaService
     * @brief Verifico che lo studente sia stato correttamente aggiunto alla lista
     */
    @Test
    public void testAggiungiStudente() {
        service.aggiungiStudente(studente);
        ObservableList<Studente> studenti = service.getStudenti();
        assertTrue(studenti.contains(studente));;
    }

    /**
     * @test Test del metodo Modifica Studente di BibliotecaService
     * @brief Verifico che lo studente precedentemente aggiunto venga modificato come richiesto e che le sue informazioni
     * vengano aggiornate correttamente
     */
    @Test
    public void testModificaStudente() {
        service.aggiungiStudente(studente);
        service.modificaStudente("0612700000", "Luigi", "Bianchi", "l.bianchi@studenti.unisa.it");

        Studente modificato = service.trovaStudente("0612700000");
        assertEquals("Luigi", modificato.getNome());
        assertEquals("Bianchi", modificato.getCognome());
        assertEquals("l.bianchi@studenti.unisa.it", modificato.getEmail());
    }
    
    /**
     * @test Test del metodo Rimuovi Studente di BibliotecaService
     * @brief Verifico che il libro venga rimosso correttamente dalla lista
     */
    @Test
    public void testRimuoviStudente() {
        service.aggiungiStudente(studente);
        service.rimuoviStudente(studente);
        assertFalse(service.getStudenti().contains(studente));
    }
    
    
     /**
     * @test Test del metodo getStudenti di BibliotecaService
     * @brief Verifico che venga presa per intero la lista degli studenti registrati
     */
    @Test
    public void testGetStudenti() {
        service.aggiungiStudente(studente);
        ObservableList<Studente> studenti = service.getStudenti();
        assertEquals(1, studenti.size());
        assertTrue(studenti.contains(studente));
    }
    
    /**
     *@test Test del metodo Trova Studente di BibliotecaService
     *@brief Verifico che la ricerca di uno studente tramite matricola vada a buon fine
     * e che non venga restituito valore nullo quando lo studente è effettivamente presente nell'elenco
     */
    @Test
    public void testTrovaStudente() {
        service.aggiungiStudente(studente);
        Studente trovato = service.trovaStudente("0612700000");
        assertNotNull(trovato);
        assertEquals(studente, trovato);
    }
    
    /**
     * @test Test del metodo aggiugni Prestito di BibliotecaService
     * @brief Verifico che il prestito sia stato correttamente aggiunto alla lista dei prestiti
     */
    @Test
    public void testAggiungiPrestito() {
        service.aggiungiLibro(libro);
        service.aggiungiStudente(studente);

        service.aggiungiPrestito(prestito);

        ObservableList<Prestito> prestiti = service.getPrestiti();
        assertTrue(prestiti.contains(prestito));
        assertEquals(4, libro.getCopieDisponibili()); // una copia in meno
        assertTrue(studente.getListaPrestiti().contains(prestito));
    }
    
     /**
     * @test Test del metodo Restituisci Prestito di BibliotecaService
     * @brief Verifico che il libro venga rimosso correttamente dalla lista dei prestiti attivi
     * e inserito nella lista dei prestiti archiviati
     */
    @Test
    public void testRestituisciPrestito() {
        
        int copiePrima = libro.getCopieDisponibili();

        service.restituisciPrestito(prestito);
        
        assertEquals(copiePrima + 1, libro.getCopieDisponibili());

        assertFalse(data.getPrestitoRepo().getAll().contains(prestito));
        assertTrue(data.getPrestitoRepo().getAllArchiviati().contains(prestito));
      
        assertNotNull(prestito.getDataFine());
        assertEquals(LocalDate.now(), prestito.getDataFine());
        assertFalse(studente.getListaPrestiti().contains(prestito));
    }
    
     /**
     * @test Test del metodo get Prestiti di BibliotecaService
     * @breif Verifico che venga presa per intero la lista dei prestiti attivi
     */
    @Test
    public void testGetPrestiti() {
        service.aggiungiLibro(libro);
        service.aggiungiStudente(studente);
        service.aggiungiPrestito(prestito);

        ObservableList<Prestito> prestiti = service.getPrestiti();
        assertEquals(1, prestiti.size());
        assertTrue(prestiti.contains(prestito));
    }

    /**
     * @test Test del metodo get Prestiti Archiviati di BibliotecaService
     * @breif Verifico che venga presa per intero la lista dei prestiti archiviati
     */
    @Test
    public void testGetPrestitiArchiviati() {
        service.aggiungiLibro(libro);
        service.aggiungiStudente(studente);
        service.restituisciPrestito(prestito);

        ObservableList<Prestito> prestiti = service.getPrestitiArchiviati();
        assertEquals(1, prestiti.size());
        assertTrue(prestiti.contains(prestito));
    }
    
    /**
     *@test Test del metodo Trova prestito di BibliotecaService
     *@brief Verifico che la ricerca di un prestito tramite codice identificativo vada a buon fine
     * e che non venga restituito valore nullo quando il prestito è effettivamente presente nell'elenco
     */
    @Test
    public void testTrovaPrestito() {
        service.aggiungiLibro(libro);
        service.aggiungiStudente(studente);
        service.aggiungiPrestito(prestito);

        Prestito trovato = service.trovaPrestito(prestito.getCodice());
        assertNotNull(trovato);
        assertEquals(prestito, trovato);
    }
    /**
     * @test Test del metodo TrovaPrestitoArchiviato di BibliotecaService
     * @brief Verifico che la ricerca di un prestito tramite codice identificativo vada a buon fine
     * e che non venga restituito valore nullo quando il prestito è effettivamente presente nell'elenco
     */
    @Test
    public void testTrovaPrestitoArchiviato() {
        service.aggiungiLibro(libro);
        service.aggiungiStudente(studente);
        service.restituisciPrestito(prestito);

        Prestito trovato = service.trovaPrestitoArchiviato(prestito.getCodice());
        assertNotNull(trovato);
        assertEquals(prestito, trovato);
    }

}
