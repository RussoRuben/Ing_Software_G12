package gruppo12.bibliotecaunisa.data;

import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.repository.LibroRepository;
import gruppo12.bibliotecaunisa.repository.PrestitoRepository;
import gruppo12.bibliotecaunisa.repository.StudenteRepository;
import java.io.File;
import java.time.LocalDate;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BibliotecaDataTest {

    private BibliotecaData data;
    private static final String TEST_PATH = "data/test_stato.bin";

    @BeforeEach
    void setUp() {
        data = new BibliotecaData();
    }
    
    /**
     * @test Verifica il metodo getLibroRepo da BibliotecaData
     * @brief Controlla che il repository dei libri non sia nullo,
     *        e sia sempre la stessa istanza.
     */
    @Test
    void testGetLibroRepo() {
        LibroRepository repo = data.getLibroRepo();

        assertNotNull(repo);
        assertEquals(repo, data.getLibroRepo());

        
        Libro l= new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"), "Editore", 2, 2020, 5);
        repo.add(l);

        assertEquals(1, data.getLibroRepo().getAll().size());
        assertTrue(data.getLibroRepo().getAll().contains(l));
    };

    /**
     * @test Verifica il metodo getStudenteRepo da BibliotecaData.
     * @brief Controlla che il repository degli studenti non sia nullo e sia sempre la stessa istanza.
     *      
     */
    @Test
    void testGetStudenteRepo() {
        StudenteRepository repo = data.getStudenteRepo();

        assertNotNull(repo);
        assertSame(repo, data.getStudenteRepo());

        Studente s = new Studente("0612700001", "Fabio", "Marracash", "fabio.marracash@studenti.unisa.it");
        repo.add(s);

        assertEquals(1, data.getStudenteRepo().getAll().size());
        assertTrue(data.getStudenteRepo().getAll().contains(s));
    }

    /**
     * @test Verifica il metodo getPrestitoRepo da BibliotecaData.
     * @brief Controlla che il repository dei prestiti venga restituito
     *        correttamente.
     */
    @Test
    void testGetPrestitoRepo() {
        PrestitoRepository repo = data.getPrestitoRepo();

        assertNotNull(repo);
        assertSame(repo, data.getPrestitoRepo());

        Libro l= new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"), "Editore", 2, 2020, 5);
        Studente s = new Studente("0612700001", "Fabio", "Marracash", "fabio.marracash@studenti.unisa.it");
        LocalDate dInizio= LocalDate.now();
        LocalDate dFine= LocalDate.now().plusDays(30);
        Prestito p = new Prestito(s, l, dInizio, dFine);

        repo.add(p);

        assertEquals(1, data.getPrestitoRepo().getAll().size());
        assertTrue(data.getPrestitoRepo().getAll().contains(p));
    }


    /**
     * @test Verifica il metodo salvaStato di BibliotecaData.
     * @brief Controlla che il file di stato venga creato correttamente
     *        e che contenga dati serializzati.
     */
    @Test
    void testSalvaStato() {
        LocalDate dInizio= LocalDate.now();
        LocalDate dFine= LocalDate.now().plusDays(30);
        Libro l= new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"), "Editore", 2, 2020, 5);
        Studente s = new Studente("0612700001", "Fabio", "Marracash", "fabio.marracash@studenti.unisa.it");
        Prestito p = new Prestito(s, l, dInizio, dFine);
        
        data.getLibroRepo().add(l);
        data.getStudenteRepo().add(s);
        data.getPrestitoRepo().add(p);
        
        data.salvaStato(TEST_PATH);

        File f = new File(TEST_PATH);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
    }

  
    /**
     * @test Verifica il metodo caricaStato di  BibliotecaData.
     * @brief Controlla che i dati precedentemente salvati vengano
     *        correttamente ripristinati nei repository.
     */
    @Test
    void testCaricaStato() {
        
        LocalDate dInizio= LocalDate.now();
        LocalDate dFine= LocalDate.now().plusDays(30);
        Libro l= new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"), "Editore", 2, 2020, 5);
        Studente s = new Studente("0612700001", "Fabio", "Marracash", "fabio.marracash@studenti.unisa.it");
        Prestito p = new Prestito(s, l, dInizio, dFine);
        
        data.getLibroRepo().add(l);
        data.getStudenteRepo().add(s);
        data.getPrestitoRepo().add(p);
        
        data.salvaStato(TEST_PATH);

        BibliotecaData caricata = new BibliotecaData();
        caricata.caricaStato(TEST_PATH);

        assertEquals(1, caricata.getLibroRepo().getAll().size());
        assertTrue(caricata.getLibroRepo().getAll().contains(l));
        assertEquals(1, caricata.getStudenteRepo().getAll().size());
        assertTrue(caricata.getStudenteRepo().getAll().contains(s));
        assertEquals(1, caricata.getPrestitoRepo().getAll().size());
        assertTrue(caricata.getPrestitoRepo().getAll().contains(p));
    }

    
     /**
     * @test Verifica il metodo caricaStato di  BibliotecaData.
     * @brief Valutiamo il caso in cui il file da cui si vuole caricare lo stato non esista e verifichiamo che 
     * non venga caricato nulla
     */
    
    @Test
    void testCaricaFileInesistente() {
        BibliotecaData nuova = new BibliotecaData();
        nuova.caricaStato("data/file_inesistente.bin");

        assertTrue(nuova.getLibroRepo().getAll().isEmpty());
        assertTrue(nuova.getStudenteRepo().getAll().isEmpty());
        assertTrue(nuova.getPrestitoRepo().getAll().isEmpty());
    }
}