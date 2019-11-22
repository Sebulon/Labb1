import java.util.ArrayDeque;
import java.util.Deque;

public class StackLoadCar<T extends Car> {
    Deque<T> cars;
    Size[] allowedSizes;
    double loadDistance = 10;

    public StackLoadCar(Size[] sizes){
        allowedSizes = sizes;

        cars = new ArrayDeque<>();
    }

    /**
     * Loads the vehicle and moves the vehicle to the correct position
     * @param xPos
     * @param yPos
     * @param car Car to be loaded
     * @param canLoad Boolean that decides if it can be loaded
     */
    void loadVehicle(double xPos, double yPos, T car, boolean canLoad){
        if(car != null && correctSize(car.getSize()) && canLoad){
            if(Math.abs(xPos - car.getxPos()) < loadDistance && Math.abs(yPos - car.getyPos()) < loadDistance) {
                cars.push(car);
                car.loadSelf();
                car.stopEngine();
            } else{
                System.out.println("Loading failed: Car is too far away!");
            }
        } else{
            System.out.println("Loading failed: Car is null/Wrong size or Transport can't load!");
        }
    }

    /**
     * First in, First out
     * @param canLoad
     * @param newX where the cars goes (x) after unloaded
     * @param newY where the cars goes (y) after unloaded
     * @return the unloaded car
     */
    T unloadFirstVehicle(boolean canLoad, double newX, double newY){
        if(canLoad && cars.size() > 0){
            T car = cars.poll();
            moveCar(car, newX, newY);
            return car;
        }
        System.out.println("No cars in this or is closed");
        return null;
    }

    /**
     * First in, last out
     * @param canLoad
     * @param newX where the cars goes (x) after unloaded
     * @param newY where the cars goes (y) after unloaded
     * @return the unloaded car
     */
    T unloadLastVehicle(boolean canLoad, double newX, double newY){
        if(canLoad && cars.size() > 0){
            T car = cars.pop();
            moveCar(car, newX, newY);
            return car;
        }

        System.out.println("No cars in this or is closed");
        return null;
    }

    private void moveCar(T car, double newX, double newY){
        car.setxPos(newX);
        car.setyPos(newY);
        car.unloadSelf();
    }



    private boolean correctSize(Size testSize){
        for(Size size : allowedSizes){
            if(size == testSize){
                return true;
            }
        }
        return false;
    }

}
