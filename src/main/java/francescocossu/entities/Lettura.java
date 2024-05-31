package francescocossu.entities;

public abstract class Lettura {
    private final String ISBN;
    private final String titolo;
    private final int annoPubblicazione;
    private final int numeroPagine;

    public Lettura(String ISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }


    @Override
    public String toString() {
        return "Lettura--- ISBN= " + ISBN + ", titolo= " + titolo + ", anno pubblicazione= " + annoPubblicazione
                + ", pagine= " + numeroPagine + " ";
    }
}




