import java.awt.*;

public class CarFerry extends MotorVehicle{

    StackLoadCar<Car> carLoad;
    boolean docked;

    public CarFerry() {
        super(21000, Color.WHITE, "Stena", Size.GIANT);
        stopEngine();
        carLoad = new StackLoadCar<>(new Size[]{Size.BIG, Size.MEDIUM, Size.SMALL, Size.TINY});
        docked = true;
    }

    /**
     * Loads a car if close enough and is docked
     * @param car
     */
    public void loadCar(Car car){
        carLoad.loadVehicle(getxPos(), getyPos(), car, docked);
    }

    /**
     * Unloads first car that got in the load when is docked and moves the car ahead of the boat
     * @return the unloaded car
     */
    public Car unloadCar(){
        return carLoad.unloadFirstVehicle(docked,
                10 * Math.cos(Math.toRadians(getDirection())),
                10 * Math.sin(Math.toRadians(getDirection())));
    }

    /**
     * Docks the ship, can only dock if the speed is == 0
     */
    public void dock(){
        if(currentSpeed == 0){
            docked = true;
        }
    }

    public void unDock(){
        docked = false;
    }

    /**
     * Overrides and uses the motorvehicle move method while also moves all cars on board
     */
    @Override
    public void move() {
        super.move();
        carLoad.moveCars(getCurrentSpeed(), getDirection());
    }

    @Override
    protected double speedFactor() {
        return docked ? 0 : enginePower * 0.0001;
    }
}
