package gruppo12.bibliotecaunisa.model;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import static org.junit.jupiter.api.Assertions.*;


public class StudenteTest {
    private Studente studente;
    private List<Prestito> prestiti;
    private Libro libro1;
    private Libro libro2;
    private Libro libro3;
    private Libro libro4;
    private Prestito prestito1;
    private Prestito prestito2;
    private Prestito prestito3;
    private Prestito prestito4;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    
    
    
    @BeforeEach
    public void setUp() {
        List <String> autori = Arrays.asList("Autore");
        libro1 = new Libro("1234567890111", "Moby Dick", autori,
                "Editore1", 2, 2020, 1);
        libro2 = new Libro("6784567890211", "Odissea", autori,
                "Editore2", 2, 2020, 1);
        libro3 = new Libro("5784567890233", "Il giovane Holden", autori,
                "Editore3", 2, 2020, 1);
        libro4 = new Libro("9784537890234", "La Storia", autori,
                "Editore4", 2, 2020, 1);
      
        //List <Prestito> prestiti= Arrays.asList(prestito1, prestito2);
        studente=new Studente("0612677146","Claudia","Barbato","c.barbato@studenti.unisa.it");
        dataInizio=LocalDate.now();
        dataFine=dataInizio.plusDays(30);
        
        prestito1 = new Prestito(studente, libro1, dataInizio,dataFine);
        prestito2 = new Prestito(studente, libro2, dataInizio,dataFine);
        prestito3 = new Prestito(studente, libro3, dataInizio,dataFine);
        prestito4 = new Prestito(studente, libro4, dataInizio,dataFine);
        
    }
     
    @Test
    void testWriteReadObjectFile() throws Exception {
        
        
        studente.aggiungiListaPrestiti(prestito1);
        studente.aggiungiListaPrestiti(prestito2);
   
        //File temporaneo per test
        File temp = File.createTempFile("test-studente", ".ser");
        temp.deleteOnExit();

        //Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {
            oos.writeObject(studente);
        }

        //Deserializzazione
        Studente studenteLetto;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(temp))) {
            studenteLetto = (Studente) ois.readObject();
        }

        //Assert
        assertNotNull(studenteLetto);
        assertEquals(studente.getMatricola(), studenteLetto.getMatricola());
        assertEquals(studente.getNome(), studenteLetto.getNome());
        assertEquals(studente.getCognome(), studenteLetto.getCognome());
        assertEquals(studente.getEmail(), studenteLetto.getEmail());;
        assertEquals(studente.getListaPrestitiString(), studenteLetto.getListaPrestitiString());
             
    }
    
    @Test
    public void testCostruttore() {
        assertEquals("0612677146", studente.getMatricola());
        assertEquals("Claudia", studente.getNome());
        assertEquals("Barbato", studente.getCognome());
        assertEquals("c.barbato@studenti.unisa.it", studente.getEmail());
        assertNotNull(studente.getListaPrestiti());
        assertTrue(studente.getListaPrestiti().isEmpty());
    }

    @Test
    public void testGetMatricola() {
        assertEquals("0612677146", studente.getMatricola());
    }
    
    @Test
    public void testGetNome() {
        assertEquals("Claudia", studente.getNome());
    }
    
    @Test
    public void testGetCognome() {
        assertEquals("Barbato", studente.getCognome());
    }
    
    @Test
    public void testGetEmail() {
        assertEquals("c.barbato@studenti.unisa.it", studente.getEmail());
    }
     
    @Test
    public void testListaPrestiti_Empty(){
        assertTrue(studente.getListaPrestiti().isEmpty());
    }

    @Test
    public void testGetListaPrestitiString_Empty() {
        assertEquals("Nessun Libro",studente.getListaPrestitiString());
    }
    
    @Test
    public void testGetListaPrestitiString_SingleItem() {
        studente.aggiungiListaPrestiti(prestito1);
        assertEquals("Moby Dick",studente.getListaPrestitiString());
    }
    
    @Test
    public void testGetListaPrestitiString_MoreItems() {
        studente.aggiungiListaPrestiti(prestito1);
        studente.aggiungiListaPrestiti(prestito2);
        assertEquals("Moby Dick, Odissea",studente.getListaPrestitiString());
    }

    @Test
    public void testSetMatricola() {
        studente.setMatricola("");
        assertEquals("", studente.getMatricola());
    }

    @Test
    public void testSetNome() {
        studente.setNome("Alessio");
        assertEquals("Alessio", studente.getNome());
    }

    @Test
    public void testSetCognome() {
        studente.setCognome("Maccaro");
        assertEquals("Maccaro", studente.getCognome());
    }

    @Test
    public void testSetEmail() {
        studente.setEmail("a.maccaro@studenti.unisa.it");
        assertEquals("a.maccaro@studenti.unisa.it", studente.getEmail());
    }

    @Test
    public void testSetListaPrestiti() {
        ObservableList<Prestito> nuovaLista = FXCollections.observableArrayList(prestito1);
        studente.setListaPrestiti(nuovaLista);
        assertIterableEquals(nuovaLista, studente.getListaPrestiti());
    }

    @Test
    public void testAggiungiListaPrestiti_Success() {
        assertTrue(studente.aggiungiListaPrestiti(prestito1));
        assertEquals(1,studente.getListaPrestiti().size());
        assertTrue(studente.aggiungiListaPrestiti(prestito2));
        assertEquals(2,studente.getListaPrestiti().size());
        assertTrue(studente.aggiungiListaPrestiti(prestito3));
        assertEquals(3,studente.getListaPrestiti().size());
    }

    @Test
    public void testAggiungiListaPrestiti_Failure(){
        studente.aggiungiListaPrestiti(prestito1);
        studente.aggiungiListaPrestiti(prestito2);
        studente.aggiungiListaPrestiti(prestito3);
        assertFalse(studente.aggiungiListaPrestiti(prestito4));
        assertEquals(3,studente.getListaPrestiti().size());
    }
    
    @Test
    public void testRimuoviListaPrestiti_Success(){
        studente.aggiungiListaPrestiti(prestito1);
        studente.aggiungiListaPrestiti(prestito2);
        assertEquals(2,studente.getListaPrestiti().size());
        assertTrue(studente.rimuoviListaPrestiti(prestito1));
        assertEquals(1,studente.getListaPrestiti().size());
        assertFalse(studente.rimuoviListaPrestiti(prestito1));
        
    }

    @Test
    public void testToString() {
        assertEquals("0612677146: Claudia Barbato", studente.toString());
    }
}

