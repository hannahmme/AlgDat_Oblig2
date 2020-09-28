import java.util.Comparator;
import java.util.Iterator;

//hentet fra tilleggsklasser som fulgte med oppgaven 

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        throw new UnsupportedOperationException();
    }





// Oppgave 1
    // metoden skal returnere antall verdier
    @Override
    public int antall() {
        throw new UnsupportedOperationException();
    }

    // metoden skal returnere true/ false om den er tom eller ikke
    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();
    }

    // metoden skal lage en dobbeltlenket liste med verdiene fra tabell a
    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();
    }


    // Oppgave 2a
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }


// Oppgave 2b
    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }









// Oppgave 3a Amalie
    // denne metoden skal returnere noden med den gitte indeksen/posisjonen
    /*private Node<T> finnNode(int indeks){

    }*/

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }


// Oppgave 3b Amalie
    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }




    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }


// Oppgave 6 Amalie
    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }


    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }


// Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }


//8b: skal returnere en instans av iteratorklassen
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

// 8d: sjekk om indeksen er lovlig. se så oppgavetekst
    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        // tror ikke denne skal endres?
        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }


// 8a: se oppgavetekst
        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }
// 8c: sette pekeren denne til noden som hører til den oppgitte indeksen
        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        // NB!! Denne koden skal ikke endres
        @Override
        public boolean hasNext(){
            return denne != null;
        }




// Oppgave 9 Amalie
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator



// Oppgave 10: ikke bruk hjelpestrukturer
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }




} // class DobbeltLenketListe
