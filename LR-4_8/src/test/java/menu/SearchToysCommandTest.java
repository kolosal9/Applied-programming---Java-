package menu;

import menu.SearchToysCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.toys.Ball;
import room.toys.Toy;

import static org.junit.jupiter.api.Assertions.*;

class SearchToysCommandTest {

    private Room room;
    private SearchToysCommand searchToysCommand;

    @BeforeEach
    void setUp() {
        room = new Room(100.0);  // Створюємо кімнату з бюджетом 100
        searchToysCommand = new SearchToysCommand(room);
    }

    @Test
    void execute_FindsToysInGivenPriceRange() {
        // Створюємо кілька іграшок
        Toy ball = new Ball(1, "Football", 30.0, "5-10", "Rubber", 15.0);
        Toy doll = new Ball(2, "Doll", 50.0, "3-6", "Plastic", 20.0);
        Toy car = new Ball(3, "Car", 70.0, "4-7", "Metal", 25.0);

        // Додаємо їх у кімнату
        room.addToy(ball);
        room.addToy(doll);
        room.addToy(car);

        // Тепер шукатимемо іграшки у діапазоні 30-60
        searchToysCommand.execute(30.0, 60.0);

        // Перевіряємо, чи будуть виведені тільки іграшки з ціною від 30 до 60
        assertTrue(room.searchToys(30.0, 60.0).contains(ball));
        assertTrue(room.searchToys(30.0, 60.0).contains(doll));
        assertFalse(room.searchToys(30.0, 60.0).contains(car));
    }

    @Test
    void execute_DoesNotFindToysIfPriceIsOutOfRange() {
        // Створюємо кілька іграшок
        Toy ball = new Ball(1, "Football", 30.0, "5-10", "Rubber", 15.0);
        Toy doll = new Ball(2, "Doll", 50.0, "3-6", "Plastic", 20.0);

        // Додаємо їх у кімнату
        room.addToy(ball);
        room.addToy(doll);

        // Шукаємо іграшки за ціною більше 100
        searchToysCommand.execute(100.0, 200.0);

        // Перевіряємо, що жодна іграшка не була знайдена
        assertTrue(room.searchToys(100.0, 200.0).isEmpty());
    }

    @Test
    void execute_FindsNoToysWhenRoomIsEmpty() {
        // Кімната порожня, тому шукаємо іграшки в діапазоні 10-50
        searchToysCommand.execute(10.0, 50.0);

        // Перевіряємо, що жодної іграшки не знайдено
        assertTrue(room.searchToys(10.0, 50.0).isEmpty());
    }

    @Test
    void execute_FindsToysWhenPriceIsExactlyEqualToRange() {
        // Створюємо кілька іграшок
        Toy ball = new Ball(1, "Football", 30.0, "5-10", "Rubber", 15.0);
        Toy doll = new Ball(2, "Doll", 50.0, "3-6", "Plastic", 20.0);
        Toy car = new Ball(3, "Car", 70.0, "4-7", "Metal", 25.0);

        // Додаємо їх у кімнату
        room.addToy(ball);
        room.addToy(doll);
        room.addToy(car);

        // Шукаємо іграшки, ціна яких рівна 30, 50
        searchToysCommand.execute(30.0, 50.0);

        // Перевіряємо, що м'яч і лялька знайдені
        assertTrue(room.searchToys(30.0, 50.0).contains(ball));
        assertTrue(room.searchToys(30.0, 50.0).contains(doll));
    }
}
