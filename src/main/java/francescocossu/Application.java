package francescocossu;

import francescocossu.entities.Lettura;
import francescocossu.entities.Libro;
import francescocossu.entities.Periodicità;
import francescocossu.entities.Rivista;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        selezionaOpzioneLibreria(compilaLibreria());

    }

    public static List<Lettura> compilaLibreria() {
        List<Lettura> libreria = new ArrayList<>();

        Libro mobyDick = new Libro("00001", "Moby Dick", 1851, 1036, "Herman Melville", "Romanzo");
        Libro harryPotter = new Libro("00002", "Harry Potter", 1997, 223, "J.K. Rowling", "Fantasy");
        Libro theHobbit = new Libro("00003", "The Hobbit", 1937, 295, "J.R.R. Tolkien", "Fantasy");
        Libro theLordOfTheRings = new Libro("00004", "The Lord of the Rings", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
        Libro leAvventureDiPinocchio = new Libro("00005", "Le Avventure di Pinocchio", 1940, 167, "Carlo Collodi", "Romanzo");
        Rivista Focus = new Rivista("00005", "Focus", 1997, 32, Periodicità.SETTIMANALE);
        Rivista TheGuardian = new Rivista("00006", "The Guardian", 2019, 32, Periodicità.MENSILE);
        Rivista TheNewYorkTimes = new Rivista("00007", "The New York Times", 2019, 32, Periodicità.SEMESTRALE);
        Rivista Forbes = new Rivista("00008", "Forbes", 2019, 32, Periodicità.SEMESTRALE);

        libreria.add(mobyDick);
        libreria.add(harryPotter);
        libreria.add(theHobbit);
        libreria.add(theLordOfTheRings);
        libreria.add(leAvventureDiPinocchio);
        libreria.add(Focus);
        libreria.add(TheGuardian);
        libreria.add(TheNewYorkTimes);
        libreria.add(Forbes);

        System.out.println("Ecco una lista dei libri presenti nell'archivio:");
        for (Lettura l : libreria) {
            System.out.println(l.toString());
        }
        System.out.println("---------------------------------------------------------------------------------------------");
        return libreria;
    }


    public static void selezionaOpzioneLibreria(List<Lettura> libreria) {
        while (true) {


            System.out.println("Seleziona 1 per aggiungere un libro o rivista, 2 per rimuovere un libro o rivista, 3 per cercare un libro o rivista, 4 per mostrare la libreria, 5 per salvare/caricare o 0 per uscire");
            String response = scanner.nextLine();
            if (response.equals("0")) {
                break;
            }
            switch (response) {
                case "1":
                    sceltaLibroRiv(libreria);
                    break;
                case "2":
                    rimuoviLettura(libreria);
                    break;
                case "3":
                    System.out.println("Inserisci il parametro da cercare: 1-Anno 2-Autore 3-ISBN");
                    String risposta = scanner.nextLine();
                    switch (risposta) {
                        case "1":
                            ricercaAnnoPubblicazione(libreria);
                            break;
                        case "2":
                            ricercaAutore(libreria);
                            break;
                        case "3":
                            ricercaPerISBN(libreria);
                            break;
                        default:
                            System.out.println("Risposta non valida, inserire un numero da 1 a 3.");
                    }
                    break;
                case "4":
                    stampaLibreria(libreria);
                    break;
                case "5":
                    System.out.println("Vuoi salvare o caricare la libreria? 1-Salva 2-Carica");
                    String risposta3 = scanner.nextLine();
                    if (risposta3.equals("1")) {
                        salvataggioSuDisco(libreria);
                    } else if (risposta3.equals("2")) {
                        leggiDaDisco();
                    }

                    break;
                default:
                    System.out.println("Risposta non valida, inserire un numero da 0 a 5.");
            }
        }
    }

    public static void sceltaLibroRiv(List<Lettura> libreria) {
        System.out.println("Quale tipo di lettura vuoi inserire? 1-Libro / 2-Rivista");
        String risposta2 = scanner.nextLine();
        if (risposta2.equals("1")) {
            creazioneLibro(libreria);
        } else if (risposta2.equals("2")) {
            creazioneRivista(libreria);
        } else {
            System.out.println("Risposta non valida, inserire 1 o 2.");
            sceltaLibroRiv(libreria);
        }


    }

    public static void creazioneLibro(List<Lettura> libreria) {
        System.out.println("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        for (Lettura lettura : libreria) {
            if (lettura.getISBN().equals(isbn)) {
                System.out.println("Esiste già un libro con lo stesso ISBN nella libreria");
                break;
            }
        }

        System.out.println("Inserisci titolo: ");
        String titolo = scanner.nextLine();
        boolean isValid = false;
        int annoPubblicazione = 0;
        while (!isValid) {
            System.out.println("Inserisci anno di pubblicazione: ");
            try {
                annoPubblicazione = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero valido per l'anno di pubblicazione");
            }
        }

        isValid = false;
        int numeroPagine = 0;
        while (!isValid) {
            System.out.println("Inserisci numero di pagine: ");
            try {
                numeroPagine = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero valido per il numero di pagine");
            }
        }
        System.out.println("Inserisci autore");
        String autore = scanner.nextLine();
        System.out.println("Inserisci genere");
        String genere = scanner.nextLine();

        Libro nuovoLibro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);

        libreria.add(nuovoLibro);

        System.out.println("Libro aggiunto alla libreria!");
    }

    public static void creazioneRivista(List<Lettura> libreria) {
        System.out.println("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        for (Lettura lettura : libreria) {
            if (lettura.getISBN().equals(isbn)) {
                System.out.println("Esiste già un libro con lo stesso ISBN nella libreria");
                break;
            }
        }

        System.out.println("Inserisci titolo: ");
        String titolo = scanner.nextLine();
        boolean isValid = false;
        int annoPubblicazione = 0;
        while (!isValid) {
            System.out.println("Inserisci anno di pubblicazione: ");
            try {
                annoPubblicazione = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero valido per l'anno di pubblicazione");
            }
        }

        isValid = false;
        int numeroPagine = 0;
        while (!isValid) {
            System.out.println("Inserisci numero di pagine: ");
            try {
                numeroPagine = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserire un numero valido per il numero di pagine");
            }
        }
        Periodicità periodicita = null;
        while (periodicita == null) {
            System.out.println("Inserisci Periodicità (1-SETTIMANALE 2-MENSILE 3-SEMESTRALE)");
            int periodicitaInput = Integer.parseInt(scanner.nextLine());

            switch (periodicitaInput) {
                case 1:
                    periodicita = Periodicità.SETTIMANALE;
                    break;
                case 2:
                    periodicita = Periodicità.MENSILE;
                    break;
                case 3:
                    periodicita = Periodicità.SEMESTRALE;
                    break;
                default:
                    System.out.println("Periodicità non valida. Riprova.");
                    break;
            }
        }

        Rivista nuovaRivista = new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, periodicita);

        libreria.add(nuovaRivista);

        System.out.println("Rivista aggiunta alla libreria!");
    }

    public static void rimuoviLettura(List<Lettura> libreria) {
        System.out.println("Inserisci l'ISBN della lettura da rimuovere: ");
        String isbn = scanner.nextLine();
        Optional<Lettura> letturaDaRimuovere = libreria.stream().filter(lettura -> lettura.getISBN().equals(isbn)).findFirst();
        if (letturaDaRimuovere.isPresent()) {
            libreria.remove(letturaDaRimuovere.get());
            System.out.println("Lettura rimossa con successo.");
        } else {
            System.out.println("L'ISBN non corrisponde a nessuna lettura presente nell'archivio.");
        }
    }

    public static void ricercaAnnoPubblicazione(List<Lettura> libreria) {
        System.out.println("Inserisci l'anno di pubblicazione per la ricerca");
        String annoInput = scanner.nextLine();
        try {
            int annoPubblicazione = Integer.parseInt(annoInput);
            Stream<Lettura> streamLibreria = libreria.stream();
            List<Lettura> libreriaRisultati = streamLibreria.filter(el -> el.getAnnoPubblicazione() == annoPubblicazione).toList();
            if (libreriaRisultati.isEmpty()) {
                System.out.println("Nessun risultato corrispondente.");
            } else {
                System.out.println("Ecco i risultati della ricerca: ");
                for (Lettura lettura : libreriaRisultati) {
                    System.out.println(lettura.toString());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("L'anno di pubblicazione deve essere un numero intero.");
        }
    }

    public static void ricercaAutore(List<Lettura> libreria) {
        System.out.println("Inserisci il nome dell'autore per la ricerca: ");
        String autore = scanner.nextLine();

        List<Lettura> libriAutore = libreria.stream().filter(elemento -> elemento instanceof Libro).filter(elemento -> ((Libro) elemento).getAutore().equals(autore)).toList();


        if (!libriAutore.isEmpty()) {
            System.out.println("Ecco i risultati della ricerca:");
            for (Lettura libro : libriAutore) {
                System.out.println(libro.toString());
            }
        } else {
            System.out.println("Nessun libro trovato per l'autore- " + autore);
        }

    }

    public static void ricercaPerISBN(List<Lettura> libreria) {
        System.out.println("Inserisci l'ISBN per la ricerca: ");
        String ISBN = scanner.nextLine();
        Optional<Lettura> letturaISBN = libreria.stream().filter(lettura -> lettura.getISBN().equals(ISBN)).findFirst();
        if (letturaISBN.isPresent()) {
            System.out.println("La lettura corrispondente a questo ISBN è " + letturaISBN.get());
        } else {
            System.out.println("L'ISBN non corrisponde a nessuna lettura presente nell'archivio.");
        }
    }

    public static void stampaLibreria(List<Lettura> libreria) {
        System.out.println("Ecco la libreria: ");
        for (Lettura lettura : libreria) {
            System.out.println(lettura.toString());
        }
    }

    public static void salvataggioSuDisco(List<Lettura> libreria) {
        File file = new File("src/main/java/francescocossu/entities/libreria.txt");
        try {
            StringBuilder testo = new StringBuilder();
            for (Lettura lettura : libreria) {
                testo.append(lettura.toString()).append("#");
            }
            FileUtils.writeStringToFile(file, testo.toString(), "UTF-8");
            System.out.println("File salvato con successo");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del file");
        }

    }


    //caricamento della lista semplice perchè quello con oggetti era troppo complesso :(
    public static void leggiDaDisco() {
        File file = new File("src/main/java/francescocossu/entities/libreria.txt");
        try {
            String letture = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            ArrayList<String> lettureList = new ArrayList<>(Arrays.asList(letture.split("#")));
            System.out.println("Prodotti caricati: " + lettureList);

            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

