public class Scoreblad extends Score {

    int subtotaalBoven;
    int bonusBoven;
    int totaalBoven;

    int totaalOnder;
    int totaalGeneraal;

    int resetWaarde = -1;

    Scoreblad() {
        reset(-1);
    }

    @Override
    void reset() {
        reset(-1);
    }

    // bereken alle (sub) totaliseringen
    void totaliseer() {

        subtotaalBoven = enen + tweeen + drieen + vieren + vijfen + zessen;
        bonusBoven = subtotaalBoven >= 63 ? 35 : 0;
        totaalBoven = subtotaalBoven + bonusBoven;

        totaalOnder = threeOfAKind + carre + fullHouse + kleineStraat + groteStraat + yahtzee + chance;
        totaalGeneraal = totaalBoven + totaalOnder;

    }


}
