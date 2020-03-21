import org.junit.Test;

import static org.junit.Assert.*;

public class SpelerTest {

    @Test
    public void testWelkeVasthouden() {
        Speler speler = new Speler("test");
        System.out.println(speler.welkeVasthouden());
        assert(true);
    }


}