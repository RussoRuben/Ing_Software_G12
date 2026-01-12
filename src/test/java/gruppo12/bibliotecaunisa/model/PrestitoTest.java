package gruppo12.bibliotecaunisa.model;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;


public class PrestitoTest {
    
    private Prestito prestito;
    private Studente studente;
    private Libro libro;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    @BeforeEach
    public void setUp() {
        Prestito.setNum(0);
        studente = new Studente("0612700000", "Mario", "Rossi", "m.rossi@unisa.it");
        libro = new Libro("1234567890111", "Titolo", Arrays.asList("Autore1", "Autore2"),"Editore", 2, 2020, 5);
        dataInizio = LocalDate.now();
        dataFine = LocalDate.now().plusDays(30);
        prestito = new Prestito(studente, libro, dataInizio, dataFine);
    }
    
    /**
     * @test Verifica Serializzazione e deserializzazione 
     * @brief Viene creato un file temporaneo da cui viene letto un oggetto per verificare che sia uguale all'originale dopo la serializzazione
     */
    @Test
    void testWriteReadObjectWithTempFile() throws Exception {
      
        //File temporaneo per test
        File temp = File.createTempFile("test-prestito", ".ser");
        temp.deleteOnExit();
        
        //Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {
            oos.writeObject(prestito);
        }

        //Deserializzazione
        Prestito prestitoLetto;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(temp))) {
            prestitoLetto = (Prestito) ois.readObject();
        }

        //Assert
        assertNotNull(prestitoLetto);
        assertEquals(prestito.getStudente(), prestitoLetto.getStudente());
        assertEquals(prestito.getLibro(), prestitoLetto.getLibro());
        assertEquals(prestito.getCodice(), prestitoLetto.getCodice());
        assertEquals(prestito.getDataInizio(), prestitoLetto.getDataInizio());
        assertEquals(prestito.getDataFine(), prestitoLetto.getDataFine());            
    }
    
    /**
     * @test verifica funzionamento costruttore
     */
    @Test
    public void testCostruttore() {
        assertEquals(studente, prestito.getStudente());
        assertEquals(libro, prestito.getLibro());
        assertEquals(dataInizio, prestito.getDataInizio());
        assertEquals(dataFine, prestito.getDataFine());
        assertEquals(1, prestito.getCodice());
    }

    /**
     * @test verifica metodo GetStudente
     */
    @Test
    public void testGetStudente() {
        assertEquals(studente, prestito.getStudente());
    }

    /**
     * @test verifica metodo SetDataFine
     */
    @Test
    public void testSetDataFine() {
        LocalDate testData = LocalDate.now().plusDays(60);
        prestito.setDataFine(testData);
        assertEquals(testData, prestito.getDataFine());
    }
    
    /**
     * @test verifica metodo GetStudenteString
     */
    @Test
    public void testGetStudenteString() {
        assertEquals(studente.toString(), prestito.getStudenteString());
    }
    
    /**
     * @test verifica metodo GetLibroString
     */
    @Test
    public void testGetLibroString() {
        assertEquals(libro.toString(), prestito.getLibroString());
    }
    
    /**
     * @test verifica metodo GetSetNum
     */
    @Test
    public void testGetSetNum() {
        Prestito.setNum(100);
        assertEquals(100, Prestito.getNum());
        
        Prestito prestito2 = new Prestito(studente, libro, dataInizio, dataFine);
        assertEquals(101, prestito2.getCodice());
    }
}