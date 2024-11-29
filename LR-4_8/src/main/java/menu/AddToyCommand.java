package menu;

import room.Room;
import room.toys.*;
import utils.LoggerSing;

import java.util.Scanner;

public class AddToyCommand implements Command {
    private final Room room;
    private final LoggerSing logger = LoggerSing.getInstance();

    public AddToyCommand(Room room) {
        this.room = room;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        logger.log("Starting AddToyCommand execution.");

        System.out.print("Enter toy type (Car, Doll, Block, Animal, Ball): ");
        String toyType = scanner.next().toLowerCase();
        logger.log("Toy type entered: " + toyType);

        try {
            System.out.print("Enter toy details (id, name, price, age group): ");
            int id = scanner.nextInt();
            String name = scanner.next();
            double price = scanner.nextDouble();
            String ageGroup = scanner.next();

            Toy toy = null;

            switch (toyType) {
                case "car":
                    System.out.print("Enter car model: ");
                    String model = scanner.next();
                    System.out.print("Is the car electric? (true/false): ");
                    boolean electric = scanner.nextBoolean();
                    toy = new Car(id, name, price, ageGroup, model, electric);
                    logger.log("Created toy: " + toy);
                    break;
                case "doll":
                    System.out.print("Enter doll height: ");
                    double height = scanner.nextDouble();
                    System.out.print("Enter doll material: ");
                    String dollMaterial = scanner.next();
                    toy = new Doll(id, name, price, ageGroup, height, dollMaterial);
                    logger.log("Created toy: " + toy);
                    break;
                case "block":
                    System.out.print("Enter block shape: ");
                    String shape = scanner.next();
                    System.out.print("Enter block color: ");
                    String color = scanner.next();
                    toy = new Block(id, name, price, ageGroup, shape, color);
                    logger.log("Created toy: " + toy);
                    break;
                case "animal":
                    System.out.print("Enter animal species: ");
                    String species = scanner.next();
                    System.out.print("Enter animal material: ");
                    String animalMaterial = scanner.next();
                    toy = new Animal(id, name, price, ageGroup, species, animalMaterial);
                    logger.log("Created toy: " + toy);
                    break;
                case "ball":
                    System.out.print("Enter ball material: ");
                    String ballMaterial = scanner.next();
                    System.out.print("Enter ball diameter: ");
                    double diameter = scanner.nextDouble();
                    toy = new Ball(id, name, price, ageGroup, ballMaterial, diameter);
                    logger.log("Created toy: " + toy);
                    break;
                default:
                    logger.logWarning("Unknown toy type: " + toyType);
                    System.out.println("Unknown toy type: " + toyType);
                    return;
            }

            if (toy != null) {
                if (room.getTotalCost() + price > room.getBudget()) {
                    String message = "Cannot add toy! Total cost exceeds the room budget.";
                    logger.logWarning(message);
                    System.out.println(message);
                } else {
                    room.addToy(toy);
                    logger.log("Toy added successfully: " + toy);
                    System.out.println("Toy added successfully: " + toy);
                }
            }
        } catch (Exception e) {
            String errorMessage = "Error adding toy: " + e.getMessage();
            logger.logError(errorMessage);
            System.out.println(errorMessage);
        }
        logger.log("AddToyCommand execution finished.");
    }
}
