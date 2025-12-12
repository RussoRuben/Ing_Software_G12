package gruppo12.bibliotecaunisa.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @brief Modello dati che rappresenta un Prestito
 * @class Prestito
 */
public class Prestito {

    /**
     * @brief Costante per generare il numero seriale del prestito
     */
    private static long num = 0;
    
    
    private long codice;
    private Studente studente;
    private Libro libro;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    
    /**
     * @brief Costruttore
     * @param studente Oggetto Studente
     * @param libro Oggetto Libro
     * @param dataInizio data di inizio del prestito
     * @param dataFine  data prevista di fine del prestito
     */
    public Prestito(Studente studente, Libro libro, LocalDate dataInizio, LocalDate dataFine) {
        this.codice = ++num;
        this.studente = studente;
        this.libro = libro;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;        
    }

    /**
     * @brief Imposta il numero seriale del prestito
     * @param nuovoNum numero prestito
     */
    public static void setNum(long nuovoNum) {
        num=nuovoNum;
    }

    /**
     * @return Restituisce il numero seriale del prestito
     */
    public static long getNum() {
        return num;
    }
    
    /**
     * @return Restituisce il codice del prestito
     */
    public long getCodice() {
        return codice;
    }

    /**
     * @return Restituisce lo Studente che richiede il prestito
     */
    public Studente getStudente() {
       return studente;
    }

    /**
     * @return Restituisce lo Studente che richiede il prestito formattato come stringa
     */
    public String getStudenteString() {
       return studente.toString();
    }

    /**
     * @return Restituisce il libro da dare in prestito
     */
    public Libro getLibro() {
        return libro;
    }

    /** 
     * @return Restituisce il libro da dare in prestito formattato come stringa
     */
    public String getLibroString() {
       return libro.getCodice() + ": " + libro.getTitolo();
    }

    /**
     * @return Restituisce la data di inizio del prestito
     */
    public LocalDate getDataInizio() {
       return dataInizio;
    }

    /**
     * @return  Restituisce la data prevista di fine prestito
     */
    public LocalDate getDataFine() {
        return dataFine;
    }
    
    /**
     * @brief Imposta la data prevista di fine prestito
     * @param dataFine 
     */
    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
}
