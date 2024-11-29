package menu;

import menu.AddToyCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.toys.Ball;

import static org.junit.jupiter.api.Assertions.*;

class AddToyCommandTest {
    private Room room;
    private AddToyCommand addToyCommand;

    @BeforeEach
    void setUp() {
        room = new Room(100.0); // Бюджет 100
        addToyCommand = new AddToyCommand(room);
    }

    @Test
    void execute_AddsToyWhenBudgetIsSufficient() {
        Ball ball = new Ball(1, "Football", 30.0, "5-10", "Rubber", 15.0);
        room.addToy(ball);

        assertEquals(1, room.getToys().size());
        assertEquals(ball, room.getToys().get(0));
    }

    @Test
    void execute_DoesNotAddToyWhenBudgetIsExceeded() {
        Ball expensiveBall = new Ball(2, "ExpensiveBall", 200.0, "5-10", "Leather", 15.0);

        room.addToy(expensiveBall);
        assertEquals(0, room.getToys().size());
    }
}
