import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTest {

    Car saab;
    Car volvo;

    @Before
    public void setUp() throws Exception {
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void incrementSpeedSaab() {
        saab.incrementSpeed(Math.random());
        assertTrue(saab.getCurrentSpeed() > 0);
    }

    @Test
    public void incrementSpeedVolvo(){
        volvo.incrementSpeed(Math.random());
        assertTrue(volvo.getCurrentSpeed() > 0);
    }

    @Test
    public void decrementSpeedSaab() {
        saab.decrementSpeed(Math.random());
        assertTrue(saab.getCurrentSpeed() >= 0);
    }

    @Test
    public void decrementSpeedVolvo() {
        volvo.decrementSpeed(Math.random());
        assertTrue(volvo.getCurrentSpeed() >= 0);
    }

    @Test
    public void turnLeft() {
        for (int i = 0; i < 9; i++) {
            saab.turnLeft();
        }
        assertTrue(saab.getDirection() > 0);
    }

    @Test
    public void turnRight() {
        volvo.turnRight();
        assertTrue(volvo.getDirection() > 0 && volvo.getDirection() < 360);
    }

    @Test
    public void move() {
        volvo.startEngine();
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.getxPos() > 0 && volvo.getyPos() < 0);
    }

    @Test
    public void getCurrentSpeed() {
        saab.startEngine();
        assertTrue(saab.getCurrentSpeed() > 0);
    }

    @Test
    public void setColor() {
        saab.setColor(Color.CYAN);
        assertSame(saab.getColor(), Color.CYAN);
    }

    @Test
    public void speedFactor() {
        assertTrue(saab.speedFactor() > 0);
    }


    @Test
    public void testGasVolvo() {
        volvo.startEngine();
        volvo.gas(0.5);
        assertTrue(volvo.currentSpeed > 0);
    }



    @Test
    public void testBrakeVolvo() {
        volvo.startEngine();
        volvo.gas(0.5);
        volvo.brake(0.5);
        assertEquals(0, volvo.currentSpeed, 0.11);
    }


    @Test
    public void testGasSaab() {
        saab.startEngine();
        saab.gas(12);
        assertTrue(saab.currentSpeed > 0);
    }

    @Test
    public void testBrakeSaab() {
        saab.startEngine();
        saab.gas(0.5);
        saab.brake(-2);
        assertTrue(saab.currentSpeed >= 0);
    }
}