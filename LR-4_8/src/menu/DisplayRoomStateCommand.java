package menu;

public class DisplayRoomStateCommand implements Command {
    @Override
    public void execute() {
        // Логіка виведення стану кімнати
        System.out.println("Displaying current room state...");
    }
}
