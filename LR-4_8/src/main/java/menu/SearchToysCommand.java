package menu;

import room.Room;
import utils.LoggerSing;

import java.util.Scanner;

public class SearchToysCommand implements Command {
    private final Room room;
    private final LoggerSing logger = LoggerSing.getInstance(); // Ініціалізація логера

    public SearchToysCommand(Room room) {
        this.room = room;
        logger.log("SearchToysCommand initialized.");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter price range (min-max): ");
            double min = scanner.nextDouble();
            double max = scanner.nextDouble();

            logger.log("Searching toys with price range: " + min + " - " + max);
            room.searchToys(min, max).forEach(toy -> {
                System.out.println(toy);
                logger.log("Found toy: " + toy);
            });
        } catch (Exception e) {
            System.out.println("Error during toy search: " + e.getMessage());
            logger.logError("Failed to search toys. Exception: " + e.getMessage());
        }
    }

    public void execute(double min, double max) {
        try {
            logger.log("Searching toys with price range: " + min + " - " + max);
            room.searchToys(min, max).forEach(toy -> {
                System.out.println(toy);
                logger.log("Found toy: " + toy);
            });
        } catch (Exception e) {
            System.out.println("Error during toy search: " + e.getMessage());
            logger.logError("Failed to search toys in range [" + min + ", " + max + "]. Exception: " + e.getMessage());
        }
    }
}
