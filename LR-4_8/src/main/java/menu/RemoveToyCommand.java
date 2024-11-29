package menu;

import room.Room;
import utils.LoggerSing;

import java.util.Scanner;

public class RemoveToyCommand implements Command {
    private final Room room;
    private final LoggerSing logger = LoggerSing.getInstance(); // Ініціалізація логера

    public RemoveToyCommand(Room room) {
        this.room = room;
        logger.log("RemoveToyCommand initialized.");
    }

    @Override
    public void execute() {
        System.out.print("Enter toy ID to remove: ");
        Scanner scanner = new Scanner(System.in);
        int toyId;

        try {
            toyId = scanner.nextInt();
            logger.log("User entered toy ID to remove: " + toyId);
            room.removeToy(toyId);
            System.out.println("Toy removed successfully.");
            logger.log("Toy with ID " + toyId + " removed successfully.");
        } catch (Exception e) {
            System.out.println("Error: Invalid input or toy ID.");
            logger.logError("Failed to remove toy. Exception: " + e.getMessage());
        }
    }

    public void execute(int toyId) {
        try {
            logger.log("Attempting to remove toy with ID: " + toyId);
            room.removeToy(toyId);
            System.out.println("Toy with ID " + toyId + " removed successfully.");
            logger.log("Toy with ID " + toyId + " removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing toy with ID " + toyId + ".");
            logger.logError("Failed to remove toy with ID " + toyId + ". Exception: " + e.getMessage());
        }
    }
}
