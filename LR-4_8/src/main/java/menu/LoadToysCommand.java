package menu;

import room.Room;
import room.toys.Toy;
import room.toys.Car;
import room.toys.Doll;
import room.toys.Block;
import room.toys.Animal;
import room.toys.Ball;
import utils.LoggerSing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadToysCommand implements Command {
    private final Room room;
    private final LoggerSing logger = LoggerSing.getInstance();

    public LoadToysCommand(Room room) {
        this.room = room;
    }

    // Оригінальний метод execute, який запитує файл у користувача
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name to load: ");
        String fileName = scanner.next();

        logger.log("User provided file name: " + fileName);
        loadToysFromFile(fileName);
    }

    // Новий метод execute, який приймає ім'я файлу як параметр
    public void execute(String fileName) {
        logger.log("Executing LoadToysCommand with file: " + fileName);
        loadToysFromFile(fileName);
    }

    // Метод, який виконує завантаження іграшок із файлу
    private void loadToysFromFile(String fileName) {
        logger.log("Starting to load toys from file: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line == null) {
                logger.logWarning("File is empty: " + fileName);
                System.out.println("Error: File is empty.");
                return;
            }

            // Завантаження бюджету
            if (line.startsWith("Room Budget:")) {
                double budget = Double.parseDouble(line.split(": ")[1]);
                room.setBudget(budget);
                logger.log("Room budget set to: " + budget);
                System.out.println("Room Budget set to: " + budget);
            }

            // Очищення поточних іграшок
            room.clearToys();
            logger.log("Cleared existing toys from room.");

            // Завантаження іграшок
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Current Toys:")) {
                    continue; // Пропускаємо заголовок
                }

                String[] parts = line.split(",");
                try {
                    Toy toy = createToy(parts);
                    if (toy != null) {
                        room.addToy(toy);
                        logger.log("Toy added: " + toy.getDescription());
                        System.out.println("Toy added: " + toy.getDescription());
                    }
                } catch (Exception e) {
                    logger.logWarning("Error processing toy: " + String.join(",", parts));
                    System.out.println("Error processing toy: " + e.getMessage());
                }
            }

            logger.log("Room state loaded successfully from " + fileName);
            System.out.println("Room state loaded successfully from " + fileName);
        } catch (IOException e) {
            logger.logError("Error loading room state from file: " + fileName + ". Exception: " + e.getMessage());
            System.out.println("Error loading room state from file: " + e.getMessage());
        }
    }

    // Фабричний метод для створення іграшок
    private Toy createToy(String[] parts) {
        String type = parts[4];
        logger.logDebug("Creating toy of type: " + type);

        switch (type) {
            case "car":
                return Car.deserialize(parts);
            case "doll":
                return Doll.deserialize(parts);
            case "block":
                return Block.deserialize(parts);
            case "animal":
                return Animal.deserialize(parts);
            case "ball":
                return Ball.deserialize(parts);
            default:
                logger.logWarning("Unknown toy type: " + type);
                throw new IllegalArgumentException("Unknown toy type: " + type);
        }
    }
}
