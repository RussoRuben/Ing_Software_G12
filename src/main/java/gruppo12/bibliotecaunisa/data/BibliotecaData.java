package gruppo12.bibliotecaunisa.data;

import gruppo12.bibliotecaunisa.repository.LibroRepository;
import gruppo12.bibliotecaunisa.repository.PrestitoRepository;
import gruppo12.bibliotecaunisa.repository.StudenteRepository;

/**
 * @brief Classe che contiene i repository del sistema 
 * @class BibliotecaData
 * 
 */
public class BibliotecaData {

    private static final String DEFAULT_PATH = "data/stato.bin";

    private final LibroRepository libroRepo;
    private final StudenteRepository studenteRepo;
    private final PrestitoRepository prestitoRepo;

    /**
     * @brief Costruttore: inizializza i repository dei libri, studenti e prestiti  
     */
    public BibliotecaData() {
        libroRepo = new LibroRepository();
        studenteRepo = new StudenteRepository();
        prestitoRepo = new PrestitoRepository();
    }

    // Metodi getter
    /**
     * @return Restituisce il repository dei libri
     */
    public LibroRepository getLibroRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     /**
     * @return Restituisce il repository degli studenti
     */
    public StudenteRepository getStudenteRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     /**
     * @return Restituisce il repository dei prestiti
     */
    public PrestitoRepository getPrestitoRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     /**
     * @brief Salva lo stato corrente del sistema utilizzando il percorso predefinito 
     */
    public void salvaStato() {
        salvaStato(DEFAULT_PATH);
    }

    // Metodi lettura / scrittura
     
    /**
     * @brief Salva lo stato corrente del sistema 
     * @param filePath Percorso del file su cui salvare lo stato
     */
    public void salvaStato(String filePath) {

    }

     /**
     * @brief Carica lo stato del sistema utilizzando il percorso predefinito
     */
    public void caricaStato() {
        caricaStato(DEFAULT_PATH);
    }

    /**
     * @brief Carica lo stato del sistema dal file indicato 
     * @param filePath Percorso del file da cui caricare lo stato
     */
    @SuppressWarnings("unchecked")
    public void caricaStato(String filePath) {

    }

}
