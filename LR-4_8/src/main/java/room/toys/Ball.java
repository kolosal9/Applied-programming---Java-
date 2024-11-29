package room.toys;

public class Ball extends Toy implements ToysActions {
    private final String material;
    private final double diameter;

    public Ball(int id, String name, double price, String ageGroup, String material, double diameter) {
        super(id, name, price, ageGroup);
        this.material = material;
        this.diameter = diameter;
    }

    public double getDiameter() {return diameter;}
    public String getMaterial() {return material;}

    @Override
    public String getType() {
        return "ball";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",ball," + material + "," + diameter;
    }

    public static Ball deserialize(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String ageGroup = parts[3];
        String material = parts[5];
        double diameter = Double.parseDouble(parts[6]);
        return new Ball(id, name, price, ageGroup, material, diameter);
    }

    @Override
    public String getDescription() {
        return toString() + ", Type: Ball, Material: " + material + ", Diameter: " + diameter + " cm.]";
    }
}
