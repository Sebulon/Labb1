import java.util.ArrayDeque;
import java.util.Deque;

public class StackLoadCar<T extends Car> {
    Deque<T> cars;
    Size[] availableSizes;
    double loadDistance = 10;

    public StackLoadCar(Size[] sizes){
        availableSizes = sizes;

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
            cars.push(car);
            car.loadSelf();
            car.stopEngine();
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
        boolean isRightSize = false;

        for(Size size : availableSizes){
            if(size == testSize){
                isRightSize = true;
            }
        }
        return isRightSize;
    }

}
