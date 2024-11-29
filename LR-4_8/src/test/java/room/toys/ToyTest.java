package room.toys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ToyTest {

    @Test
    public void testGetters() {
        Toy toy = new TestToy(1, "Test Toy", 25.50, "3-5 years");
        assertEquals(1, toy.getId(), "ID should match the value passed to constructor");
        assertEquals("Test Toy", toy.getName(), "Name should match the value passed to constructor");
        assertEquals(25.50, toy.getPrice(), "Price should match the value passed to constructor");
        assertEquals("3-5 years", toy.getAgeGroup(), "AgeGroup should match the value passed to constructor");
    }

    @Test
    public void testSerialize() {
        Toy toy = new TestToy(2, "Another Toy", 15.75, "6-8 years");
        String serialized = toy.serialize();
        assertEquals("2,Another Toy,15.75,6-8 years,TestType", serialized, "Serialization output should match expected format");
    }

    @Test
    public void testDeserialize() {
        String line = "3,Sample Toy,30.00,9-12 years";
        String[] deserialized = Toy.deserialize(line);
        String[] expected = {"3", "Sample Toy", "30.00", "9-12 years"};
        assertArrayEquals(expected, deserialized, "Deserialization output should match expected array");
    }

    @Test
    public void testToString() {
        Toy toy = new TestToy(4, "Cool Toy", 40.00, "1-3 years");
        String expected = "Toy [ID=4, Name=Cool Toy, Price=40.0, AgeGroup=1-3 years]";
        assertEquals(expected, toy.toString(), "toString output should match expected format");
    }
}
