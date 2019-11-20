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

    public void unloadCar(){
        if(rampIsDown){
            Car car = load.pop();
            car.
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
