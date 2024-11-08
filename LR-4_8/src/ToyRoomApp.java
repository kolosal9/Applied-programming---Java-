import menu.Menu;

import java.util.Scanner;

public class ToyRoomApp {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            menu.displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            menu.executeCommand(choice);
        } while (!choice.equals("7"));
    }
}
