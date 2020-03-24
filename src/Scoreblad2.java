public class Scoreblad2 extends Score2 { // todo: remove this class

    int resetWaarde = -1;

    Scoreblad2() {
        reset();
    }

    @Override
    void reset() {
        reset();
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

}
