import java.util.Objects;

public class SleutelWaarde {
    public String sleutel;
    public int waarde;
    public char toets;
    public boolean genoteerd = false;
    // todo: add field waardeIsSet; add setters/getters; this avoid initialization -1; make fields private

    SleutelWaarde(String sleutel, int waarde){
        this.sleutel = sleutel;
        this.waarde = waarde;
    }
    SleutelWaarde(String sleutel, int waarde, char toets){
        this.sleutel = sleutel;
        this.waarde = waarde;
        this.toets = toets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleutelWaarde that = (SleutelWaarde) o;
        return Objects.equals(sleutel, that.sleutel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sleutel);
    }
}
