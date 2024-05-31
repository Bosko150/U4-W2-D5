package francescocossu;

import francescocossu.entities.Lettura;
import francescocossu.entities.Libro;
import francescocossu.entities.Periodicità;
import francescocossu.entities.Rivista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Lettura> libreria = new ArrayList<Lettura>();

        sceltaLibroRiv(libreria);

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

        System.out.println("Libro aggiunto all'archivio!");
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

        System.out.println("Rivista aggiunta all'archivio!");
    }
}
