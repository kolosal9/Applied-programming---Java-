package menu;

import menu.SortToysCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.toys.Ball;
import room.toys.Toy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortToysCommandTest {
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(200.0); // Ініціалізація кімнати з бюджетом
    }

    @Test
    void execute_SortsByName() {
        Ball ball1 = new Ball(1, "ZBall", 30.0, "5-10", "Rubber", 15.0);
        Ball ball2 = new Ball(2, "ABall", 20.0, "5-10", "Rubber", 10.0);

        room.addToy(ball1);
        room.addToy(ball2);

        SortToysCommand sortCommand = new SortToysCommand(room, "name");
        sortCommand.execute();

        // Використання Collectors.toList() для сумісності з Java 8+
        List<String> toyNames = room.getToys().stream()
                .map(Toy::getName)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("ABall", "ZBall"), toyNames);
    }

    @Test
    void execute_SortsByPrice() {
        Ball ball1 = new Ball(1, "ZBall", 30.0, "5-10", "Rubber", 15.0);
        Ball ball2 = new Ball(2, "ABall", 20.0, "5-10", "Rubber", 10.0);

        room.addToy(ball1);
        room.addToy(ball2);

        SortToysCommand sortCommand = new SortToysCommand(room, "price");
        sortCommand.execute();

        // Використання Collectors.toList() для сумісності з Java 8+
        List<Double> toyPrices = room.getToys().stream()
                .map(Toy::getPrice)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(20.0, 30.0), toyPrices);
    }

    @Test
    void execute_SortsById() {
        Ball ball1 = new Ball(2, "ZBall", 30.0, "5-10", "Rubber", 15.0);
        Ball ball2 = new Ball(1, "ABall", 20.0, "5-10", "Rubber", 10.0);

        room.addToy(ball1);
        room.addToy(ball2);

        SortToysCommand sortCommand = new SortToysCommand(room, "id");
        sortCommand.execute();

        // Використання Collectors.toList() для сумісності з Java 8+
        List<Integer> toyIds = room.getToys().stream()
                .map(Toy::getId)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2), toyIds);
    }
}
