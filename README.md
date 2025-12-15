# 📚 Biblioteca UNISA

Applicazione Java per la gestione di una biblioteca universitaria, con supporto a **JavaFX**, persistenza su file e test automatici.

## Funzionalità
- Gestione di **Libri**
- Gestione di **Studenti**
- Gestione di **Prestiti**
- Archiviazione dei prestiti restituiti
- Salvataggio e ripristino dello stato dell’applicazione

## Architettura
- **Model**: `Libro`, `Studente`, `Prestito`
- **Repository**: gestione dei dati tramite `ObservableList`, `HashMap`
- **Service**: logica applicativa (`BibliotecaService`)
- **Data**: persistenza tramite serializzazione su file

## Tecnologie
- Java
- JavaFX
- JUnit 5

## Autori
- Progetto sviluppato dal **Gruppo 12**
- Corso di **Ingegneria del Software** - Università degli Studi di Salerno
