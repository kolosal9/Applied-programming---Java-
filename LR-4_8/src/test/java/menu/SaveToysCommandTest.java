package menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.toys.Animal;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SaveToysCommandTest {
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(200.0); // Бюджет кімнати
        room.addToy(new Animal(1, "Lion", 50.0, "5-10", "Wild", "Plastic")); // Додаємо іграшку
    }

    @Test
    void execute_SavesRoomStateToFile() {
        // Викликаємо метод `execute` з параметром
        SaveToysCommand saveCommand = new SaveToysCommand(room);
        String fileName = "./file_test.txt";  // Використовуємо поточну директорію
        saveCommand.execute(fileName);  // Викликаємо метод з параметром

        // Додатково даємо час для запису файлу
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Файл, який очікується
        File saveFile = new File(fileName);

        // Перевірка існування файлу
        assertTrue(saveFile.exists(), "Файл збереження іграшок не було створено.");

        // Додаткова перевірка, що файл не є порожнім
        assertTrue(saveFile.length() > 0, "Файл збереження є порожнім.");
    }

}
