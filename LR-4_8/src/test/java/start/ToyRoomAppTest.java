package start;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToyRoomAppTest {

    @Test
    public void testPromptBudget_ValidInput() {
        String simulatedInput = "100.50\n"; // Симульоване введення
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes())); // Підміна System.in
            double budget = ToyRoomApp.promptBudget(); // Виклик методу
            assertEquals(100.50, budget, "The budget should match the input value");
        } finally {
            System.setIn(originalIn); // Відновлення System.in
        }
    }
}