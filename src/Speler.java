import java.util.ArrayList;
import java.util.Scanner;

public class Speler {

    Score score = new Score();
    Score scoreblad = new Score();
    private String naam;

    Speler(String naam) {
        this.naam = naam;
    }

    String doeWorp(Dobbelstenen dobbelstenen, int vasthouden) {
        return dobbelstenen.werp(vasthouden);
    }

    public boolean isJa(String msg) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\t%s, %s", naam, msg);
        String line;
        char actie;
        do {
            line = input.nextLine().toUpperCase();
            actie = line.length() == 0 ? '0' : line.charAt(0);
            if (actie == 'J' | actie == 'N') {
                break;
            } else {
                System.out.printf("\tOngeldige invoer, probeer opnieuw:");
            }
        } while (true);
        return actie == 'J';
    }

    boolean isGeldigeNotatie(Score scoreblad, String letter) {

        // maak lijst van nog niet-gescoorde items
        ArrayList<Character> validList = new ArrayList<>();

        for (SleutelWaarde sw : scoreblad.item) {
            if (sw.toets != 0 && !sw.genoteerd) validList.add(sw.toets);
        }

        return validList.indexOf(letter.toLowerCase().charAt(0)) >= 0 ? true : false;

    }

    String getNaam() {
        return naam;
    }

    void noteerScore2(Score score, Score scoreblad) {

        if (score.item.get(Categorie.yahtzee) != 0 ) {
            System.out.printf("\nJe hebt YAHTZEE :-)\n");
            if ( scoreblad.item.get(Categorie.yahtzee) < 50 ) {
                scoreblad.item.put(Categorie.yahtzee, 50);
            } else {
                scoreblad.item.put(Categorie.yahtzeeBonus, scoreblad.item.get(Categorie.yahtzeeBonus)+100);
            }
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.printf("\nKies welke worp je wilt noteren: ");
        String line;
        char keuze = '-';
        do {
            line = input.nextLine();
            if (line.length() > 0 && isGeldigeNotatie(scoreblad, line)) {
                keuze = line.toLowerCase().charAt(0);
            } else {
                System.out.printf("\tOngeldige invoer, probeer opnieuw\n");
            }
        } while (keuze == '-');

        // kopieer gekozen waarde naar scoreblad
        for (SleutelWaarde sw : scoreblad.item) {
            if (keuze == sw.toets) {
                scoreblad.item.put(sw.sleutel, score.item.get(sw.sleutel));
                break;
            }
        }

    }

    void speelBeurt(Dobbelstenen dobbelstenen) {

        score.reset();

        // doe maximaal 3x worp
        int vasthouden = 0;
        for (int w = 1; w <= 3; w++) {
            System.out.println("Worp " + w + ": " + doeWorp(dobbelstenen, vasthouden));
            if (w <= 2) {
                if (!isJa("wil je nog een keer gooien? (J)a of (N)ee")) {
                    break;
                }
                vasthouden = welkeVasthouden();
            }
        }

        // toon score laatste worp
        score.berekenScoreWorp(dobbelstenen.getWorp());
        toonScoreblad2(scoreblad, score);
        noteerScore2(score, scoreblad);
    }

    void toonScoreblad2(Score scoreblad, Score score) {
        System.out.printf("\n Scoreblad van speler: %s\n", naam);
        System.out.printf("%20s | %20s | %20s | %20s\n", " ", "------ score -------", "--- laatste worp ---", "--- kies welke worp je wilt noteren ---");
        for (SleutelWaarde sw : scoreblad.item) {
            if (sw.sleutel.toLowerCase().matches(".*totaal.*") || sw.sleutel.matches(Categorie.bonusBoven)) continue;
            System.out.printf("%20s | %20s | %20s | %8s\n", sw.sleutel, sw.genoteerd ? sw.waarde : "", score.item.get(sw.sleutel), sw.genoteerd ? "": (sw.toets == 0?"": "("+sw.toets+")"));
        }
    }

    int welkeVasthouden() {
        Scanner input = new Scanner(System.in);

        System.out.printf("\tWelke dobbelstenen wil je vasthouden? ( bijv. 15 betekent dobbelstenen 1 en 5; 0 = geen)\n");
        boolean isInvoerJuist;
        String line;

        do {
            isInvoerJuist = true;
            line = input.nextLine();
            //characters in bereik '0'..'5'
            for (int p = 0; p < line.length(); p++) {
                if (line.charAt(p) < '0' || line.charAt(p) > '5') {
                    isInvoerJuist = false;
                }
            }
            if (!isInvoerJuist) System.out.printf("\tOngeldige invoer, probeer opnieuw\n");
        } while (!isInvoerJuist);

        return Integer.valueOf(line);
    }

}

