/**
 * A workshop for cars
 * @param <T> a specification of which cars that can be in this workshop
 */
public class CarWorkshop <T extends Car>{
    private Container<T> garage;
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
        if(canLoad)
            garage.loadTransportable(xPos, yPos, car);
    }

    public T unloadCar(){
        if(isOpen)
            return garage.unloadFirstTransportable( xPos - 10, yPos);
        return null;
    }

    public void open(){
        isOpen = true;
    }

    public void close(){
        isOpen = false;
    }
}
