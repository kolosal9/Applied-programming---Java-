package room.toys;

public class Animal extends Toy implements ToysActions {
    private final String species;
    private final String material;

    public Animal(int id, String name, double price, String ageGroup, String species, String material) {
        super(id, name, price, ageGroup);
        this.species = species;
        this.material = material;
    }

    public String getSpecies() { return species; }
    public String getMaterial() { return material; }

    @Override
    public String getType() {
        return "animal";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",animal," + species + "," + material;
    }

    public static Animal deserialize(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String ageGroup = parts[3];
        String species = parts[5];
        String material = parts[6];
        return new Animal(id, name, price, ageGroup, species, material);
    }

    @Override
    public String getDescription() {
        return toString() + ", Type: Animal, Species: " + species + ", Material: " + material + ".]";
    }
}
