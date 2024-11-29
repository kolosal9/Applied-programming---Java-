package room.toys;

public class Doll extends Toy implements ToysActions {
    private final double height;
    private final String material;

    public Doll(int id, String name, double price, String ageGroup, double height, String material) {
        super(id, name, price, ageGroup);
        this.height = height;
        this.material = material;
    }

    public double getHeight() { return height; }
    public String getMaterial() { return material; }

    @Override
    public String getType() {
        return "doll";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",doll," + height + "," + material;
    }

    public static Doll deserialize(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String ageGroup = parts[3];
        double height = Double.parseDouble(parts[5]);
        String material = parts[6];
        return new Doll(id, name, price, ageGroup, height, material);
    }

    @Override
    public String getDescription() {
        return toString() + ", Type: Doll, Height: " + height + " cm, Material: " + material + ".]";
    }
}
