import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Handles storage of transportable
 * @param <T>
 */
public class Container<T extends Transportable> {

    Deque<T> loadStack;
    private final Size[] allowedSizes;
    private final double loadDistance = 10;

    public Container(Size[] sizes){
        allowedSizes = sizes;
        loadStack = new ArrayDeque<T>();
    }


    /**
     * Loads the object and moves it to the correct position
     * @param xPos
     * @param yPos
     * @param transportable object to be loaded
     */
    void loadTransportable(double xPos, double yPos, T transportable){
        if(transportable != null && correctSize(transportable.getSize())){
            if(Math.abs(xPos - transportable.getX()) < loadDistance && Math.abs(yPos - transportable.getY()) < loadDistance) {
                loadStack.push(transportable);
                transportable.loadSelf();
            } else{
                System.out.println("Loading failed: object is too far away!");
            }
        } else{
            System.out.println("Loading failed: object is null/Wrong size");
        }
    }

    /**
     * First in, First out
     * @param newX where the object goes (x) after unloading
     * @param newY where the object goes (y) after unloading
     * @return the unloaded car
     */
    T unloadFirstTransportable(double newX, double newY){
        if(loadStack.size() > 0){
            T transportable = loadStack.poll();
            ejectTransportable(transportable, newX, newY);
            return transportable;
        }
        System.out.println("No cars in this or is closed");
        return null;
    }

    /**
     * First in, last out
     * @param newX where the object goes (x) after unloading
     * @param newY where the object goes (y) after unloading
     * @return the unloaded car
     */
    T unloadLastTransportable(double newX, double newY){
        if(loadStack.size() > 0){
            T transportable = loadStack.pop();
            ejectTransportable(transportable, newX, newY);
            return transportable;
        }

        System.out.println("No objects in this");
        return null;
    }

    private void ejectTransportable(T transportable, double newX, double newY){
        transportable.setX(newX);
        transportable.setY(newY);
        transportable.unloadSelf();
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
