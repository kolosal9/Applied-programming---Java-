package room;

import room.toys.Toy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private double budget;
    private final List<Toy> toys;

    public Room(double budget) {
        this.budget = budget;
        this.toys = new ArrayList<>();
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public boolean addToy(Toy toy) {
        if (getTotalCost() + toy.getPrice() <= budget) {
            toys.add(toy);
            return true;
        }
        return false;
    }

    public void removeToy(int toyId) {
        toys.removeIf(toy -> toy.getId() == toyId);
    }

    public List<Toy> searchToys(double minPrice, double maxPrice) {
        return toys.stream()
                .filter(toy -> toy.getPrice() >= minPrice && toy.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public double getTotalCost() {
        return toys.stream().mapToDouble(Toy::getPrice).sum();
    }

    public void clearToys() {
        toys.clear();
        System.out.println("All toys have been removed from the room.");
    }
}
