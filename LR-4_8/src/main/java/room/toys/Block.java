package room.toys;

public class Block extends Toy implements ToysActions {
    private final String shape;
    private final String color;

    public Block(int id, String name, double price, String ageGroup, String shape, String color) {
        super(id, name, price, ageGroup);
        this.shape = shape;
        this.color = color;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }

    @Override
    public String getType() {
        return "block";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",block," + shape + "," + color;
    }

    public static Block deserialize(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String ageGroup = parts[3];
        String shape = parts[5];
        String color = parts[6];
        return new Block(id, name, price, ageGroup, shape, color);
    }

    @Override
    public String getDescription() {
        return toString() + ", Type: Block, Shape: " + shape + ", Color: " + color + ".]";
    }
}
