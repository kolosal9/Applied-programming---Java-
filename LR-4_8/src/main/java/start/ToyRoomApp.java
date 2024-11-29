package start;

import menu.Menu;
import room.Room;
import utils.LoggerSing;

import java.util.Scanner;

public class ToyRoomApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoggerSing logger = LoggerSing.getInstance();

    public static void main(String[] args) {
        logger.log("Program started.");
        double budget = promptBudget();
        Room room = new Room(budget);
        Menu menu = new Menu(room);
        runMenu(menu);
    }

    public static double promptBudget() {
        double budget = 0.0;
        while (true) {
            System.out.print("Enter the budget for the room (positive number): ");
            try {
                budget = Double.parseDouble(scanner.nextLine());
                if (budget > 0) {
                    break;
                } else {
                    System.out.println("The budget must be a positive number. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        logger.log("Budget entered: " + budget);
        return budget;
    }

    public static void runMenu(Menu menu) {
        String choice;
        do {
            menu.displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            menu.executeCommand(choice);
        } while (!choice.equals("8"));
    }
}
