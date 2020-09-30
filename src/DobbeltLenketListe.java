import java.awt.datatransfer.StringSelection;
import java.util.*;

//hentet fra tilleggsklasser som fulgte med oppgaven 

public class DobbeltLenketListe<T> implements Liste<T> {
    T[] a = (T[]) new Object[]{};

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

    public DobbeltLenketListe(){
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }


    // Oppgave 1
    // metoden skal returnere antall verdier
    @Override
    public int antall() {
        return this.antall;
    }

    // metoden skal returnere true/ false om den er tom eller ikke
    @Override
    public boolean tom() {
        if(antall == 0){
            return true;
        }
        return false;
    }

    // metoden skal lage en dobbeltlenket liste med verdiene fra tabell a
    //den sjekker med metoden fra Objects-klassen requireNonNull at tabellen ikke er null
    //Metoden har en teller kalt current. Den starter ved hodet og flytter seg ett hakk frem for hver
    //ny node.
    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");
        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();
        Node current = liste.hode;

        for(T verdi : a) {
            //tar ikke med null-verdier
            if (verdi != null) {
                antall++;
                Node nyNode = new Node(verdi);

                //setter inn verdi i hode-noden.
                if (liste.hode == null) {
                    liste.hode = nyNode;
                    liste.hode.forrige = null;
                    liste.hode.neste = null;
                }else{
                    //setter inn neste node i rekken
                    current.neste = nyNode;
                    nyNode.forrige = current;
                    nyNode.neste = null;
                }
                //oppdater neste, flytte current 1 fram
                current = nyNode;
            }
        }
        //setter hale-p
        liste.hale = current;
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









// Oppgave 3a Amalie TODO: Tror denne skal være OK
    private Node<T> finnNode(int indeks){
        int halvpart = antall/2;
        Node<T> nodePaIndeks = null;

        Node<T> node;

        // mindre enn halvpart, let fra hode og gå til høyre med .next
        if(indeks<halvpart){
            node = hode;
            for(int i = 0; i == indeks; i++){
                nodePaIndeks = node.neste;
            }
        }

        // større: let fra hale og gå til venstre med .prev
        else{
            node = hale;
            for(int i = antall-1; i == indeks; i--){
                nodePaIndeks = node.forrige;
            }
        }

        return nodePaIndeks;
    }


    // skal hente og returnere verdien på en node sin indeks
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false); // false betyr at indeksen ikke kan være lik antallet
        return finnNode(indeks).verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false); // Kaster et unntak om indeksen ikke allerede ligger i listen

        Node<T> enNode = finnNode(indeks);
        T oldValue = enNode.verdi;
        enNode.verdi = nyverdi;

        endringer++;
        return oldValue;
    }

    // hentet fra kompendiet, delkapittel 1.2.3
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > tablengde(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


// Oppgave 3b: skal returnere en liste som inneholder verdiene i intervallet
    public Liste<T> subliste(int fra, int til){ //fra=hode og til=hale
        fratilKontroll(antall, fra, til); //evt antall-1?

        Liste<T> subliste = null;

        for(int i = 0; i < antall; i++){
            T leggInn = hent(fra);
            subliste.leggInn(i, leggInn);
            fra++;
        }

        return subliste;
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
        if(verdi == null){throw new NoSuchElementException("nullverdi");}

        for(int i = 0; i<antall; i++){
            if(finnNode(i).verdi == verdi){
                finnNode(i).verdi = null;
                return true;
            }

        }

        return false;
    }

// TODO: test denne
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        T utVerdi = hent(indeks);

        // finner noden som skal fjernes
        Node<T> skalFjernes = finnNode(indeks);

        // tilegner forrige node sin neste den som skal fjernes sin neste
        Node<T> nesteNode = skalFjernes.neste;
        Node<T> forrigeNode = skalFjernes.forrige;
        skalFjernes.neste = null;

        forrigeNode.neste = nesteNode;
        nesteNode.forrige = forrigeNode;

        return utVerdi;
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
