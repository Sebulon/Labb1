import java.awt.*;
import java.util.Deque;

public class Peterbilt extends Car implements Flatbed{

    private boolean rampIsDown = false;
    private Transporter<Car> load;

    public Peterbilt() {
        super(2, 700, Color.blue, "Peterbilt", Size.BIG);

        load = new Transporter<>(new Size[]{Size.MEDIUM, Size.SMALL, Size.TINY});
    }

    /**
     * If car in range and the ramp is down and of accepted size, pushes car onto stack
     * @param car to be checked if can be put on stack
     */
    public void addCar(Car car){
            load.loadTransportable(getxPos(), getyPos(), car, rampIsDown);
    }

    /**
     * If ramp is down and method is called, remove car from stack (from flatbed)
     * Move car backwards so it is not in the same position as the truck
     */
    public void unloadCar(){
        Car car = load.unloadLastTransportable(rampIsDown, xPos - 10, yPos);
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

    public Deque<Car> getLoad() {
        return load.loadStack;
    }

    public boolean getRampIsDown(){
        return rampIsDown;
    }
}
