import java.util.ArrayList;
import java.util.Scanner;

public class Spel {

    ArrayList<Speler> spelers = new ArrayList<>();
    Dobbelstenen dobbelstenen = new Dobbelstenen(5);

    public static void main(String[] args) {

        // creeer benodigde objecten
        Spel spel = new Spel();

        // definieer spelers
        spel.maakSpelers();

        // speel spel
        for (int ronde = 1; ronde <= 13; ronde++) {
            for (Speler speler : spel.spelers) {
                System.out.println("---------------------------------------------------");
                System.out.println("Speler: " + speler.getNaam() + " is nu aan de beurt");
                speler.speelBeurt(spel.dobbelstenen);
                if ( ronde == 13) speler.scoreblad.totaliseer();
            }
        }

        // bepaal winnaar
        Speler winnaar = null;
        int hoogsteScore = 0;
        for (Speler speler : spel.spelers) {
            int totaal = speler.scoreblad.totaalGeneraal;
            System.out.println("=============================================");
            System.out.printf("%s heeft %d punten\n", speler.getNaam(),speler.scoreblad.totaalGeneraal);
            if ( totaal > hoogsteScore) {
                hoogsteScore = totaal;
                winnaar = speler;
            }
        }
        System.out.printf("\n DE WINNAAR IS: %s\n",winnaar.getNaam());




    } // main

    private void maakSpelers() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Voer naam in van speler: " + (spelers.size() + 1) + " (geen naam om te stoppen)");
            String naam = input.nextLine();
            if (naam.length() == 0) {
                break;
            }
            spelers.add(new Speler(naam));
        }
        System.out.print("Spelers zijn: ");
        for (Speler s : spelers) {
            System.out.print(s.getNaam() + " ");
        }
        System.out.println();
    }

}
