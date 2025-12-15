package gruppo12.bibliotecaunisa.model;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class LibroTest {
    private Libro libro;
    private List<String> autori;

    
    @BeforeEach
    public void setUp() {
        autori = Arrays.asList("Autore1", "Autore2");
        libro = new Libro("1234567890111", "Titolo", autori,
                "Editore", 2, 2020, 5);
    }
    
    /**
     * @test Verifica Serializzazione e deserializzazione 
     * @brief Viene creato un file temporaneo da cui viene letto un oggetto per verificare che sia uguale all'originale dopo la serializzazione
     */
    @Test
    void testWriteReadObjectWithTempFile() throws Exception {

      
        //File temporaneo per test
        File temp = File.createTempFile("test-libro", ".ser");
        temp.deleteOnExit();

        //Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {
            oos.writeObject(libro);
        }

        //Deserializzazione
        Libro libroLetto;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(temp))) {
            libroLetto = (Libro) ois.readObject();
        }

        //Assert
        assertNotNull(libroLetto);
        assertEquals(libro.getCodice(), libroLetto.getCodice());
        assertEquals(libro.getTitolo(), libroLetto.getTitolo());
        assertEquals(libro.getAnno(), libroLetto.getAnno());
        assertEquals(libro.getAutoriString(), libroLetto.getAutoriString());
        assertEquals(libro.getCopieDisponibili(), libroLetto.getCopieDisponibili());
        assertEquals(libro.getNumeroEdizione(), libroLetto.getNumeroEdizione());
        assertEquals(libro.getEditore(), libroLetto.getEditore());                
    }
    
    /**
     * @test verifica funzionamento costruttore
     */
    @Test
    public void testCostruttore() {
        assertEquals("1234567890111", libro.getCodice());
        assertEquals("Titolo", libro.getTitolo());
        assertEquals("Editore", libro.getEditore());
        assertEquals(2, libro.getNumeroEdizione());
        assertEquals(2020, libro.getAnno());
        assertEquals(5, libro.getCopieDisponibili());
        assertIterableEquals(autori, libro.getAutori());
    }

    /**
     * @test verifica metodo GetAutoriString
     */
    @Test
    public void testGetAutoriString() {
        assertEquals("Autore1, Autore2", libro.getAutoriString());
    }
    
    /**
     * @test verifica metodo GetCodice
     */
    @Test
    public void testGetCodice() {
        assertEquals("1234567890111", libro.getCodice());
    }
    
    /**
     * @test verifica metodo GetTitolo
     */
    @Test
    public void testGetTitolo() {
        assertEquals("Titolo", libro.getTitolo());
    }
    
    /**
     * @test verifica metodo GetEditore
     */
    @Test
    public void testGetEditore() {
        assertEquals("Editore", libro.getEditore());
    }
    
    /**
     * @test verifica metodo GetAnno
     */
    @Test
    public void testGetAnno() {
        assertEquals(2020, libro.getAnno());
    }

    /**
     * @test verifica metodo GetNumeroEdizione
     */
    @Test
    public void testGetNumeroEdizione() {
        assertEquals(2, libro.getNumeroEdizione());
    }
    
    /**
     * @test verifica metodo GetCopieDisponibili
     */
    @Test
    public void testGetCopieDisponibili() {
        assertEquals(5, libro.getCopieDisponibili());
    }

    /**
     * @test verifica metodo SetCodice
     */
    @Test
    public void testSetCodice() {
        libro.setCodice("XYZ");
        assertEquals("XYZ", libro.getCodice());
    }

    /**
     * @test verifica metodo SetTitolo
     */
    @Test
    public void testSetTitolo() {
        libro.setTitolo("NuovoTitolo");
        assertEquals("NuovoTitolo", libro.getTitolo());
    }

    
    /**
     * @test verifica metodo SetEditore
     */
    @Test
    public void testSetEditore() {
        libro.setEditore("NuovoEditore");
        assertEquals("NuovoEditore", libro.getEditore());
    }

    /**
     * @test verifica metodo SetNumeroEdizione
     */
    @Test
    public void testSetNumeroEdizione() {
        libro.setNumeroEdizione(10);
        assertEquals(10, libro.getNumeroEdizione());
    }
    
    /**
     * @test verifica metodo SetAutori
     */
    @Test
    public void testSetAutori() {
        List<String> nuoviAutori = Arrays.asList("A", "B");
        libro.setAutori(nuoviAutori);
        assertIterableEquals(nuoviAutori, libro.getAutori());
    }

    /**
     * @test verifica metodo SetAnno
     */
    @Test
    public void testSetAnno() {
        libro.setAnno(1999);
        assertEquals(1999, libro.getAnno());
    }
    
    /**
     * @test verifica metodo SetCopieDisponibili
     */
    @Test
    public void testSetCopieDisponibili() {
        libro.setCopieDisponibili(99);
        assertEquals(99, libro.getCopieDisponibili());
    }

    /**
     * @test verifica metodo ToString
     */
    @Test
    public void testToString() {
        assertEquals("1234567890111: Titolo", libro.toString());
    }
}