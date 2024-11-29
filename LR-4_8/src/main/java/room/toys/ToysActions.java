package room.toys;

public interface ToysActions {
    int getId();

    String getName();

    double getPrice();

    String getAgeGroup();

    String getDescription();

    String getType();

    String serialize();

    static Toy deserialize(String[] parts) {
        throw new UnsupportedOperationException("This method should be implemented in subclasses.");
    }
}