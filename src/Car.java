import java.awt.*;

public abstract class Car extends MotorVehicle {

    private final int nrDoors;
    private boolean loaded = false;


    /**
     *
     * @param nrDoors
     * @param enginePower
     * @param color
     * @param modelName
     */
    public Car(int nrDoors, double enginePower, Color color, String modelName, Size size){
        super(enginePower, color, modelName, size);
        this.nrDoors = nrDoors;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    void setDirection(double direction) {
        if(loaded)
            this.direction = direction;
    }

    void setxPos(double xPos) {
        if(loaded)
            this.xPos = xPos;
    }

    void setyPos(double yPos) {
        if(loaded)
            this.yPos = yPos;
    }


    public void loadSelf(){
        loaded = true;
    }

    public void unloadSelf(){
        loaded = false;
    }

}
