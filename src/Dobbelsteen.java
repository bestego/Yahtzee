import java.util.Random;

public class Dobbelsteen {

    private String worp;
    private Random random = new Random();

    String werp() {
        Integer worp = random.nextInt(6) + 1;
        return worp.toString().trim();
    }

}
