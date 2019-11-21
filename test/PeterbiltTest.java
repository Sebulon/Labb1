import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PeterbiltTest {

    Peterbilt peterbilt;

    @Before
    public void setUp() throws Exception{
        peterbilt = new Peterbilt();
    }

    @Test
    public void addCar() {
        Car saab = new Saab95();
        peterbilt.lowerBed();
        peterbilt.addCar(saab);
        assertSame(peterbilt.getLoad().getFirst(), saab);
    }

    @Test
    public void unloadCar() {
        Saab95 saab95 = new Saab95();
        peterbilt.lowerBed();
        peterbilt.addCar(saab95);
        peterbilt.unloadCar();
        assertEquals(peterbilt.getxPos() - 10, saab95.getxPos(), 2);
    }

    @Test
    public void move() {
        Volvo240 volvo240 = new Volvo240();
        peterbilt.lowerBed();
        peterbilt.addCar(volvo240);
        peterbilt.gas(1);
        peterbilt.move();
        assertEquals(peterbilt.getxPos(), volvo240.getxPos(), 0.01);
    }

    @Test
    public void lowerBed() {
        peterbilt.gas(1);
        peterbilt.lowerBed();
        assertFalse(peterbilt.getRampIsDown());
    }
}