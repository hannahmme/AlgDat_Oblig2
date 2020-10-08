import java.util.Arrays;
import java.util.Comparator;

public class Main {

    //Oppgave 0 - no runtime error
    public static void main(String[] args) {
        //Til oppgave 1
        Integer[] helttallsliste = {1,2,3,2,22,50};
        Integer[] helttallsliste2 = {};
        Integer[] helttallsliste3 = {9};
        Integer[] helttallsliste4 = {null};

        //Oppgave 1 og 4
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>(helttallsliste);
        System.out.println("for-løkken: " + liste.indeksTil(2));
        //DobbeltLenketListe<Integer> liste2 = new DobbeltLenketListe<>(helttallsliste2);
        //DobbeltLenketListe<Integer> liste3 = new DobbeltLenketListe<>(helttallsliste3);
        //DobbeltLenketListe<Integer> liste4 = new DobbeltLenketListe<>(helttallsliste4);
        //System.out.println(liste.inneholder(3));
        //System.out.println("While-metoden :" + liste.indeksTilWhile(2));

        //Oppgave 2a
        /*String[] s1 = {};
        String[] s2 = {"A"};
        String[] s3 = {null, "A", null, "B", null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.print(l1.toString() + " " + l2.toString() + " " + l3.toString() + " " + l1.omvendtString() + " " +
                l2.omvendtString() + " " + l3.omvendtString());*/

        //Oppgave 2b
        /*DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        System.out.println(liste.toString() + " " + liste.omvendtString());
        for(int i = 1; i <= 3; i++){
            liste.leggInn(i);
            System.out.println(liste.toString() + " " + liste.omvendtString());
        }*/

        //Oppgave 5
        /*String[] string1 = {"A", "B", "C", "D", "E"};
        DobbeltLenketListe<String> liste1 = new DobbeltLenketListe<>(string1);
        System.out.println(liste1.toString() + " " + liste1.omvendtString());
        liste1.leggInn(1, "Å");
        System.out.println(liste1.toString() + " " + liste1.omvendtString());*/

        //Oppgave 6
        System.out.println("Oppgave 6 - fjern verdi: ");
        String[] string1 = {"A", "B", "C", "D", "E"};
        DobbeltLenketListe<String> liste1 = new DobbeltLenketListe<>(string1);
        System.out.println(liste1.toString() + " " + liste1.omvendtString());
        System.out.println(liste1.fjern("C"));
        System.out.println(liste1.toString() + " " + liste1.omvendtString());
        System.out.println();
        System.out.println("Oppgave 6 - fjern indeks: ");
        String[] string2 = {"A", "B", "C", "D", "E"};
        DobbeltLenketListe<String> liste2 = new DobbeltLenketListe<>(string1);
        System.out.println(liste2.toString() + " " + liste2.omvendtString());
        System.out.println(liste2.fjern(1));
        System.out.println(liste2.toString() + " " + liste2.omvendtString());
        System.out.println();

        //Oppgave 7
        liste.nullstill();
        System.out.println(liste.antall());

        System.out.println(liste.tom());

        //Oppgave 8
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> mip = new DobbeltLenketListe<>(navn);
        mip.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : mip) System.out.print(s + " ");

        /*String[] navn = {"Lars", "Anders", "Bodil", "Kari", "Per", "Berit"};
        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
        liste1.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : liste1){
            System.out.print(s + " ");
        }*/

        //Oppgave 10
        Liste<Integer> genericList = new DobbeltLenketListe<>(helttallsliste);
        DobbeltLenketListe.sorter(genericList, Comparator.naturalOrder());
        //System.out.println(Arrays.toString(helttallsliste));

    }
}
