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

        if(antall == 0){ //Hvis listen er tom opprettes det en node som både hode og hale peker på, med verdien verdi, og forrigepeker og nestepeker peker til null.
            hale = new Node<T>(verdi, null, null);
            hode = hale;
        }
        else{ //Hvis listen ikke er tom opprettes en ny node med verdien verdi, med forrigepeker til hale og nestepeker til null. Den er hale sin neste, hale settes til å peke på hale.neste
            hale.neste = new Node<T>(verdi, hale, null);
            hale = hale.neste;
        }

        antall++; //Øker antall noder i listen med 1 hver gang en node blir lagt til.
        endringer++; //Øker antall endringer med 1.
        return true; //Metoden returnerer true hvis en ny node har blitt lagt inn i listen.
    }



// Oppgave 3a Amalie
    private Node<T> finnNode(int indeks){
        int halvpart = antall/2;

        Node<T> node;

        if(indeks==0){
            node = hode;
        }

        // mindre enn halvpart, let fra hode og gå til høyre med .next
        else if(indeks<=halvpart){
            node = hode;
            for(int i = 0; i < indeks; i++){
                node = node.neste;
            }
        }

        // større: let fra hale og gå til venstre med .prev
        else{
            node = hale;
            for(int i = antall-1; i > indeks; i--){
                node = node.forrige;
            }
        }

        return node;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false); // false betyr at indeksen ikke kan være lik antallet
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
    public Liste<T> subliste(int fra, int til){ //fra=hode og til=hale
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        for (int i = fra; i < til; i++) {
            T verdi = hent(i);
            subliste.leggInn(verdi);
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
        Objects.requireNonNull(verdi); //Kaster et unntak dersom verdi er null
        indeksKontroll(indeks, true); //Kaster et unntak dersom indeksen er mindre enn null og større enn antall

        if(antall == 0){ //Hvis listen er tom opprettes det en node som både hode og hale peker på, med verdien verdi, og forrigepeker og nestepeker peker til null.
            hale = new Node<T>(verdi, null, null);
            hode = hale;
        }
        else if(indeks == 0){ //Hvis indeks er 0 skal noden settes inn foran
            hode.forrige= new Node<T>(verdi, null, hode);
            hode = hode.forrige;
        }
        else if(indeks == antall){ //Hvis indeks er det samme som antall skal noden settes inn på slutten, og halen skal peke på denne
            hale.neste= new Node<T>(verdi, hale, null);
            hale = hale.neste;
        }
        else{ //Setter en inn en verdi mellom to andre verdier
            //Oppretter de tre relevante nodene p, r, q
            Node p = finnNode(indeks-1); //Bruker metoden finnNode() til at node p peker på noden med indeksen indeks-1
            Node r = finnNode(indeks); //Node r peker på noden med indeksen indeks, som den nye noden skal peke på
            Node q = new Node<T>(verdi, p, r); //Node q peker på en ny node med verdien verdi, som har forrigepeker til p og nestepeker til r

            r = q.neste; //Noden r settes til å peke på q sin neste
            p.neste = q; //Setter at p sin nestepeker peker på q
            r.forrige = q; //Setter at r sin forrigepeker peker på q
        }

        antall++; //Øker antall noder i listen med 1 hver gang en ny node blir lagt inn.
        endringer++; //Øker antall endringer med 1.
    }


// Oppgave 6 Amalie
    @Override
    public boolean fjern(T verdi) {

        if(verdi == null){return false; }

        // noden som skal fjernes
        Node<T> node = hode;

        do{
            if (node.verdi.equals(verdi)) {    // hvis den finner verdien i listen
                break;      // går ut av loopen, aktuell node er funnet
            }
            node = node.neste;
        } while(node!=null);



        // fant ikke verdien, returnerer false
        if(node == null) { // hvis noden er null har man altså ikke funnet den i listen
            return false;
        }

        // hvis det bare er én node
        else if (antall == 1) {
            hode = null;
            hale = null;
        }

        // hvis neste er hale/ siste node skal fjernes
        else if (node == hale) {
            hale = hale.forrige;
            hale.neste = null;
        }

        // hvis forrige er hode/ første skal fjernes
        else if (node == hode) {
            hode = hode.neste;
            hode.forrige = null;
        }

        else {
            node.forrige.neste = node.neste;      // den forrige sin neste peker på den neste
            node.neste.forrige = node.forrige;    // den neste sin forrige er denne sin forrige
        }

        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        // finner noden som skal fjernes
        Node<T> denne = hale;

        // tilegner forrige node sin neste den som skal fjernes sin neste
        if(antall == 1){
            hode = null;
            hale = null;
        }

        else if(indeks == antall-1){ // hvis neste er hale/ siste node skal fjernes
            hale = hale.forrige;
            hale.neste = null;
        }

        else if(indeks == 0){ // hvis forrige er hode/ første skal fjernes
            denne = hode;
            hode = hode.neste;
            hode.forrige = null;
        }

        else {
            denne = finnNode(indeks);
            denne.forrige.neste = denne.neste;      // den forrige sin neste peker på den neste
            denne.neste.forrige = denne.forrige;    // den neste sin forrige er denne sin forrige
        }

        T utVerdi = denne.verdi;

        antall--;
        endringer++;

        return utVerdi;
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
            current.verdi = null;
            current.forrige = null;
            //Lagringsplass for neste node i rekken, før vi gjør current.neste-peker til null.
            Node temp = current.neste;
            current.neste = null;
            //telleren "current" flyttes en plass fram
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
        return new DobbeltLenketListeIterator();
    }

// 8d: sjekk om indeksen er lovlig. se så oppgavetekst
    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException("Listen har ingen noder.");
            }
            fjernOK = true;
            //hjelpevariabel for å lagre verdien til denne
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
        }

