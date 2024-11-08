package Droids;

import java.util.List;

public class HealDroid extends Droid {
    private final double healPerSecond;

    public HealDroid(String name, double health, double damage,double speed, double healPerSecond) {
        super(name, health, damage,speed);
        this.healPerSecond = healPerSecond;
    }

    @Override
    public void attack(Droid target) {
        System.out.println(this.getName() + " атакує на "  + getDamage() + " урона та зцілює себе на " + healPerSecond + " здоров'я.");
        this.setHealth(this.getHealth() + healPerSecond);
        super.attack(target);
    }

    @Override
    public void interactWithTeam(List<Droid> teammates) {
        System.out.println(this.getName() + " зцілює свою команду на " + healPerSecond + " здоров'я.");
        for (Droid teammate : teammates) {
            teammate.setHealth(teammate.getHealth() + healPerSecond);
        }
    }
    @Override
    public String toString() {
        return super.toString() + " [Зцілює: " + healPerSecond + " HP/сек]";
    }
}