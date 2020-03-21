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

    String getNaam() {
        return naam;
    }

    void noteerScore(Score score, Scoreblad scoreblad) { //todo: only allow assignments to 0 fields
        Scanner input = new Scanner(System.in);
        System.out.printf("\nWelke score wil je noteren? Kies uit 1 t/m 13: ");
        String line;
        int keuze = 0;
        do {
            try {
                keuze = Integer.valueOf(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.printf("\tOngeldige invoer, probeer opnieuw\n");
                continue;
            }
            if (keuze >= 1 && keuze <= 13) {
                break;
            } else {
                System.out.printf("\tOngeldige invoer, probeer opnieuw\n");
                continue;
            }
        } while (true);

        switch (keuze) {
            case 1:
                scoreblad.enen = score.enen;
                break;
            case 2:
                scoreblad.tweeen = score.tweeen;
                break;
            case 3:
                scoreblad.drieen = score.drieen;
                break;
            case 4:
                scoreblad.vieren = score.vieren;
                break;
            case 5:
                scoreblad.vijfen = score.vijfen;
                break;
            case 6:
                scoreblad.zessen = score.zessen;
                break;
            case 7:
                scoreblad.threeOfAKind = score.threeOfAKind;
                break;
            case 8:
                scoreblad.carre = score.carre;
                break;
            case 9:
                scoreblad.fullHouse = score.fullHouse;
                break;
            case 10:
                scoreblad.kleineStraat = score.kleineStraat;
                break;
            case 11:
                scoreblad.groteStraat = score.groteStraat;
                break;
            case 12:
                scoreblad.yahtzee = score.yahtzee;
                break;
            case 13:
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
        score.toonScoreWorp(dobbelstenen.getWorp());

        // toon scoreblad
        System.out.printf("\nScoreblad van %s:\n",naam);
        scoreblad.toonScore();

        // kies welke score overgezet moet worden naar scoreblad
        noteerScore(score, scoreblad);
        scoreblad.toonScore();

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

