#Obligatorisk oppgave 2 - Algoritmer og Datastrukturer
Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. Oppgaven er levert av følgende studenter:

#Arbeidsfordeling
* Amalie Christine Leiknes, S340559, s340559@oslomet.no
* Caroline Sofie Jetteberg, S313564, s313564@oslomet.no
* Hannah Marie Maurstad Eriksen, S325340, s325340@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt.
Vi har 80 commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:

Hannah har hatt hovedansvar for oppgave 1, 4, og 7.
Caroline har hatt hovedansvar for oppgave 2, 5, og 8.
Amalie har hatt hovedansvar for oppgave 3, 6 og 9.
Vi har i fellesskap løst oppgave 10.

# Beskrivelse av oppgaveløsning

* Oppgave 1: Løste ved å implementere metodene int antall() og boolean tom(). Disse skulle returnere antall
             og true/false dersom listen var tom. Lagde konstruktør for DobbeltLenketListe-klassen. 
             Den skulle ta inn en generisk tabell a, som hver verdi i a skulle bli verdier i noder som skulle bli en dobbelt lenket liste. 
             
* Oppgave 2: Løst ved å bruke Stringbuilder og traverser fra hode til hale ved hjelp av neste-pekere. Metoden 
             omvendtString er kodet på samme måte, men traverserer fra hale til hode ved hjelp av forrige-pekere.
             Laget en leggInn-metode som oppretter ny node med innsendt verdi og legges bakerst i listen. Returnerer
             true om vellykket. Skiller mellom om listen er tom eller ikke.
             
* Oppgave 3: Løste finnNode(int indeks) ved å bruke to for-løkker for å lete gjennom hver sin halvpart av listen.
             Løste hent() ved å skrive akkurat som beskrevet i oppgaveteksten.
             Løste oppdater() ved hjelp av finnNode().
             
             Løste subliste() ved å bruke en for-løkke og leggInn().
* Oppgave 4: Løste indeksTil() ved å bruke en hjelpevariabel som blir en teller. Telleren går gjennom
             antall noder, og så lenge den ikke er null, sjekker den med .equals() om noden er lik verdien som sendes inn.
             Er den det, returneres indeksen, ellers -1. 
             
* Oppgave 4: Løste inneholder() ved hjelp av indeksTil(). Er svaret fra indeksTil() ikke lik -1, betyr det at verdien finnes i listen
             og metoden returnerer true. Hvis ikke, returnerer den false. 
             
* Oppgave 5: Laget en leggInn-metode som sender inn en verdi og en indeks verdien skal legges på i listen. Det opprettes 
             da en ny node med denne verdien. Koden er bygget på koden fra oppgave 2b, og tar høyde for fire ulike 
             tilfeller: at listen er tom, at noden skal legges først i listen, sist i listen og mellom to verdier.
             
* Oppgave 6: Løste ved å finne de forskjellige sceanrioene som kunne skje ved sletting av en node.
             Her kan listen være tom, bestå av én node (hode = hale og må nullstilles) siste eller første skal slettes,
             eller en node i midten av listen skal slettes. Samme kode er brukt på begge metodene, abre at det i fjern(T)
             er lagt inn en ekstra for-løkke for å finne noden som skal slettes.
             Koden feiler noen ganger på effektivitet, men den går uten feil de fleste ganger.
             
* Oppgave 7: Løste nullstill() ved å bruke en hjelpevariabel som teller. Brukte en while-løkke som kjører så lenge teller ikke lik hale. 
             For hver iterasjon settes telleren sin verdi og forrige-peker til null. Før den setter neste-pekeren til null, 
             lagrer jeg teller.neste i en hjelpevariabel "temp". Deretter kan man sette neste-pekeren til null og setter telleren til temp. 
             Antall minker med en for hver iterasjon, endringer øker med en. Det er denne metoden jeg har valgt å benytte, da koden
             kodes direkte, uten bruk av kall på andre metoder. 
             
* Oppgave 7: Skrev om nullstill() ---- (Koden vi ikke brukte):   
                @Override
                    public void nullstill(){
                        for(int i = 0; i < antall; i++){
                            fjern(i);
                            antall--;
                            endringer++;
                        }
                        hode = hale = null;
                        antall--;
                        endringer++;
                
             Valgte å bruke den andre metoden, da denne metoden for hver iterasjon gjør et nytt metodekall, istedenfor 
             å direkte hente ut verdier og endre dem der de er. 
                
* Oppgave 8: Løst ved å implementere T next() som sjekker om iteratorendringer er lik endringer. Kaster exception ellers.
             Videre implementeres Iterator<T> iterator(). Returnerer instans av DobbeltLenketListeIterator(). 
             Lager Iterator<T> iterator(int indeks). Den returenrer en instans av iteratorklassen ved hjelp av 
             konstruktøren DobbeltLenketListeIterator(int indeks),
             etter at indeks er blitt sjekket om er lovlig. 

* Oppgave 9: Løste ved å implementere mye lik kode som fra oppgave 6.

* Oppgave 10: Løst ved å ha en dobbel for-løkke. Dette for at teller 1 og teller 2 skal sjekkes opp mot hverandre. 
              Metoden henter ut elementer fra listen ved hjelp av metoden .hent(), hvor det sendes inn indeks i og j. 
              Det brukes c.compare(), hvor elementet til venstre sjekkes om er større enn elementet til høyre i listen. 
              Er dette sant, bytter disse to plass ved hjelp av metoden .oppdater(). 
              
