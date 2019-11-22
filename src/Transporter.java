/**
 * The transporter handles loading and transporting transportable object
 * @param <T> T should be an object that can be transported
 */
public class Transporter<T extends Transportable> extends Container<T>{

    public Transporter(Size[] sizes){
        super(sizes);
    }


    /**
     * If the transporter is an object able to move, this method moves the loaded objects with it
     * @param speed
     * @param direction
     */
    void followTransport (double speed, double direction){
        for(T transportable : loadStack){
            transportable.followTransport(speed, direction);
        }
    }



}
