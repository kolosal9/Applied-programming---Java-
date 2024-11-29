package menu;

import menu.RemoveToyCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

class RemoveToyCommandTest {

    private Room mockRoom;
    private RemoveToyCommand removeToyCommand;

    @BeforeEach
    void setUp() {
        mockRoom = mock(Room.class); // Мокуємо об'єкт Room
        removeToyCommand = new RemoveToyCommand(mockRoom);
    }

    @Test
    void testExecuteWithIdParameter() {
        int toyId = 1;

        // Викликаємо метод execute з ID
        removeToyCommand.execute(toyId);

        // Перевіряємо, що removeToy викликається з правильним ID
        verify(mockRoom, times(1)).removeToy(toyId);
    }

    @Test
    void testExecuteWithScanner() {
        // Симулюємо ввід користувача через консоль
        String simulatedInput = "2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Викликаємо метод execute, який читає дані зі стандартного вводу
        removeToyCommand.execute();

        // Перевіряємо, чи був викликаний метод removeToy з правильним ID
        verify(mockRoom, times(1)).removeToy(2);

        // Повертаємо стандартний ввід до оригінального стану
        System.setIn(System.in);
    }
}
