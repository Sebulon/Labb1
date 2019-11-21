import java.util.ArrayDeque;
import java.util.Deque;

public class StackLoadCar {
    Deque<Car> cars;
    Size[] availableSizes;
    double loadDistance = 10;

    public StackLoadCar(Size[] sizes){
        availableSizes = sizes;

        cars = new ArrayDeque<>();
    }

    void loadVehicle(double xPos, double yPos, Car car, boolean canLoad){
        if(car != null && correctSize(car.getSize()) && canLoad){
            cars.push(car);
            car.loadSelf();
            car.stopEngine();
        }
    }

    /**
     * First in, First out
     * @return
     */
    Car unloadFirstVehicle(boolean canLoad){
        if(canLoad && cars.size() > 0)
            return cars.poll();
        System.out.println("No cars in this or is closed");
        return null;
    }

    /**
     * First in, last out
     * @param canLoad
     * @return
     */
    Car unloadLastVehicle(boolean canLoad){
        if(canLoad && cars.size() > 0)
            return cars.pop();
        System.out.println("No cars in this or is closed");
        return null;
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
