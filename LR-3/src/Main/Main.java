package Main;

import Droids.Droid;
import Droids.AttackDroid;
import Droids.DefendDroid;
import Droids.HealDroid;
import Gameplay.Battle;
import Gameplay.Creating;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Droid> availableDroids = new ArrayList<Droid>() {{
            add(new AttackDroid("Razor Fang", 950, 40, 18, "Plasma Claw", 28)); // Сильна атака ближнього бою, слабка броня
            add(new DefendDroid("Bulwark Sentinel", 1450, 16, 55, 400)); // Надзвичайно витривалий, низька швидкість
            add(new HealDroid("Bio-Regen", 900, 9, 12, 35)); // Спеціалізується на довготривалому відновленні
            add(new AttackDroid("Shockwave", 1100, 28, 30, "Electro Cannon", 22)); // Баланс атаки і броні, середня швидкість
            add(new DefendDroid("Aegis Vanguard", 1300, 24, 48, 500)); // Дуже потужна броня, підходить для захисту команди
            add(new HealDroid("Synth Healer", 950, 11, 18, 20)); // Може швидко лікувати союзників, висока витривалість
            add(new AttackDroid("Inferno Blaze", 1025, 36, 25, "Flame Thrower", 20)); // Потужний вогнемет, середня швидкість
            add(new DefendDroid("Titanium Wall", 1500, 14, 60, 380)); // Максимальна броня і витривалість, але дуже повільний
            add(new HealDroid("Nano Surge", 975, 10, 15, 15)); // Швидка регенерація, середня витривалість
            add(new AttackDroid("Cyclone Fury", 1075, 33, 45, "Wind Blade", 26)); // Висока швидкість і атака, низька броня
        }};


        List<Droid> team1 = null;
        List<Droid> team2 = null;
        Creating creating = new Creating(availableDroids);

        while (true) {
            System.out.println("\n1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Команда на команду");
            System.out.println("5. Записати бій у файл");
            System.out.println("6. Відтворити бій із файлу");
            System.out.println("7. Вийти");

            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Droid newDroid = Creating.createNewDroid(scanner); // Створення нового дроїда
                    if (newDroid != null) {
                        availableDroids.add(newDroid);
                        System.out.println("Дроїд створений і доданий до списку.");
                    }
                    break;
                case 2:
                    Creating.showDroids(availableDroids); // Показує список дроїдів
                    break;
                case 3: // Бій 1 на 1
                    team1 = creating.createTeam(1); // Створення першої команди з 1 дроїда
                    team2 = creating.createTeam(1); // Створення другої команди з 1 дроїда
                    Battle.oneVsOne(team1.get(0), team2.get(0)); // Бій 1 на 1
                    break;
                case 4: // Бій команда на команду
                    System.out.print("\nВведіть кількість дроїдів у команді (макс 3): \n");
                    int teamSize = Math.min(scanner.nextInt(), 3);
                    System.out.println("Створення команди №1. Виберіть дроїдів із наступного списку:");
                    team1 = creating.createTeam(teamSize); // Перша команда
                    System.out.println("Створення команди №2. Виберіть дроїдів із наступного списку:");
                    team2 = creating.createTeam(teamSize); // Друга команда
                    Battle.teamVsTeam(team1, team2); // Бій команда на команду
                    break;
                case 5:
                    if (team1 != null) {
                        Battle.saveBattle(team1, team2); // Записуємо бій у файл
                    } else {
                        System.out.println("Команди не створено. Спочатку проведіть бій.");
                    }
                    break;
                case 6:
                    Battle.loadBattle(); // Відтворюємо бій із файлу
                    break;
                case 7:
                    System.out.println("Вихід...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }
}
