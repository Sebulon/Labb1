import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Peterbilt extends Car implements Flatbed{

    private boolean rampIsDown = false;
    private Deque<Car> load;
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
        if(car.getSize() != Size.BIG && rampIsDown){
            if(Math.abs(car.getxPos() - getxPos()) < maxLoadDistance
                    && Math.abs(car.getyPos() - getyPos()) < maxLoadDistance){

                load.push(car);
            }
        }
    }

    /**
     * If ramp is down and method is called, remove car from stack (from flatbed)
     * Move car backwards so it is not in the same position as the truck
     */
    public void unloadCar(){
        if(rampIsDown){
            Car car = load.pop();
            car.currentSpeed = -10;
            car.move();
            car.currentSpeed = 0;
        }
    }


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
        return 0;
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
}
