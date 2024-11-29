package menu;

import room.Room;
import room.toys.Toy;
import utils.LoggerSing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveToysCommand implements Command {
    private final Room room;
    private final LoggerSing logger = LoggerSing.getInstance(); // Ініціалізація логера

    public SaveToysCommand(Room room) {
        this.room = room;
        logger.log("SaveToysCommand initialized.");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name to save: ");
        String fileName = scanner.next();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Room Budget: " + room.getBudget() + "\n");
            writer.write("Current Toys:\n");

            for (Toy toy : room.getToys()) {
                writer.write(toy.serialize() + "\n");
            }

            System.out.println("Room state saved successfully to " + fileName);
            logger.log("Room state successfully saved to file: " + fileName);
        } catch (IOException e) {
            // Логування помилки
            logger.logError("Error saving room state to file: " + e.getMessage());
            // Відправлення повідомлення про помилку на email
        }
    }

    // Оновлений метод для збереження у файл за заданим іменем
    public void execute(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Room Budget: " + room.getBudget() + "\n");
            writer.write("Current Toys:\n");

            for (Toy toy : room.getToys()) {
                writer.write(toy.serialize() + "\n");
            }

            logger.log("Room state successfully saved to file: " + fileName);
        } catch (IOException e) {
            // Логування помилки
            logger.logError("Failed to save room state to file: " + fileName + ". Exception: " + e.getMessage());
            // Відправлення повідомлення про помилку на email
        }
    }
}
