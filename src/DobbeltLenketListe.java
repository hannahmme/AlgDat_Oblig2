import java.util.*;

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

    public DobbeltLenketListe(){
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }


    // Oppgave 1 - Hannah
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


    /**
     * Konstruktør som oppretter en dobbelt lenket liste med noder.
     * Bruker Objects sin .requireNonNull() for å sjekke om tabellen er null.
     * Current brukes som en teller, som starter på plass hode
     * og flytter seg en plass til høyre for hver ny node.
     * Antall økes hver gang en node legges til.
     * Hvis hode er null, legges det til verdi og forrige og neste-peker er lik null.
     * Deretter legges nodene til etter det, og forrige og neste-peker oppdateres.
     *
     * @param a - generisk liste
     */

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");
        Node<T> current = hode;

        for(T verdi : a) {
            //tar ikke med null-verdier
            if(verdi != null){
                Node<T> nyNode = new Node<T>(verdi);
                antall++;
                endringer++;
                if (hode == null) {
                    //setter inn verdi i hode-noden.
                    nyNode.forrige = null;
                    nyNode.neste = null;
                    hode = nyNode;
                } else {
                    //setter inn neste node i rekken
                    nyNode.forrige = current;
                    nyNode.neste = null;
                    current.neste = nyNode;
                }
                //oppdater neste, flytte current 1 fram
                current = nyNode;
            }
        }
        //setter hale-p
        hale = current;
    }

    // Oppgave 2a
    @Override
    public String toString() {
        if (antall == 0){ //Hvis den dobbeltlenkede listen er tom returneres en streng med kun klammer (tom liste)
            return "[]";
        }

        StringBuilder listeString = new StringBuilder();
        Node node = hode; //Bruker en hjelpenode som "holder" på gjeldende node, starter med listen sitt hode
        listeString.append('['); //Strengen startes med en start-klamme
        listeString.append(node.verdi); //Verdien i første node (hode) legges til strengen
        node = node.neste; //Hjelpenoden blir satt til noden etter hodet

        while(node != null){ //while-løkke som legger til noden sin verdi så lenge noden ikke er null (verdien kan være null), hjelpenoden blir satt til neste node
            listeString.append(',');
            listeString.append(' ');
            listeString.append(node.verdi);
            node = node.neste;
        }

        listeString.append(']'); //Strengen avsluttes med en slutt-klamme

        return listeString.toString(); //bruker StringBuilder sin toString()-metode for å gjøre om listeString til en tekststreng
    }

    public String omvendtString() {
        if (antall == 0){ //Hvis den dobbeltlenkede listen er tom returneres en streng med kun klammer (tom liste)
            return "[]";
        }

        StringBuilder omvendtListeString = new StringBuilder();
        Node node = hale; //Bruker en hjelpenode som "holder" på gjeldende node, starter med listen sin hale
        omvendtListeString.append('['); //Strengen startes med en start-klamme
        omvendtListeString.append(node.verdi); //Verdien i siste node (hale) legges til strengen
        node = node.forrige; //Hjelpenoden blir satt til noden før halen

        while(node != null){ //while-løkke som legger til noden sin verdi så lenge noden ikke er null (verdien kan være null), hjelpenoden blir satt til forrige node
            omvendtListeString.append(',');
            omvendtListeString.append(' ');
            omvendtListeString.append(node.verdi);
            node = node.forrige;
        }

        omvendtListeString.append(']'); //Strengen avsluttes med en slutt-klamme

        return omvendtListeString.toString(); //bruker StringBuilder sin toString()-metode for å gjøre om listeString til en tekststreng
    }


// Oppgave 2b
    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi); //sjekker om verdien er null, hvis den er null kastes en NullPointerException, hvis ikke returneres verdi.

        if(antall == 0){ //Hvis listen er tom opprettes det en hode-node som også er hale med verdien verdi, og forrigepeker og nestepeker peker til null.
            hode = hale = new Node<T>(verdi, null, null);
        }
        else{ //Hvis listen ikke er tom settes halen til neste verdi, som er en ny node med verdi som vedi, forrigepeker peker til halen, og
            hale = hale.neste = new Node<T>(verdi, hale, null);
        }

        antall++; //Øker antall noder i listen med 1 hver gang en node blir lagt til.
        endringer++; //Øker antall endringer med 1.
        return true; //Metoden returnerer true hvis en ny node har blitt lagt inn i listen.
    }



