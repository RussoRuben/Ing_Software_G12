package gruppo12.bibliotecaunisa.data;

import gruppo12.bibliotecaunisa.repository.LibroRepository;
import gruppo12.bibliotecaunisa.repository.PrestitoRepository;
import gruppo12.bibliotecaunisa.repository.StudenteRepository;

public class BibliotecaData {

    private static final String DEFAULT_PATH = "data/stato.bin";

    private final LibroRepository libroRepo;
    private final StudenteRepository studenteRepo;
    private final PrestitoRepository prestitoRepo;

    public BibliotecaData() {
        libroRepo = new LibroRepository();
        studenteRepo = new StudenteRepository();
        prestitoRepo = new PrestitoRepository();
    }

    // Metodi getter
    public LibroRepository getLibroRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StudenteRepository getStudenteRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PrestitoRepository getPrestitoRepo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void salvaStato() {
        salvaStato(DEFAULT_PATH);
    }

    // Metodi lettura / scrittura
    public void salvaStato(String filePath) {

    }

    public void caricaStato() {
        caricaStato(DEFAULT_PATH);
    }

    @SuppressWarnings("unchecked")
    public void caricaStato(String filePath) {

    }

}
