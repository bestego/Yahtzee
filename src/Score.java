import java.util.Arrays;

public class Score {

    int enen;
    int tweeen;
    int drieen;
    int vieren;
    int vijfen;
    int zessen;

    int threeOfAKind;
    int carre;
    int fullHouse;
    int kleineStraat;
    int groteStraat;
    int yahtzee;
    int chance;

    void reset() {
        reset(0);
    }

    void reset(int startWaarde) {
        enen = startWaarde;
        tweeen = startWaarde;
        drieen = startWaarde;
        vieren = startWaarde;
        vijfen = startWaarde;
        zessen = startWaarde;

        threeOfAKind = startWaarde;
        carre = startWaarde;
        fullHouse = startWaarde;
        kleineStraat = startWaarde;
        groteStraat = startWaarde;
        yahtzee = startWaarde;
        chance = startWaarde;
    }

    void toonScoreWorp(String worp) {
        berekenScoreWorp(worp);
        toonScore();
    }

    void toonScore() {

//        System.out.println("(1) enen: " + enen);
//        System.out.println("(2) tweeen: " + tweeen);
//        System.out.println("(3) drieen: " + drieen);
//        System.out.println("(4) vieren: " + vieren);
//        System.out.println("(5) vijfen: " + vijfen);
//        System.out.println("(6) zessen: " + zessen);
//        System.out.println("---------------------------");
//        System.out.println("(7) three of a kind: " + threeOfAKind);
//        System.out.println("(8) carré: " + carre);
//        System.out.println("(9) full house: " + fullHouse);
//        System.out.println("(10) kleine straat: " + kleineStraat);
//        System.out.println("(11) grote straat: " + groteStraat);
//        System.out.println("(12) yahtzee: " + yahtzee);
//        System.out.println("(13) chance: " + chance);

        System.out.printf("%20s %2s |%20s %2s |%20s %2s |%20s %2s |%20s %2s |%20s %2s |\n",
                "(1) enen:", enen, "(2) tweeen:", tweeen, "(3) drieen:", drieen, "(4) vieren:", vieren, "(5) vijfen:", vijfen, "(6) zessen:", zessen);
        System.out.printf("%20s %2s |%20s %2s |%20s %2s |%20s %2s |%20s %2s |%20s %2s |%20s %2s\n",
                "(7) three of a kind:", threeOfAKind, "(8) carré:", carre, "(9) full house:", fullHouse, "(10) kleine straat:", kleineStraat,
                "(11) grote straat:", groteStraat, "(12) yahtzee:", yahtzee, "(13) chance:", chance);
    }

     void berekenScoreWorp(String worp) {

        int[] value2freq = new int[7]; // uses indexes 0..6 ; poor man's map ;-)
        // bereken boven
        for (char c : worp.toCharArray()) {
            switch (c) {
                case '1':
                    enen += 1;
                    value2freq[1]++;
                    break;
                case '2':
                    tweeen += 2;
                    value2freq[2]++;
                    break;
                case '3':
                    drieen += 3;
                    value2freq[3]++;
                    break;
                case '4':
                    vieren += 4;
                    value2freq[4]++;
                    break;
                case '5':
                    vijfen += 5;
                    value2freq[5]++;
                    break;
                case '6':
                    zessen += 6;
                    value2freq[6]++;
                    break;
            }
        }

        int worpTotaal = enen + tweeen + drieen + vieren + vijfen + zessen;

        // three of a kind
        if (dezelfde(value2freq, 3) > 0) threeOfAKind = worpTotaal;
        // carre
        if (dezelfde(value2freq, 4) > 0) carre = worpTotaal;
        // full house
        if (dezelfde(value2freq, 2) > 0 && dezelfde(value2freq, 3) > 0) fullHouse = 25;
        // kleine straat
        if (opeenvolgend(worp, 4)) kleineStraat = 30;
        // grote straat
        if (opeenvolgend(worp, 5)) kleineStraat = 50;
        // yahtzee
        if (dezelfde(value2freq, 5) > 0) yahtzee = 50; //todo: yatzee bonus toevoegen
        // chance
        chance = worpTotaal;
    }

    /**
     * @param aantal dat hetzelfde moet zijn
     * @return hoe vaak gevraagde voorkomt
     */
    private int dezelfde(int[] value2freq, int aantal) {
        int count = 0;
        for (int i = 1; i < value2freq.length; i++) {
            if (value2freq[i] == aantal) count++;
        }
        return count;
    }

    /**
     * @param worp
     * @return grootste aantal opeenvolgende stenen
     */
    private boolean opeenvolgend(String worp, int aantal) {
        int count = 0;
        boolean match = false;
        char[] dobbelsteen = worp.toCharArray();
        Arrays.sort(dobbelsteen);

        int pos = 0;
        while (pos < worp.length() - 1) {
            if (dobbelsteen[pos + 1] - dobbelsteen[pos] == 1) {
                count = count == 0 ? 2 : ++count;
                if (count == aantal) match = true;
            } else {
                count = 0;
            }
            pos++;
        }
        return match;
    }

} // Score