// Oppgave 3a Amalie TODO: Tror denne skal være OK
    private Node<T> finnNode(int indeks){
        int halvpart = antall/2;

        Node<T> node;

        if(indeks==0){
            node = hode;
        }

        // mindre enn halvpart, let fra hode og gå til høyre med .next
        else if(indeks<halvpart){
            node = hode;
            for(int i = 0; i == indeks; i++){
                node = node.neste;
            }
        }

        // større: let fra hale og gå til venstre med .prev
        else{
            node = hale;
            for(int i = antall; i == indeks; i--){
                node = node.forrige;
            }
        }

        return node;
    }


    // TODO: Skal hente og returnere verdien på en node, men har noen problemer med indeksen??? tror at den har indeks 99999 uansett
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false); // false betyr at indeksen ikke kan være lik antallet

        return finnNode(indeks).verdi;
    }

    // metode som oppdaterer eksisterende verdi og skriver ut gammel verdi
    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false); // Kaster et unntak om indeksen ikke allerede ligger i listen

        T oldValue = finnNode(indeks).verdi;

        if(nyverdi == null){
            throw new NullPointerException("Ikke tillatt med nullverdier!");
        }
        else {

            finnNode(indeks).verdi = nyverdi;

            endringer++;
            return oldValue;
        }
    }

    // OK. hentet fra kompendiet, delkapittel 1.2.3
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > tabellengde(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


// Oppgave 3b: skal returnere en liste som inneholder verdiene i intervallet
    //TODO: denne blir feil fordi hent-metoden er feil
    public Liste<T> subliste(int fra, int til){ //fra=hode og til=hale
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        int antallISubliste = til - fra;

        if(antallISubliste!=0) {

            for (int i = fra; i < antallISubliste; i++) {
                T verdi = hent(i);
                subliste.leggInn(verdi);
            }

        }
            return subliste;

    }

    // Oppgave 4 - Hannah
    /**
     * Metoden sjekker om verdi ligger i listen.
     * Current brukes som teller, hvor den starter på hode-noden
     * og øker med current.neste for hver iterasjon.
     * Antall er alle verdiene som ligger i listen.
     *
     * @param verdi - generisk verdi som skal finnes indeks av
     * @return - returnerer indeks til verdien om den ligger i listen,
     *           ellers -1 om verdi er null eller ikke finnes.
     */

    @Override
    public int indeksTil(T verdi) {
        Node<T> current = this.hode;
        for(int i = 0; i < antall; i++){
            if(current != null){
                if(current.verdi.equals(verdi)){
                    return i;
                }
                current = current.neste;
            }
        }
        return -1;
    }

    /**
     * Metoden sjekker om en verdi finnes i listen.
     * Benytter metoden indeksTil().
     *
     * @param verdi - verdien det letes etter
     * @return - true hvis verdien finnes i listen, ellers false
     */
    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) != -1){
            return true;
        }
        return false;
    }

    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi);

        //Må sjekke om indeks er større eller lik 0 og mindre eller lik antall

        if(antall == 0){ //Hvis listen er tom opprettes det en hode-node som også er hale med verdien verdi, og forrigepeker og nestepeker peker til null.
            hode = hale;
            hale = new Node<T>(verdi, null, null);
        }
        else if(indeks == 0){ //Hvis indeks er 0 skal noden settes inn foran
            hode = hode.forrige;
            hode.forrige= new Node<T>(verdi, null, hode);
        }
        else if(indeks == antall){ //
            hale = hale.neste;
            hale.neste= new Node<T>(verdi, hale, null);
        }
        else{
            //Sette inn verdi på ønsket indeks, verdien skal legges mellom to andre verdier
        }


        antall++; //Øker antall noder i listen med 1 hver gang en ny node blir lagt inn.
        endringer++; //Øker antall endringer med 1.

    }


    //Todo: Jeg har kommentert ut innholdet i metoden i oppg.6
    //todo: enn så lenge, fordi det ga heap-Exception eller noe.
    //todo: Testen klarte ikke kjøre lengre enn til oppgave 5 pga det.
    // - Hannah
