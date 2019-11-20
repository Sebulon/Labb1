import java.util.Scanner;

public class CommandLine {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Car currentCar = null;
        boolean hasChosenACar = false;
        while(!hasChosenACar){
            System.out.println("Choose a car");
            String getCar = scanner.nextLine();
            switch (getCar){
                case "Koeningsegg":
                    currentCar = new Koeningsegg();
                    hasChosenACar = true;
                    break;
                case "Volvo":
                    currentCar = new Volvo240();
                    hasChosenACar = true;
                    break;
                case "Saab":
                    currentCar = new Saab95();
                    hasChosenACar = true;
                    break;
                default:
                    System.out.println("Car is not in stock");
                    break;
            }
        }
        System.out.println("You chose " + currentCar.getClass().getSimpleName());
        while (true){
            System.out.println("Chose a command");
            String command = scanner.nextLine();
            switch (command.toLowerCase()){
                case "stop":
                    currentCar.stopEngine();
                    break;
                case "start":
                    currentCar.startEngine();
                    break;
                case "gas":
                    System.out.println("How much gas?");
                    double input = scanner.nextDouble();
                    currentCar.gas(input);
                    scanner.nextLine();
                    break;
                case "brake":
                    System.out.println("How hard to brake?");
                    double input1 = scanner.nextDouble();
                    currentCar.brake(input1);
                    scanner.nextLine();
                    break;
                case "turn right":
                    currentCar.turnRight();
                    break;
                case "turn left":
                    currentCar.turnLeft();
                    break;
                case "do nothing":
                    break;
                case "abort":
                    return;
                default:
                    System.out.println("Invalid command");
                    break;
            }
            currentCar.move();
            System.out.println("The current car is on (" + currentCar.getxPos() + ", " + currentCar.getyPos() + ")");
            System.out.println("Current speed: " + currentCar.getCurrentSpeed());
            System.out.println("Current Radians: " + currentCar.getDirection());
        }
    }
}
