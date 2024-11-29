package menu;

import room.Room;
import utils.LoggerSing;

import java.util.Scanner;

public class SortToysCommand implements Command {
    private final Room room;
    private String sortingParameter;
    private final LoggerSing logger = LoggerSing.getInstance(); // Ініціалізація логера

    public SortToysCommand(Room room) {
        this.room = room;
    }

    public SortToysCommand(Room room, String sortingParameter) {
        this.room = room;
        this.sortingParameter = sortingParameter;
    }

    @Override
    public void execute() {
        try {
            if (sortingParameter == null) {
                System.out.println("Enter sorting parameter (price, name, id):");
                Scanner scanner = new Scanner(System.in);
                sortingParameter = scanner.next();
            }

            logger.log("Sorting toys by: " + sortingParameter);

            room.getToys().sort((toy1, toy2) -> {
                switch (sortingParameter) {
                    case "price":
                        return Double.compare(toy1.getPrice(), toy2.getPrice());
                    case "name":
                        return toy1.getName().compareTo(toy2.getName());
                    case "id":
                        return Integer.compare(toy1.getId(), toy2.getId());
                    default:
                        return 0;
                }
            });

            logger.log("Toys sorted successfully by " + sortingParameter);
            System.out.println("Toys sorted successfully by " + sortingParameter + ".");
        } catch (Exception e) {
            logger.logError("Error during sorting: " + e.getMessage());
            System.out.println("Error during sorting: " + e.getMessage());
        }
    }
}
