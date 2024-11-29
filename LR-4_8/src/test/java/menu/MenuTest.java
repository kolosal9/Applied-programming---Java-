package menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import menu.Menu;
import menu.Command;
import java.lang.reflect.Field;
import java.util.Map;

import static org.mockito.Mockito.*;

class MenuTest {

    private Room mockRoom;
    private Menu menu;

    @BeforeEach
    void setUp() {
        mockRoom = mock(Room.class);
        menu = new Menu(mockRoom);
    }

    @Test
    void testExecuteCommand_AddToy() throws NoSuchFieldException, IllegalAccessException {
        // Мокування команди для додавання іграшки
        Command mockAddToyCommand = mock(Command.class);

        // Отримуємо доступ до приватного поля 'commands' за допомогою рефлексії
        Field commandsField = Menu.class.getDeclaredField("commands");
        commandsField.setAccessible(true); // дозволяємо доступ до приватного поля

        // Отримуємо значення поля 'commands'
        @SuppressWarnings("unchecked")
        Map<String, Command> commands = (Map<String, Command>) commandsField.get(menu);

        // Замінюємо команду додавання іграшки на моковану
        commands.put("1", mockAddToyCommand);

        // Викликаємо команду додавання іграшки
        menu.executeCommand("1");

        // Перевіряємо, що метод execute був викликаний для цієї команди
        verify(mockAddToyCommand, times(1)).execute();
    }
}
