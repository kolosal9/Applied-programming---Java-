package menu;

import room.Room;
import utils.LoggerSing;

import java.util.Map;
import java.util.HashMap;

public class Menu {
    private final Map<String, Command> commands = new HashMap<>();
    private final LoggerSing logger = LoggerSing.getInstance(); // Ініціалізація логера

    public Menu(Room room) { // Передаємо Room у конструктор Menu
        commands.put("1", new AddToyCommand(room));
        commands.put("2", new RemoveToyCommand(room));
        commands.put("3", new SortToysCommand(room));
        commands.put("4", new SearchToysCommand(room));
        commands.put("5", new SaveToysCommand(room));
        commands.put("6", new LoadToysCommand(room));
        commands.put("7", new DisplayRoomStateCommand(room));
        commands.put("8", this::exitProgram);

        logger.log("Menu initialized with commands.");
    }

    public void displayMenu() {
        System.out.println("\n1. Add Toy");
        System.out.println("2. Remove Toy");
        System.out.println("3. Sort Toys");
        System.out.println("4. Search Toys");
        System.out.println("5. Save Toys");
        System.out.println("6. Load Toys");
        System.out.println("7. Display Room State");
        System.out.println("8. Exit");

        logger.logDebug("Menu displayed to the user.");
    }

    public void executeCommand(String choice) {
        logger.log("User selected menu option: " + choice);

        Command command = commands.get(choice);
        if (command != null) {
            try {
                logger.logDebug("Executing command for menu option: " + choice);
                command.execute();
                logger.log("Command executed successfully for option: " + choice);
            } catch (Exception e) {
                logger.logError("Error executing command for option: " + choice + ". Exception: " + e.getMessage());
            }
        } else {
            logger.logWarning("Invalid menu option selected: " + choice);
            System.out.println("Invalid choice.");
        }
    }

    private void exitProgram() {
        logger.log("Exiting the program...");
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
