import java.util.ArrayList;
import java.util.Scanner;

public class Speler {


    Score score = new Score();
    Scoreblad scoreblad = new Scoreblad();
    private String naam;

    Speler(String naam) {
        this.naam = naam;
    }

    String doeWorp(Dobbelstenen dobbelstenen) {
        return doeWorp(dobbelstenen, 0);
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

    boolean isGeldigeNotatie(Scoreblad scoreblad, String letter) {

        // maak lijst van nog niet-gescoorde items
        ArrayList<Character> validList = new ArrayList<>();
        if (scoreblad.enen == -1) validList.add('1');
        if (scoreblad.tweeen == -1) validList.add('2');
        if (scoreblad.drieen == -1) validList.add('3');
        if (scoreblad.vieren == -1) validList.add('4');
        if (scoreblad.vijfen == -1) validList.add('5');
        if (scoreblad.zessen == -1) validList.add('6');
        if (scoreblad.threeOfAKind == -1) validList.add('t');
        if (scoreblad.carre == -1) validList.add('c');
        if (scoreblad.fullHouse == -1) validList.add('f');
        if (scoreblad.kleineStraat == -1) validList.add('k');
        if (scoreblad.groteStraat == -1) validList.add('g');
        if (scoreblad.yahtzee == -1) validList.add('y');
        if (scoreblad.chance == -1) validList.add('z');

        return validList.indexOf(letter.toLowerCase().charAt(0)) >= 0 ? true : false;

    }

    String getNaam() {
        return naam;
    }

    void noteerScore(Score score, Scoreblad scoreblad) {

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

        switch (keuze) {
            case '1':
                scoreblad.enen = score.enen;
                break;
            case '2':
                scoreblad.tweeen = score.tweeen;
                break;
            case '3':
                scoreblad.drieen = score.drieen;
                break;
            case '4':
                scoreblad.vieren = score.vieren;
                break;
            case '5':
                scoreblad.vijfen = score.vijfen;
                break;
            case '6':
                scoreblad.zessen = score.zessen;
                break;
            case 't':
                scoreblad.threeOfAKind = score.threeOfAKind;
                break;
            case 'c':
                scoreblad.carre = score.carre;
                break;
            case 'f':
                scoreblad.fullHouse = score.fullHouse;
                break;
            case 'k':
                scoreblad.kleineStraat = score.kleineStraat;
                break;
            case 'g':
                scoreblad.groteStraat = score.groteStraat;
                break;
            case 'y':
                scoreblad.yahtzee = score.yahtzee;
                break;
            case 'z':
                scoreblad.chance = score.chance;
                break;
            default:
                System.out.println("Programma fout: onverwachte keuze");
                return;
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
        System.out.printf("\nScore laatse worp:\n");
        score.berekenScoreWorp(dobbelstenen.getWorp());

        toonScoreblad(scoreblad, score);
        noteerScore(score, scoreblad);
    }

    void toonScoreblad(Scoreblad scoreblad, Score score) {
        System.out.printf("\n Scoreblad van speler: %s\n", naam);
        System.out.printf("%20s | %20s | %20s | %20s\n", " ", "------ score -------", "--- laatste worp ---", "--- kies welke worp je wilt noteren ---");
        System.out.printf("");
        System.out.printf("%20s | %20s | %20s | %20s\n", "enen", scoreblad.enen == -1 ? "" : scoreblad.enen, score.enen, scoreblad.enen == -1 ? "(1)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "tweeen", scoreblad.tweeen == -1 ? "" : scoreblad.tweeen, score.tweeen, scoreblad.tweeen == -1 ? "(2)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "drieen", scoreblad.drieen == -1 ? "" : scoreblad.drieen, score.drieen, scoreblad.drieen == -1 ? "(3)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "vieren", scoreblad.vieren == -1 ? "" : scoreblad.vieren, score.vieren, scoreblad.vieren == -1 ? "(4)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "vijfen", scoreblad.vijfen == -1 ? "" : scoreblad.vijfen, score.vijfen, scoreblad.vijfen == -1 ? "(5)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "zessen", scoreblad.zessen == -1 ? "" : scoreblad.zessen, score.zessen, scoreblad.zessen == -1 ? "(6)" : "");

        System.out.printf("%20s | %20s | %20s | %20s\n", "three of a kind", scoreblad.threeOfAKind == -1 ? "" : scoreblad.threeOfAKind, score.threeOfAKind, scoreblad.threeOfAKind == -1 ? "(T)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "carré", scoreblad.carre == -1 ? "" : scoreblad.carre, score.carre, scoreblad.carre == -1 ? "(C)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "full house", scoreblad.fullHouse == -1 ? "" : scoreblad.fullHouse, score.fullHouse, scoreblad.fullHouse == -1 ? "(F)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "kleine straat", scoreblad.kleineStraat == -1 ? "" : scoreblad.kleineStraat, score.kleineStraat, scoreblad.kleineStraat == -1 ? "(K)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "grote straat", scoreblad.groteStraat == -1 ? "" : scoreblad.groteStraat, score.groteStraat, scoreblad.groteStraat == -1 ? "(G)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "yahtzee", scoreblad.yahtzee == -1 ? "" : scoreblad.yahtzee, score.yahtzee, scoreblad.yahtzee == -1 ? "(Y)" : "");
        System.out.printf("%20s | %20s | %20s | %20s\n", "chance", scoreblad.chance == -1 ? "" : scoreblad.chance, score.chance, scoreblad.chance == -1 ? "(Z)" : "");

    }

    int welkeVasthouden() {
        Scanner input = new Scanner(System.in);

        System.out.printf("\tWelke dobbelstenen wil je vasthouden? ( 0 = geen)\n");
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

