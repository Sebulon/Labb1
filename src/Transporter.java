import java.util.ArrayDeque;
import java.util.Deque;

public class Transporter<T extends Transportable> {
    Deque<T> loadStack;
    Size[] allowedSizes;
    double loadDistance = 10;

    public Transporter(Size[] sizes){
        allowedSizes = sizes;

        loadStack = new ArrayDeque<>();
    }

    /**
     * Loads the vehicle and moves the vehicle to the correct position
     * @param xPos
     * @param yPos
     * @param transportable object to be loaded
     * @param canLoad Boolean that decides if it can be loaded
     */
    void loadTransportable(double xPos, double yPos, T transportable, boolean canLoad){
        if(transportable != null && correctSize(transportable.getSize()) && canLoad){
            if(Math.abs(xPos - transportable.getX()) < loadDistance && Math.abs(yPos - transportable.getY()) < loadDistance) {
                loadStack.push(transportable);
                transportable.loadSelf();
            } else{
                System.out.println("Loading failed: object is too far away!");
            }
        } else{
            System.out.println("Loading failed: object is null/Wrong size or Transport can't load!");
        }
    }

    /**
     * First in, First out
     * @param canLoad
     * @param newX where the cars goes (x) after unloaded
     * @param newY where the cars goes (y) after unloaded
     * @return the unloaded car
     */
    T unloadFirstTransportable(boolean canLoad, double newX, double newY){
        if(canLoad && loadStack.size() > 0){
            T transportable = loadStack.poll();
            ejectTransportable(transportable, newX, newY);
            return transportable;
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
    T unloadLastTransportable(boolean canLoad, double newX, double newY){
        if(canLoad && loadStack.size() > 0){
            T transportable = loadStack.pop();
            ejectTransportable(transportable, newX, newY);
            return transportable;
        }

        System.out.println("No cars in this or is closed");
        return null;
    }

    private void ejectTransportable(T transportable, double newX, double newY){
        transportable.setX(newX);
        transportable.setY(newY);
        transportable.unloadSelf();
    }

    void followTransport (double speed, double direction){
        for(T transportable : loadStack){
            transportable.followTransport(speed, direction);
        }
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
