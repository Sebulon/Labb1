import java.awt.*;

public class Koeningsegg extends Car{

    private boolean turboOn;
    private static final double trim = 1.3;

    Koeningsegg(){
        super(2, 500, Color.WHITE, "Agera");
        turboOn = false;
        stopEngine();
    }

    protected double speedFactor() {
        double turbo = turboOn ? 1.3 : 1;
        return turbo * enginePower * trim * 0.01;
    }


}
