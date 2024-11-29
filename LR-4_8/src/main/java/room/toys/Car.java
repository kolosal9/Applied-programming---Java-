package room.toys;

public class Car extends Toy implements ToysActions {
    private final String model;
    private final boolean electric;

    public Car(int id, String name, double price, String ageGroup, String model, boolean electric) {
        super(id, name, price, ageGroup);
        this.model = model;
        this.electric = electric;
    }

    public String getModel() { return model; }
    public boolean isElectric() {return electric;}


    @Override
    public String getType() {
        return "car";
    }

    @Override
    public String serialize() {
        return serializeBase() + ",car," + model + "," + electric;
    }

    public static Car deserialize(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String ageGroup = parts[3];
        String model = parts[5];
        boolean electric = Boolean.parseBoolean(parts[6]);
        return new Car(id, name, price, ageGroup, model, electric);
    }

    @Override
    public String getDescription() {
        return toString() + ", Type: Car, Model: " + model + ", Electric: " + (electric ? "Yes" : "No") + ".]";
    }
}
