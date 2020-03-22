import java.util.Arrays;

public class Score2 {

    final ScoreList categorie = new ScoreList();

    Score2() {
        this(0);
    }

    Score2(int startWaarde) {
        categorie.add(new SleutelWaarde(Categorie.enen, startWaarde, '1'));
        categorie.add(new SleutelWaarde(Categorie.tweeen, startWaarde, '2'));
        categorie.add(new SleutelWaarde(Categorie.drieen, startWaarde, '3'));
        categorie.add(new SleutelWaarde(Categorie.vieren, startWaarde, '4'));
        categorie.add(new SleutelWaarde(Categorie.vijfen, startWaarde, '5'));
        categorie.add(new SleutelWaarde(Categorie.zessen, startWaarde, '6'));


        categorie.add(new SleutelWaarde(Categorie.threeOfAKind, startWaarde, 't'));
        categorie.add(new SleutelWaarde(Categorie.carre, startWaarde, 'c'));
        categorie.add(new SleutelWaarde(Categorie.fullHouse, startWaarde, 'f'));
        categorie.add(new SleutelWaarde(Categorie.kleineStraat, startWaarde, 'k'));
        categorie.add(new SleutelWaarde(Categorie.groteStraat, startWaarde, 'g'));
        categorie.add(new SleutelWaarde(Categorie.yahtzee, startWaarde, 'y'));
        categorie.add(new SleutelWaarde(Categorie.chance, startWaarde, 'z'));

        categorie.add(new SleutelWaarde(Categorie.subtotaalBoven, 0));
        categorie.add(new SleutelWaarde(Categorie.bonusBoven, 0));
        categorie.add(new SleutelWaarde(Categorie.totaalBoven, 0));
        categorie.add(new SleutelWaarde(Categorie.yahtzeeBonus, 0));
        categorie.add(new SleutelWaarde(Categorie.totaalOnder, 0));
        categorie.add(new SleutelWaarde(Categorie.totaalGeneraal, 0));
    }

    void reset() {

        reset(0);
    }

    void reset(int startWaarde) {
        for (SleutelWaarde item : categorie) {
            item.waarde = startWaarde;
        }
    }

    void berekenScoreWorp(String worp) {

        int[] value2freq = new int[7]; // uses indexes 0..6 ; poor man's map ;-)
        // bereken boven
        for (char c : worp.toCharArray()) {
            switch (c) {
                case '1':
                    categorie.put(Categorie.enen, categorie.get(Categorie.enen) + 1);
                    value2freq[1]++;
                    break;
                case '2':
                    categorie.put(Categorie.tweeen, categorie.get(Categorie.tweeen) + 2);
                    value2freq[2]++;
                    break;
                case '3':
                    categorie.put(Categorie.drieen, categorie.get(Categorie.drieen) + 3);
                    value2freq[3]++;
                    break;
                case '4':
                    categorie.put(Categorie.vieren, categorie.get(Categorie.vieren) + 4);
                    value2freq[4]++;
                    break;
                case '5':
                    categorie.put(Categorie.vijfen, categorie.get(Categorie.vijfen) + 5);
                    value2freq[5]++;
                    break;
                case '6':
                    categorie.put(Categorie.zessen, categorie.get(Categorie.zessen) + 6);
                    value2freq[6]++;
                    break;
            }
        }

        int worpTotaal = 0;
        for (int d = 0; d < worp.length(); d++) {
            worpTotaal += Character.getNumericValue(worp.charAt(d));
        }

        // three of a kind
        if (dezelfde(value2freq, 3) > 0) categorie.put(Categorie.threeOfAKind, worpTotaal);
        // carre
        if (dezelfde(value2freq, 4) > 0) categorie.put(Categorie.carre, worpTotaal);
        // full house
        if (dezelfde(value2freq, 2) > 0 && dezelfde(value2freq, 3) > 0) categorie.put(Categorie.fullHouse, 25);
        // kleine straat
        if (opeenvolgend(worp, 4)) categorie.put(Categorie.kleineStraat, 30);
        // grote straat
        if (opeenvolgend(worp, 5)) categorie.put(Categorie.kleineStraat, 50);
        // yahtzee
        if (dezelfde(value2freq, 5) > 0) categorie.put(Categorie.yahtzee, 50); //todo: yatzee bonus toevoegen
        // chance
        categorie.put(Categorie.chance, worpTotaal);
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

    // bereken alle (sub) totaliseringen
    void totaliseer() {

        categorie.put(Categorie.subtotaalBoven, categorie.get(Categorie.enen) + categorie.get(Categorie.tweeen) + categorie.get(Categorie.drieen) +
                categorie.get(Categorie.vieren) + categorie.get(Categorie.vijfen) + categorie.get(Categorie.zessen));
        categorie.put(Categorie.bonusBoven, categorie.get(Categorie.subtotaalBoven) >= 63 ? 35 : 0);
        categorie.put(Categorie.totaalBoven, categorie.get(Categorie.subtotaalBoven) + categorie.get(Categorie.bonusBoven));

        categorie.put(Categorie.totaalOnder, categorie.get(Categorie.threeOfAKind) + categorie.get(Categorie.carre) + categorie.get(Categorie.fullHouse) +
                categorie.get(Categorie.kleineStraat) + categorie.get(Categorie.groteStraat) + categorie.get(Categorie.yahtzee) + categorie.get(Categorie.chance) + categorie.get(Categorie.yahtzeeBonus));
        categorie.put(Categorie.totaalGeneraal, categorie.get(Categorie.totaalBoven) + categorie.get(Categorie.totaalOnder));
    }

} // Score
