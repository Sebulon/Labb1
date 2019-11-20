import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Saab95Test {

    Saab95 saab95;

    @Before
    public void setUp() throws Exception {
        saab95 = new Saab95();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetTurboOn() {
        saab95.setTurboOn();
        assertTrue(saab95.enginePower * 0.01 < saab95.speedFactor());
    }

    @Test
    public void testSetTurboOff() {
        saab95.setTurboOn();
        saab95.setTurboOff();
        assertEquals(saab95.enginePower * 0.01, saab95.speedFactor(), 0.01);
    }

}