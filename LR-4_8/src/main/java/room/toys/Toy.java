package room.toys;

public abstract class Toy implements ToysActions {
    private final int id;
    private final String name;
    private final double price;
    private final String ageGroup;

    public Toy(int id, String name, double price, String ageGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ageGroup = ageGroup;
    }

    public abstract String serialize();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    protected String serializeBase() {
        return id + "," + name + "," + price + "," + ageGroup;
    }

    public static String[] deserialize(String line) {
        return line.split(",");
    }

    @Override
    public String toString() {
        return "Toy [ID=" + id + ", Name=" + name + ", Price=" + price + ", AgeGroup=" + ageGroup + "]";
    }
}

