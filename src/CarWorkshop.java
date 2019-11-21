import java.util.ArrayDeque;

public class CarWorkshop <T extends Car>{
    private StackLoadCar garage;
    private int maxAmount;
    private final double xPos;
    private final double yPos;
    private boolean isOpen = true;

    public CarWorkshop(double xPos, double yPos, Size[] sizes, int maxAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        garage = new StackLoadCar(sizes);
        this.maxAmount = maxAmount;
    }

    public void loadCar(T car){
        boolean canLoad = maxAmount < garage.cars.size() && isOpen;
        garage.loadVehicle(xPos, yPos, car, canLoad);
    }

    public void unloadCar(){
        garage.unloadFirstVehicle(isOpen);
    }
}
