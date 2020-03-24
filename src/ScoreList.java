import java.util.ArrayList;

public class ScoreList extends ArrayList<SleutelWaarde> {

    /**
     * Geeft waarde behorende bij sleutel
     *
     * @param sleutel
     * @return waarde behorende bij sleutel OF null indien sleutel niet aanwezig
     */
    Integer get(String sleutel) {
        for (SleutelWaarde sw : this) {
            if (sw.sleutel.equals(sleutel)) {
                return new Integer(sw.waarde);
            }
        }
        return null;
    }

    boolean isGenoteerd(String sleutel) {
        for (SleutelWaarde sw : this) {
            if (sw.sleutel.equals(sleutel)) {
                return sw.genoteerd;
            }
        }
        return false;
    }

    boolean put(String sleutel, int waarde) {
        for (SleutelWaarde sw : this) {
            if (sw.sleutel.equals(sleutel)) {
                sw.waarde = waarde;
                sw.genoteerd = true;
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String line = "";
        for (SleutelWaarde sw : this) {
            line = line.length() == 0 ? "{" + sw.sleutel + "=>" + sw.waarde + "}" : " , {" + sw.sleutel + "=>" + sw.waarde + "} ";
        }
        return line;
    }
}
