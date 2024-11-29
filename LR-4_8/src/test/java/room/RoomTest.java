package room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.toys.Block;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(150.0); // Бюджет 150
    }

    @Test
    void addToy_AddsToyWhenBudgetIsSufficient() {
        Block block = new Block(1, "Lego", 50.0, "5-10", "Square", "Red");
        assertTrue(room.addToy(block));
        assertEquals(1, room.getToys().size());
    }

    @Test
    void addToy_DoesNotAddToyWhenBudgetIsExceeded() {
        Block expensiveBlock = new Block(2, "ExpensiveLego", 200.0, "5-10", "Rectangle", "Blue");
        assertFalse(room.addToy(expensiveBlock));
        assertEquals(0, room.getToys().size());
    }

    @Test
    void getTotalCost_ReturnsCorrectSum() {
        Block block1 = new Block(1, "Lego1", 50.0, "5-10", "Square", "Red");
        Block block2 = new Block(2, "Lego2", 70.0, "5-10", "Rectangle", "Blue");

        room.addToy(block1);
        room.addToy(block2);

        assertEquals(120.0, room.getTotalCost());
    }
}
