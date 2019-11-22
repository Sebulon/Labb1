import java.util.ArrayDeque;

public class CarWorkshop <T extends Car>{
    private StackLoadCar<T> garage;
    private int maxAmount;
    private final double xPos;
    private final double yPos;
    private boolean isOpen = true;

    public CarWorkshop(double xPos, double yPos, Size[] sizes, int maxAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        garage = new StackLoadCar<>(sizes);
        this.maxAmount = maxAmount;
    }

    public void loadCar(T car){
        boolean canLoad = maxAmount > garage.cars.size() && isOpen;
        garage.loadVehicle(xPos, yPos, car, canLoad);
    }

    public T unloadCar(){
        return garage.unloadFirstVehicle(isOpen, xPos - 10, yPos);
    }

    public void open(){
        isOpen = true;
    }

    public void close(){
        isOpen = false;
    }
}
