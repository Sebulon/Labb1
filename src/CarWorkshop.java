public class CarWorkshop <T extends Car>{
    private Transporter<T> garage;
    private int maxAmount;
    private final double xPos;
    private final double yPos;
    private boolean isOpen = true;

    public CarWorkshop(double xPos, double yPos, Size[] sizes, int maxAmount){
        this.xPos = xPos;
        this.yPos = yPos;
        garage = new Transporter<>(sizes);
        this.maxAmount = maxAmount;
    }

    public void loadCar(T car){
        boolean canLoad = maxAmount > garage.loadStack.size() && isOpen;
        garage.loadTransportable(xPos, yPos, car, canLoad);
    }

    public T unloadCar(){
        return garage.unloadFirstTransportable(isOpen, xPos - 10, yPos);
    }

    public void open(){
        isOpen = true;
    }

    public void close(){
        isOpen = false;
    }
}
