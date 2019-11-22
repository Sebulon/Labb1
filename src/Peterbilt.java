import java.awt.*;
import java.util.Deque;

public class Peterbilt extends Car implements Flatbed{

    private boolean rampIsDown = false;
    private Transporter<Car> load;

    /**
     * Truck that can carry cars
     */
    public Peterbilt() {
        super(2, 700, Color.blue, "Peterbilt", Size.BIG);

        load = new Transporter<>(new Size[]{Size.MEDIUM, Size.SMALL, Size.TINY});
    }

    /**
     * If car in range and the ramp is down and of accepted size, pushes car onto stack
     * @param car to be checked if can be put on stack
     */
    public void addCar(Car car){
        if(rampIsDown)
            load.loadTransportable(getxPos(), getyPos(), car);
    }

    /**
     * If ramp is down and method is called, remove car from stack (from flatbed)
     * Move car backwards so it is not in the same position as the truck
     */
    public Car unloadCar(){
        if(rampIsDown)
            return load.unloadLastTransportable(xPos - 10, yPos);
        return null;
    }

    /**
     * Uses Car.move for own movement as well as it moves all the cars in load
     */
    @Override
    public void move() {
        super.move();
        load.followTransport(getCurrentSpeed(), getDirection());
    }

    @Override
    protected double speedFactor() {
        return rampIsDown ? 0 : enginePower * 0.01;
    }

    @Override
    public void lowerBed() {
        if(currentSpeed == 0){
            rampIsDown = true;
        }
    }

    @Override
    public void raiseBed() {
        rampIsDown = false;
    }

    Deque<Car> getLoad() {
        return load.loadStack;
    }

    boolean getRampIsDown(){
        return rampIsDown;
    }
}
