import java.util.ArrayList;
import java.util.List;

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
        //DobbeltLenketListe<Integer> liste2 = new DobbeltLenketListe<>(helttallsliste2);
        //DobbeltLenketListe<Integer> liste3 = new DobbeltLenketListe<>(helttallsliste3);
        //DobbeltLenketListe<Integer> liste4 = new DobbeltLenketListe<>(helttallsliste4);
        System.out.println("for-løkken: " + liste.indeksTil(2));
        //System.out.println(liste.inneholder(3));
        //System.out.println("While-metoden :" + liste.indeksTilWhile(2));


    }
}
