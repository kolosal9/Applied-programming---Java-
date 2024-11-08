package menu;
import java.util.Map;
import java.util.HashMap;

// Клас для меню, який викликає відповідні команди
public class Menu {
    private Map<String, Command> commands = new HashMap<>();

    public Menu() {
        commands.put("1", new AddToyCommand());
        commands.put("2", new RemoveToyCommand());
        commands.put("3", new SortToysCommand());
        commands.put("4", new SearchToysCommand());
        commands.put("5", new SaveToysCommand());  // Команда для збереження
        commands.put("6", new LoadToysCommand());  // Команда для завантаження
        commands.put("7", new DisplayRoomStateCommand());
        commands.put("8", this::exitProgram);  // Оновлено пункт меню
    }

    public void displayMenu() {
        System.out.println("1. Add Toy");
        System.out.println("2. Remove Toy");
        System.out.println("3. Sort Toys");
        System.out.println("4. Search Toys");
        System.out.println("5. Save Toys");
        System.out.println("6. Load Toys");
        System.out.println("7. Display Room State");
        System.out.println("8. Exit");
    }

    public void executeCommand(String choice) {
        Command command = commands.get(choice);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void exitProgram() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
