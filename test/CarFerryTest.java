import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarFerryTest {

    CarFerry carFerry;

    @Before
    public void setUp() throws Exception {
        carFerry = new CarFerry();
    }

    @Test
    public void loadCar() {
       carFerry.loadCar(new Volvo240());
    }

    @Test
    public void unloadCar() {
        Car car = new Saab95();
        carFerry.loadCar(car);
        assertSame(carFerry.unloadCar(), car);
    }

    @Test
    public void move() {Peterbilt peterbilt = new Peterbilt();
        peterbilt.lowerBed();
        Car saab = new Saab95();
        peterbilt.addCar(saab);
        carFerry.loadCar(peterbilt);
        carFerry.gas(1);
        carFerry.move();
        assertEquals(saab.getxPos(), carFerry.getxPos(), 0.01);
    }
}