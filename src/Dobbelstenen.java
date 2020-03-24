import java.util.ArrayList;

public class Dobbelstenen {

    ArrayList<Dobbelsteen> dobbelstenen = new ArrayList<>();
    private String worp, vorigeWorp;

    /**
     * maak set dobbelstenen
     */
    Dobbelstenen(int aantal) {
        for (int i = 1; i <= aantal; i++) {
            dobbelstenen.add(new Dobbelsteen());
        }
    }

    /**
     * werpt alle dobbelstenen in set
     */
    String werp() {
        String worpen = "";
        vorigeWorp = worp;
        for (Dobbelsteen d : dobbelstenen) {
            worpen += d.werp();
        }
        worp = worpen.trim();
        return worp;
    }

    /**
     * @param vasthouden posities die moeten worden vastgehouden; 0 = niets vasthouden
     * @return resultaat waarbij oude + nieuwe worp gecombineerd zijn
     */
    String werp(int vasthouden) {

        String fixeer = String.valueOf(vasthouden);
        String worpen = "";

        vorigeWorp = worp;
        for (int d = 0; d < dobbelstenen.size(); d++) {
            if (fixeer.contains(String.valueOf(d+1))) {
                worpen += String.valueOf(vorigeWorp.charAt(d)); // oude worp
            } else {
                worpen += dobbelstenen.get(d).werp();           // niewe worp
            }
        }
        worp = worpen;
        //worp = "66666";
        return worpen;
    }

    public String getWorp(){
        return worp;
    }

}

