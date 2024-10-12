package Droids;

import java.util.List;

public class Droid {
    private final String name;
    private double health;
    private final double damage;
    private final double speed;
    private double initialHealth;

    public Droid(String name, double health, double damage, double speed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        initialHealth = health;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void restoreHealth() {
        this.health = initialHealth;
    }

    public void attack(Droid target) {
        target.setHealth(target.getHealth() - this.damage);
    }
    public void interactWithTeam(List<Droid> teammates){}

    @Override
    public String toString() {
        return name + " (Здоров'я: " + health + ", Урон: " + damage + ", Швидкість: " + speed +")";
    }
}