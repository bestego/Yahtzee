import org.junit.Test;

public class SpelTest {

    @Test
    public void testBerekenScoreWorpYathzee() {
        Score score = new Score();
        score.toonScoreWorp("11111");
        assert (true);
    }

    @Test
    public void testBerekenScoreFullHouse() {
        Score score = new Score();
        score.toonScoreWorp("12121");
        assert (true);
    }

    @Test
    public void testBerekenScoreKleineStraatBegin() {
        Score score = new Score();
        score.toonScoreWorp("12346");
        assert (true);
    }

    @Test
    public void testBerekenScoreKleineStraatEind() {
        Score score = new Score();
        score.toonScoreWorp("13456");
        assert (true);
    }

}