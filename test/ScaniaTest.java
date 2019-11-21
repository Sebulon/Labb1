import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScaniaTest {

    Scania scania;

    @Before
    public void setUp() throws Exception{
        scania = new Scania();
    }

    @Test
    public void speedFactor() {
        scania.raiseBed();
        scania.gas(.5);
        assertEquals(0, scania.getxPos(), 0.0);
    }

    @Test
    public void lowerBed() {
        scania.raiseBed();
        scania.raiseBed();
        scania.lowerBed();
        scania.lowerBed();
        scania.lowerBed();
        assertEquals(0, scania.getDegreesOnFlatbed(), 0.0);
    }

    @Test
    public void raiseBed() {
        for (int i = 0; i < 10; i++) {
            scania.raiseBed();
        }
        assertEquals(70, scania.getDegreesOnFlatbed(), 0.0);
    }
}