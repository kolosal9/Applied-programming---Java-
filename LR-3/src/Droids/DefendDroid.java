package Droids;

public class DefendDroid extends Droid {
    private final double armor;

    public DefendDroid(String name, double health, double damage,double speed, double armor) {
        super(name, health + armor, damage, speed);
        this.armor = armor;
    }


    @Override
    public void attack(Droid target) {
        System.out.println(this.getName() + " атакує " + target.getName() + " на " + getDamage() + " урона");
        super.attack(target);
    }

    @Override
    public String toString() {
        return super.toString() + " [Броня: " + armor + "]";
    }
}
