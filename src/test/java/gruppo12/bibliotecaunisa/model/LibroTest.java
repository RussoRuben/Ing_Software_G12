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

    @Test
    public void testGetAutoriString() {
        assertEquals("Autore1, Autore2", libro.getAutoriString());
    }
    
    @Test
    public void testGetCodice() {
        assertEquals("1234567890111", libro.getCodice());
    }
    
    @Test
    public void testGetTitolo() {
        assertEquals("Titolo", libro.getTitolo());
    }
    
    @Test
    public void testGetEditore() {
        assertEquals("Editore", libro.getEditore());
    }
     
    @Test
    public void testGetAnno() {
        assertEquals(2020, libro.getAnno());
    }

    @Test
    public void testGetNumeroEdizione() {
        assertEquals(2, libro.getNumeroEdizione());
    }
    
    @Test
    public void testGetCopieDisponibili() {
        assertEquals(5, libro.getCopieDisponibili());
    }


    @Test
    public void testSetCodice() {
        libro.setCodice("XYZ");
        assertEquals("XYZ", libro.getCodice());
    }

    @Test
    public void testSetTitolo() {
        libro.setTitolo("NuovoTitolo");
        assertEquals("NuovoTitolo", libro.getTitolo());
    }

    @Test
    public void testSetEditore() {
        libro.setEditore("NuovoEditore");
        assertEquals("NuovoEditore", libro.getEditore());
    }

    @Test
    public void testSetNumeroEdizione() {
        libro.setNumeroEdizione(10);
        assertEquals(10, libro.getNumeroEdizione());
    }

    @Test
    public void testSetAutori() {
        List<String> nuoviAutori = Arrays.asList("A", "B");
        libro.setAutori(nuoviAutori);
        assertIterableEquals(nuoviAutori, libro.getAutori());
    }

    @Test
    public void testSetAnno() {
        libro.setAnno(1999);
        assertEquals(1999, libro.getAnno());
    }

    @Test
    public void testSetCopieDisponibili() {
        libro.setCopieDisponibili(99);
        assertEquals(99, libro.getCopieDisponibili());
    }

    @Test
    public void testToString() {
        assertEquals("1234567890111: Titolo", libro.toString());
    }
}
