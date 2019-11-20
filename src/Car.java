import java.awt.*;

public abstract class Car implements Engine, Movable{

    protected int nrDoors;
    protected double enginePower;
    protected Color color;
    protected double currentSpeed = 0;
    protected String modelName;
    private double direction = 0;
    private double rotationSpeed = Math.PI / 4;
    private double xPos;
    private double yPos;
    private Size size;


    /**
     *
     * @param nrDoors
     * @param enginePower
     * @param color
     * @param modelName
     */
    public Car(int nrDoors, double enginePower, Color color, String modelName, Size size){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.size = size;

        stopEngine();
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The angle of the car in degrees, where 0 deg is the same direction as the unit circle 0 deg
     * @return degrees
     */
    public double getDirection() {
        return direction * 180 / Math.PI;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public Size getSize(){
        return size;
    }

    /**
     * Determines the acceleration factor for the car
     * @return
     */
    protected abstract double speedFactor();

    /**
     * Rotates left
     * Changes direction
     */
    public void turnLeft() {
        direction += rotationSpeed;
        if(Math.PI * 2 < direction){
            direction -= Math.PI * 2;
        }
    }

    /**
     * Rotates right
     * Changes direction
     */
    public void turnRight(){
        direction -= rotationSpeed;
        if(direction < 0){
            direction += 2 * Math.PI;
        }
    }

    /**
     * xPos and yPos is changed dependent on direction and speed
     */
    public void move() {
        xPos += currentSpeed * Math.cos(direction);
        yPos += currentSpeed * Math.sin(direction);
    }

    /**
     * Sets currentSpeed to > 0
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Is dependent on speedFactor
     * @param amount how much gas is applied
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    /**
     * Is dependent on speedFactor?
     * @param amount how much brake is applied
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Gas increases currentSpeed depending on the attribute
     * @param amount Value between 0 - 1, more makes for higher acceleration
     */
    public void gas(double amount){
        if(amount > 1){
            System.out.println("Gas amount is more than 1");
            return;
        }else if(amount < 0){
            System.out.println("Gas is less than 0");
            return;
        }
        incrementSpeed(amount);
    }

    /**
     * Brake decreases currentSpeed depending on the attribute
     * @param amount Value between 0 - 1, more makes for greater retardation
     */

    void brake(double amount){
        if(amount > 1){
            System.out.println("Brake amount is more than 1");
            return;
        }else if(amount < 0){
            System.out.println("Brake is less than 0");
            return;
        }
        decrementSpeed(amount);
    }
}
