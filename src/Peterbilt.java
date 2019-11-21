import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Peterbilt extends Car implements Flatbed{

    private boolean rampIsDown = false;
    private final int maxLoadDistance = 10;
    private StackLoadCar load;

    public Peterbilt() {
        super(2, 700, Color.blue, "Peterbilt", Size.BIG);

        load = new StackLoadCar(new Size[]{Size.MEDIUM, Size.SMALL, Size.TINY});
    }

    /**
     * If car in range and the ramp is down and of accepted size, pushes car onto stack
     * @param car to be checked if can be put on stack
     */
    public void addCar(Car car){
        if(Math.abs(getxPos() - car.getxPos()) < maxLoadDistance && Math.abs(getyPos() - car.getyPos()) < maxLoadDistance)
            load.loadVehicle(getxPos(), getyPos(), car, rampIsDown);
    }

    /**
     * If ramp is down and method is called, remove car from stack (from flatbed)
     * Move car backwards so it is not in the same position as the truck
     */
    public void unloadCar(){
        Car car = load.unloadLastVehicle(rampIsDown);
        if(car != null){
            car.unloadSelf();
            car.currentSpeed = -10;
            car.move();
            car.currentSpeed = 0;
        }
    }

    /**
     * Uses Car.move for own movement as well as it moves all the cars in load
     */
    @Override
    public void move() {
        super.move();
        for(Car c : load.cars){
            c.setxPos(this.getxPos());
            c.setyPos(this.getyPos());
            c.setDirection(getDirection());
        }
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
        return load.cars;
    }

    public boolean getRampIsDown(){
        return rampIsDown;
    }
}
