import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Peterbilt extends Car implements Flatbed{

    private boolean rampIsDown = false;
    private final Deque<Car> load;
    private final int maxLoadDistance = 10;

    public Peterbilt() {
        super(2, 700, Color.blue, "Peterbilt", Size.BIG);

        load = new ArrayDeque<>();
    }

    /**
     * If car in range and the ramp is down and of accepted size, pushes car onto stack
     * @param car to be checked if can be put on stack
     */
    public void addCar(Car car){
        if(car != null && car.getSize() != Size.BIG && rampIsDown){
            if(Math.abs(car.getxPos() - getxPos()) < maxLoadDistance
                    && Math.abs(car.getyPos() - getyPos()) < maxLoadDistance){

                load.push(car);
                car.loadSelf();
                car.setyPos(getyPos());
                car.setxPos(getxPos());
                car.setDirection(getDirection());
                car.stopEngine();
            }
        }
    }

    /**
     * If ramp is down and method is called, remove car from stack (from flatbed)
     * Move car backwards so it is not in the same position as the truck
     */
    public void unloadCar(){
        if(rampIsDown && load.size() > 0){
            Car car = load.pop();
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
        for(Car c : load){
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
        return load;
    }

    public boolean getRampIsDown(){
        return rampIsDown;
    }
}