// Oppgave 6 Amalie
    @Override
    public boolean fjern(T verdi) {
/*        if(verdi == null){throw new NoSuchElementException("nullverdi");}

        if(antall>0) {
            for (int i = 0; i < antall; i++) {
                if (finnNode(i).verdi == verdi) {

                    if(antall == 1){
                        hode.verdi = null;
                        hode = hode.neste;
                        hale = null;
                    }

                    if(finnNode(i).neste == hale){ // hvis neste er hale
                        hale.forrige = finnNode(i-1);
                    }
                    if(finnNode(i).forrige == hode){ // hvis forrige er hode
                        hode.neste = finnNode(i+1);
                    }

                    finnNode(i-1).neste = finnNode(i+1);
                    finnNode(i+1).forrige = finnNode(i-1).neste;

                    endringer++;
                    antall--;
                    return true;
                }
            }
        }
        return false;*/
    return true;
    }

// TODO: denne blir feil fordi hent() er feil
    //Todo: Jeg har kommentert ut innholdet i metoden i oppg. 6
    //todo: enn så lenge, fordi det ga heap-Exception eller noe.
    //todo: Testen klarte ikke kjøre lengre enn til oppgave 5 pga det.
    // - Hannah
    @Override
    public T fjern(int indeks) {
        /*indeksKontroll(indeks, false);

        T utVerdi = hent(indeks);

        // finner noden som skal fjernes
        Node<T> denne = finnNode(indeks);

        // tilegner forrige node sin neste den som skal fjernes sin neste
        if(antall == 1){
            hode.verdi = null;
            hode = hode.neste;
            hale = null;
        }

        if(denne.neste == hale){ // hvis neste er hale
            hale.forrige = finnNode(indeks-1);
        }

        if(denne.forrige == hode){ // hvis forrige er hode
            hode.neste = finnNode(indeks+1);
        }

        finnNode(indeks-1).neste = finnNode(indeks+1);
        finnNode(indeks+1).forrige = finnNode(indeks-1).neste;

        antall--;

        System.out.print(utVerdi);
        return utVerdi;*/
        return (T) "hello";
    }


// Oppgave 7 - Hannah

    /**
     * Metode som nullstiller dobbeltlenkede listen med noder.
     * Den bruker to hjelpevariabler, en som en teller og den andre
     * som en midltertidig lagringsplass så vi ikke mister current.neste.
     */
    @Override
    public void nullstill() {
        Node current = this.hode;
        while(current != hale){
            System.out.println(antall);
            current.verdi = null;
            current.forrige = null;
            Node temp = current.neste;
            current.neste = null;
            current = temp;
            endringer++;
            antall--;
        }
        hode = hale = null;
        antall--;
        endringer++;
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


// Oppgave 9 Amalie - kan ikke testes før oppg 8 er gjort
        @Override
        public void remove(){

            // noe som skal kastes her, se oppg

            if(endringer!=iteratorendringer){
                throw new ConcurrentModificationException("feil i oppg 9");
            }

            fjernOK = false;

            // nuller ut hode og hale hvis den som fjernes er eneste verdi
            if(antall == 1){
                hode.verdi = null;
                hode = hode.neste;
                hale = null;
            }

            if(denne == null){ // hvis neste er hale
                hale.forrige = denne.forrige.forrige;
            }

            if(denne.forrige == hode){ // hvis forrige er hode
                hode.neste = denne;
            }

            else {
                denne.forrige = denne.forrige.forrige;
                denne.forrige.forrige.neste = denne;
            }

            antall--;
            iteratorendringer++;
            endringer++;
        }

    } // class DobbeltLenketListeIterator



// Oppgave 10: ikke bruk hjelpestrukturer
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {



        throw new UnsupportedOperationException();
    }




} // class DobbeltLenketListe
