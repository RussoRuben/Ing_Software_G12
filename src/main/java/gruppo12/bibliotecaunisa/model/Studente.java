package gruppo12.bibliotecaunisa.model;

public class Studente {

    private String matricola;

    private String nome;

    private String cognome;

    private String email;

    private ObservableList<Prestito> listaPrestit;

    public void writeObject(ObjectOutputStream out) {
    }

    public void readObject(ObjectInputStream in) {
    }

    public long getMatricola() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCognome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<Prestito> getListaPrestiti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getListaPrestitiString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMatricola(long matricola) {
    }

    public void setNome(String nome) {
    }

    public void setCognome(String cognome) {
    }

    public void setEmail(String email) {
    }

    public void setListaPrestiti(ObservableList<Prestito> listaPrestiti) {
    }

    public boolean aggiungiListaPrestiti(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rimuoviListaPrestiti(Prestito prestito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
