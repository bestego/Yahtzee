public class Scoreblad2 extends Score2 { // todo: remove this class

    int resetWaarde = -1;

    Scoreblad2() {
        reset(-1);
    }

    @Override
    void reset() {
        reset(-1);
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

}