// 8c: sette pekeren denne til noden som hører til den oppgitte indeksen
    private DobbeltLenketListeIterator(int indeks){
            Node nodePaaIndeks = finnNode(indeks);
            denne = nodePaaIndeks;
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

// NB!! Denne koden skal ikke endres
        @Override
        public boolean hasNext(){
            if(denne != null){
                return true;
            }
            return false;
        }


// Oppgave 9 Amalie - TODO: kan ikke testes før oppg 8 er gjort
        @Override
        public void remove(){

            if(denne.forrige==null || antall == 0 || !fjernOK){
                throw new IllegalStateException("Kan ikke fjerne noden!");
            }

            if(endringer!=iteratorendringer){
                throw new ConcurrentModificationException("feil i oppg 9");
            }

            fjernOK = false;

            // noden som skal fjernes
            Node<T> node = hode;

            // nuller ut hode og hale hvis den som fjernes er eneste verdi
            if(antall == 1){
                hode = null;
                hale = null;
            }

            else if(node.neste == null){ // hvis neste er hale/ siste node skal fjernes
                hale = node.forrige;
                hale.neste = null;
            }

            else if(node.forrige == null){ // hvis forrige er hode/ første skal fjernes
                hode = denne;
                hode.forrige = null;
            }

            else {
                node = denne.forrige;
                node.forrige.neste = denne;      // den forrige sin neste peker på den neste
                denne.forrige = node.forrige;    // den neste sin forrige er denne sin forrige
            }

            antall--;
            iteratorendringer++;
            endringer++;
        }

    } // class DobbeltLenketListeIterator


    
    // Oppgave 10: ikke bruk hjelpestrukturer
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        DobbeltLenketListe<T> list = (DobbeltLenketListe<T>) liste;

        Node<T> current = list.hode;
        Node<T> currentSinNext = current.neste;
        Node<T> temp = null;
        while (current != list.hale) {
            if (c.compare(current.verdi, currentSinNext.verdi) == 1) {
                temp = current;
                current = currentSinNext;
                currentSinNext = temp;
                current = current.neste;
            }
            if (current.verdi.equals(currentSinNext.verdi)) {

            }


        }

    }
}

    // class DobbeltLenketListe
