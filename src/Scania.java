import java.awt.*;

public class Scania extends Car {

    boolean turboOn = false;

    public Scania(){
        super(2, 350, Color.white, "LBS141");
        stopEngine();
    }

    public void turboOn(){
        turboOn = true;
    }

    public void turboOff(){
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn);
            turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
