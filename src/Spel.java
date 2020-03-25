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
        int aantalBeurten = 13;
        for (int beurt = 1; beurt <= aantalBeurten; beurt++) {
            for (Speler speler : spel.spelers) {
                System.out.println("---------------------------------------------------");
                System.out.println("Speler: " + speler.getNaam() + " is nu aan de beurt");
                speler.speelBeurt(spel.dobbelstenen);
                if (beurt == aantalBeurten) speler.scoreblad.totaliseer();
            }
        }

        spel.toonUitslag(spel.spelers);

    } // main

    private void maakSpelers() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Voer naam in van speler: " + (spelers.size() + 1) + " (geen naam om verder te gaan)");
            String naam = input.nextLine();
            if (naam.length() == 0) {
                if (spelers.size() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            spelers.add(new Speler(naam));
        } while (true);
        System.out.print("Spelers zijn: ");
        for (Speler s : spelers) {
            System.out.print(s.getNaam() + " ");
        }
        System.out.println();
    }

    private void toonUitslag(ArrayList<Speler> spelersLijst) {
        String buffer = String.format("%20s |","------ speler(s) -->");
        for (Speler speler : spelersLijst) {
            buffer += String.format("%20s |", speler.getNaam());
        }
        System.out.println(buffer);
        for (SleutelWaarde sw : spelersLijst.get(0).score.item) {
            if ( sw.sleutel.equals(Categorie.subtotaalBoven)) System.out.println();
             buffer = String.format("%20s |", sw.sleutel);
            for (Speler speler : spelersLijst) {
                buffer += String.format("%20s |", speler.scoreblad.item.get(sw.sleutel));
            }
            System.out.println(buffer);
        }
    }

}
