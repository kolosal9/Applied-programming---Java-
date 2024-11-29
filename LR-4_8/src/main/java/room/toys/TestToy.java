package room.toys;

public class TestToy extends Toy {
    public TestToy(int id, String name, double price, String ageGroup) {
        super(id, name, price, ageGroup);
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getType() {
        return "TestType";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",TestType";
    }
}
