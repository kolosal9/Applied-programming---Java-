package Gameplay;

import Droids.AttackDroid;
import Droids.DefendDroid;
import Droids.Droid;
import Droids.HealDroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Creating {
    private final List<Droid> availableDroids;

    public Creating(List<Droid> availableDroids) {
        this.availableDroids = availableDroids;
    }

    public List<Droid> createTeam(int teamSize) {
        Scanner scanner = new Scanner(System.in);
        List<Droid> team = new ArrayList<>();

        for (int i = 0; i < availableDroids.size(); i++) {
            System.out.println((i + 1) + ". " + availableDroids.get(i));
        }

        for (int i = 0; i < teamSize; i++) {
            System.out.println();
            System.out.println("Виберіть дроїда №" + (i + 1) + " (введіть номер дроїда): ");
            int droidIndex = scanner.nextInt() - 1;

            if (droidIndex >= 0 && droidIndex < availableDroids.size()) {
                team.add(availableDroids.get(droidIndex));
            } else {
                System.out.println("Невірний номер, спробуйте ще раз.");
                i--;
            }
        }

        System.out.println("\nКоманда створена:");
        for (Droid droid : team) {
            System.out.println(droid);
        }
        System.out.println();
        return team;
    }

    // Метод для створення нового дроїда
    public static Droid createNewDroid(Scanner scanner) {
        System.out.print("Виберіть тип дроїда (1 - Атакуючий, 2 - Захисний, 3 - Лікувальний): ");
        int type = scanner.nextInt();
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.next();
        System.out.print("Введіть здоров'я: ");
        double health = scanner.nextDouble();
        System.out.print("Введіть атаку: ");
        double damage = scanner.nextDouble();
        System.out.print("Введіть швидкість: ");  // новий параметр для швидкості
        double speed = scanner.nextDouble();

        switch (type) {
            case 1:
                System.out.print("Введіть зброю: ");
                String weapon = scanner.next();
                System.out.print("Введіть силу зброї: ");
                double weaponDamage = scanner.nextDouble();
                return new AttackDroid(name, health, damage, speed, weapon, weaponDamage);
            case 2:
                System.out.print("Введіть броню: ");
                double armor = scanner.nextDouble();
                return new DefendDroid(name, health, damage, speed, armor);
            case 3:
                System.out.print("Введіть силу лікування: ");
                double healPerSecond = scanner.nextDouble();
                return new HealDroid(name, health, damage, speed, healPerSecond);
            default:
                System.out.println("Невірний тип дроїда. Дроїд не створено.");
                return null;
        }
    }

    public static void showDroids(List<Droid> availableDroids) {
        System.out.println("\n--- Список дроїдів ---");
        for (int i = 0; i < availableDroids.size(); i++) {
            Droid droid = availableDroids.get(i);
            System.out.println((i + 1) + ". " + droid.getName() + " - Здоров'я: " + droid.getHealth() + ", Атака: " + droid.getDamage() +", Швидкість: " + droid.getSpeed());
        }
    }
}