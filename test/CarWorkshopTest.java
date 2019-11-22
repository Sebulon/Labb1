import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarWorkshopTest {
    CarWorkshop<Volvo240> volvo240CarWorkshop;
    CarWorkshop<Car> carWorkshop;

    @Before
    public void setUp() throws Exception {
        volvo240CarWorkshop = new CarWorkshop<>(0, 0, new Size[]{Size.MEDIUM}, 5);
        carWorkshop = new CarWorkshop<>(0, 0, new Size[]{Size.MEDIUM, Size.SMALL, Size.BIG}, 10);
    }

    @Test
    public void loadCar() {
        Volvo240 volvo240 = new Volvo240();
        volvo240CarWorkshop.loadCar(volvo240);
    }

    @Test
    public void unloadCar() {
        Saab95 saab95 = new Saab95();
        carWorkshop.loadCar(saab95);
        Car freshSaab = carWorkshop.unloadCar();
        assertSame(saab95, freshSaab);
    }
}