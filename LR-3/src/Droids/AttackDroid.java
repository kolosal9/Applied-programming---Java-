package Droids;

public class AttackDroid extends Droid {
    private final String gunName;
    private final double gunDamage;

    public AttackDroid(String name, double health, double damage,double speed, String gunName, double gunDamage) {
        super(name, health, damage + gunDamage, speed);
        this.gunName = gunName;
        this.gunDamage = gunDamage;
    }

    public double getGunDamage() {
        return gunDamage;
    }

    @Override
    public void attack(Droid target) {
        System.out.println(this.getName() + " атакує " + target.getName() + " за допомогою " + gunName + " на " + getDamage() + " урона");
        super.attack(target);
    }
}

