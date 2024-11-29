package menu;

import menu.LoadToysCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import room.Room;
import room.toys.Car;
import room.toys.Toy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

class LoadToysCommandTest {
    private Room room;
    private LoadToysCommand loadToysCommand;

    @BeforeEach
    void setUp() {
        room = mock(Room.class);  // Замокуємо Room для тесту
        loadToysCommand = new LoadToysCommand(room);
    }

    @Test
    void testLoadToysFromFile() throws IOException {
        // Підготовка даних для тесту
        String fileName = "toys.txt";
        String fileContent = "Room Budget: 100.0\n" +
                "Current Toys:\n" +
                "1,Football,30.0,5-10,Rubber,car\n";

        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine())
                .thenReturn("Room Budget: 100.0") // Перший рядок — бюджет
                .thenReturn("Current Toys:") // Заголовок
                .thenReturn("1,Football,30.0,5-10,Rubber,car") // Іграшка
                .thenReturn(null); // Кінець файлу

        // Використовуємо замоканий BufferedReader
        try (BufferedReader reader = mockReader) {
            // Викликаємо метод для завантаження іграшок
            loadToysCommand.execute(fileName);  // Викликаємо метод з параметром
            verify(room, times(1)).setBudget(100.0);  // Перевірка, чи встановлений бюджет
            verify(room, times(1)).addToy(any(Car.class));  // Перевірка, чи додана іграшка
        }
    }

    @Test
    void testLoadToysFileIsEmpty() throws IOException {
        String emptyFileName = "emptyToys.txt";
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn(null);  // Файл порожній

        // Викликаємо метод для тестування порожнього файлу
        try (BufferedReader reader = mockReader) {
            loadToysCommand.execute(emptyFileName);  // Викликаємо метод з параметром
            verify(room, times(0)).addToy(any(Toy.class));  // Не повинно бути жодних додавань
        }
    }

    @Test
    void testLoadToysFileHasInvalidData() throws IOException {
        String invalidDataFileName = "invalidDataToys.txt";
        String invalidData = "Invalid Toy Data";

        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenReturn("Room Budget: 100.0") // Перший рядок — бюджет
                .thenReturn("Current Toys:")
                .thenReturn(invalidData) // Некоректний рядок
                .thenReturn(null); // Кінець файлу

        try (BufferedReader reader = mockReader) {
            loadToysCommand.execute(invalidDataFileName);  // Викликаємо метод з параметром
            verify(room, times(0)).addToy(any(Toy.class));  // Іграшка не повинна бути додана
        }
    }
}
