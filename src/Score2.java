import java.util.Arrays;

public class Score2 {

    final ScoreList item = new ScoreList();

    Score2() {
        item.add(new SleutelWaarde(Categorie.enen, 0, '1'));
        item.add(new SleutelWaarde(Categorie.tweeen, 0, '2'));
        item.add(new SleutelWaarde(Categorie.drieen, 0, '3'));
        item.add(new SleutelWaarde(Categorie.vieren, 0, '4'));
        item.add(new SleutelWaarde(Categorie.vijfen, 0, '5'));
        item.add(new SleutelWaarde(Categorie.zessen, 0, '6'));


        item.add(new SleutelWaarde(Categorie.threeOfAKind, 0, 't'));
        item.add(new SleutelWaarde(Categorie.carre, 0, 'c'));
        item.add(new SleutelWaarde(Categorie.fullHouse, 0, 'f'));
        item.add(new SleutelWaarde(Categorie.kleineStraat, 0, 'k'));
        item.add(new SleutelWaarde(Categorie.groteStraat, 0, 'g'));
        item.add(new SleutelWaarde(Categorie.yahtzee, 0, 'y'));
        item.add(new SleutelWaarde(Categorie.chance, 0, 'z'));

        item.add(new SleutelWaarde(Categorie.subtotaalBoven, 0));
        item.add(new SleutelWaarde(Categorie.bonusBoven, 0));
        item.add(new SleutelWaarde(Categorie.totaalBoven, 0));
        item.add(new SleutelWaarde(Categorie.yahtzeeBonus, 0));
        item.add(new SleutelWaarde(Categorie.totaalOnder, 0));
        item.add(new SleutelWaarde(Categorie.totaalGeneraal, 0));
    }

    void reset() {
        for (SleutelWaarde item : item) {
            item.waarde = 0;
            item.genoteerd = false;
        }
    }

    void berekenScoreWorp(String worp) {

        int[] value2freq = new int[7]; // uses indexes 0..6 ; poor man's map ;-)
        // bereken boven
        for (char c : worp.toCharArray()) {
            switch (c) {
                case '1':
                    item.put(Categorie.enen, item.get(Categorie.enen) + 1);
                    value2freq[1]++;
                    break;
                case '2':
                    item.put(Categorie.tweeen, item.get(Categorie.tweeen) + 2);
                    value2freq[2]++;
                    break;
                case '3':
                    item.put(Categorie.drieen, item.get(Categorie.drieen) + 3);
                    value2freq[3]++;
                    break;
                case '4':
                    item.put(Categorie.vieren, item.get(Categorie.vieren) + 4);
                    value2freq[4]++;
                    break;
                case '5':
                    item.put(Categorie.vijfen, item.get(Categorie.vijfen) + 5);
                    value2freq[5]++;
                    break;
                case '6':
                    item.put(Categorie.zessen, item.get(Categorie.zessen) + 6);
                    value2freq[6]++;
                    break;
            }
        }

        int worpTotaal = 0;
        for (int d = 0; d < worp.length(); d++) {
            worpTotaal += Character.getNumericValue(worp.charAt(d));
        }

        // three of a kind
        if (dezelfde(value2freq, 3) > 0) item.put(Categorie.threeOfAKind, worpTotaal);
        // carre
        if (dezelfde(value2freq, 4) > 0) item.put(Categorie.carre, worpTotaal);
        // full house
        if (dezelfde(value2freq, 2) > 0 && dezelfde(value2freq, 3) > 0) item.put(Categorie.fullHouse, 25);
        // kleine straat
        if (opeenvolgend(worp, 4)) item.put(Categorie.kleineStraat, 30);
        // grote straat
        if (opeenvolgend(worp, 5)) item.put(Categorie.kleineStraat, 50);
        // yahtzee
        if (dezelfde(value2freq, 5) > 0) item.put(Categorie.yahtzee, 50);
        // chance
        item.put(Categorie.chance, worpTotaal);
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

        item.put(Categorie.subtotaalBoven, item.get(Categorie.enen) + item.get(Categorie.tweeen) + item.get(Categorie.drieen) +
                item.get(Categorie.vieren) + item.get(Categorie.vijfen) + item.get(Categorie.zessen));
        item.put(Categorie.bonusBoven, item.get(Categorie.subtotaalBoven) >= 63 ? 35 : 0);
        item.put(Categorie.totaalBoven, item.get(Categorie.subtotaalBoven) + item.get(Categorie.bonusBoven));

        item.put(Categorie.totaalOnder, item.get(Categorie.threeOfAKind) + item.get(Categorie.carre) + item.get(Categorie.fullHouse) +
                item.get(Categorie.kleineStraat) + item.get(Categorie.groteStraat) + item.get(Categorie.yahtzee) + item.get(Categorie.chance) + item.get(Categorie.yahtzeeBonus));
        item.put(Categorie.totaalGeneraal, item.get(Categorie.totaalBoven) + item.get(Categorie.totaalOnder));
    }

} // Score
