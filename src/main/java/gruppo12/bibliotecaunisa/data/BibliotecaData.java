package gruppo12.bibliotecaunisa.data;

import gruppo12.bibliotecaunisa.model.Libro;
import gruppo12.bibliotecaunisa.model.Prestito;
import gruppo12.bibliotecaunisa.model.Studente;
import gruppo12.bibliotecaunisa.repository.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        return libroRepo;
    }

    public StudenteRepository getStudenteRepo() {
        return studenteRepo;
    }

    public PrestitoRepository getPrestitoRepo() {
        return prestitoRepo;
    }

    public void salvaStato() {
        salvaStato(DEFAULT_PATH);
    }

    // Metodi lettura / scrittura
    public void salvaStato(String filePath) {

        File cartella = new File("data");
        if (!cartella.exists()) {
            cartella.mkdirs();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {

            List<Libro> libriSerializzabili = new ArrayList<>(libroRepo.getAll());
            List<Studente> studentiSerializzabili = new ArrayList<>(studenteRepo.getAll());
            List<Prestito> prestitiSerializzabili = new ArrayList<>(prestitoRepo.getAll());
            List<Prestito> prestitiArchiviatiSerializzabili = new ArrayList<>(prestitoRepo.getAllArchiviati());

            out.writeObject(libriSerializzabili);
            out.writeObject(studentiSerializzabili);
            out.writeObject(prestitiSerializzabili);
            out.writeObject(prestitiArchiviatiSerializzabili);

            System.out.println("Stato salvato su: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void caricaStato() {
        caricaStato(DEFAULT_PATH);
    }

    @SuppressWarnings("unchecked")
    public void caricaStato(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File di stato non trovato.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            List<Libro> libriCaricati = (List<Libro>) in.readObject();
            List<Studente> studentiCaricati = (List<Studente>) in.readObject();
            List<Prestito> prestitiCaricati = (List<Prestito>) in.readObject();
            List<Prestito> prestitiArchiviatiCaricati = (List<Prestito>) in.readObject();

            libroRepo.replaceAll(libriCaricati);
            studenteRepo.replaceAll(studentiCaricati);
            prestitoRepo.replaceAll(prestitiCaricati);
            prestitoRepo.replaceAllArchiviati(prestitiArchiviatiCaricati);

            long maxAttivi = prestitiCaricati.stream().mapToLong(Prestito::getCodice).max().orElse(0);
            long maxArchiviati = prestitiArchiviatiCaricati.stream().mapToLong(Prestito::getCodice).max().orElse(0);

            if (maxAttivi > maxArchiviati) {
                Prestito.setNum(maxAttivi);
            } else {
                Prestito.setNum(maxArchiviati);
            }

            System.out.println("Stato caricato.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
