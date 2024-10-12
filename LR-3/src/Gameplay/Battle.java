package Gameplay;

import Droids.Droid;

import java.util.List;
import java.io.*;

public class Battle {

    public static void oneVsOne(Droid firstDroid, Droid secondDroid) {
        firstDroid.restoreHealth();
        secondDroid.restoreHealth();

        Droid attacker = chooseFirstAttacker(firstDroid, secondDroid);
        Droid defender = (attacker == firstDroid) ? secondDroid : firstDroid;

        System.out.println("------------------------------------");
        System.out.println("Бій починається між " + firstDroid.getName() + " та " + secondDroid.getName());
        System.out.println("Першим атакує: " + attacker.getName());
        System.out.println("------------------------------------");

        // Цикл бою
        while (true) {
            attacker.attack(defender);
            System.out.println("Поточне здоров'я " + defender.getName() + " => " + defender.getHealth() + "\n");
            if(defender.getHealth() < 0)
                break;
            // Міняємо ролі атакуючого і захисника після кожної атаки
            Droid temp = attacker;
            attacker = defender;
            defender = temp;
        }
        System.out.println("------------------------------------");
        System.out.println(attacker.getName() + " переміг!");
        System.out.println("------------------------------------");
    }

    private static Droid chooseFirstAttacker(Droid firstDroid, Droid secondDroid) {
        if (firstDroid.getSpeed() > secondDroid.getSpeed()) {
            return firstDroid;
        } else if (secondDroid.getSpeed() > firstDroid.getSpeed()) {
            return secondDroid;
        } else {
            // Вибір випадкового атакуючого, якщо швидкість однакова
            return (Math.random() < 0.5) ? firstDroid : secondDroid;
        }
    }

    public static void teamVsTeam(List<Droid> team1, List<Droid> team2) {
        System.out.println("\n--- Бій команда на команду ---");

        team1.forEach(Droid::restoreHealth);
        team2.forEach(Droid::restoreHealth);

        double speedTeam1 = calculateTeamSpeed(team1);
        double speedTeam2 = calculateTeamSpeed(team2);

        System.out.println("Загальна швидкість команди 1: " + speedTeam1);
        System.out.println("Загальна швидкість команди 2: " + speedTeam2);

        boolean team1AttacksFirst = speedTeam1 >= speedTeam2;

        System.out.println("Першим атакує " + (team1AttacksFirst ? "команда 1!" : "команда 2!"));

        while (!team1.isEmpty() && !team2.isEmpty()) {
            System.out.println("\n--- Нова атака! ---");

            if (team1AttacksFirst) {
                attackRound(team1, team2, "команди 1", "команди 2");
                if (!team2.isEmpty()) {
                    attackRound(team2, team1, "команди 2", "команди 1");
                }
            } else {
                attackRound(team2, team1, "команди 2", "команди 1");
                if (!team1.isEmpty()) {
                    attackRound(team1, team2, "команди 1", "команди 2");
                }
            }

            // Показуємо загальний стан команд після кожного раунду
            printTeamHealthStatus(team1, "Команда 1");
            printTeamHealthStatus(team2, "Команда 2");
        }

        // Визначення переможця
        if (team1.isEmpty() && team2.isEmpty()) {
            System.out.println("\nНічия! Обидві команди знищені.");
        } else if (team1.isEmpty()) {
            System.out.println("\nКоманда 2 перемогла!");
        } else {
            System.out.println("\nКоманда 1 перемогла!");
        }
    }

    private static void attackRound(List<Droid> attackingTeam, List<Droid> defendingTeam, String attackingTeamName, String defendingTeamName) {
        for (Droid attacker : attackingTeam) {
            if (defendingTeam.isEmpty()) break; // Перевірка, чи є ще живі дроїди в команді, що захищається
            Droid defender = defendingTeam.get(0); // Вибираємо першого дроїда з команди, що захищається

            attacker.attack(defender); // Атакуємо захисника
            attacker.interactWithTeam(attackingTeam); // Взаємодія дроїда зі своєю командою

            if (defender.getHealth() <= 0) {
                System.out.println(defender.getName() + " з " + defendingTeamName + " знищений!");
                defendingTeam.remove(defender);
            }
        }
    }

    private static double calculateTeamSpeed(List<Droid> team) {
        return team.stream().mapToDouble(Droid::getSpeed).sum();
    }

    private static void printTeamHealthStatus(List<Droid> team, String teamName) {
        if (team.isEmpty()) {
            System.out.println(teamName + " повністю знищена!");
        } else {
            System.out.println(teamName + " - поточний стан:");
            for (Droid droid : team) {
                System.out.println(droid.getName() + ": " + droid.getHealth() + " здоров'я");
            }
        }
    }

        public static void saveBattle(List<Droid> team1, List<Droid> team2) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("battle_log.txt"))) {
                writer.write("\nКоманда 1:\n");
                for (Droid droid : team1) {
                    writer.write(droid.getName() + " - здоров'я: " + droid.getHealth() + "\n");
                }
                writer.write("\nКоманда 2:\n");
                for (Droid droid : team2) {
                    writer.write(droid.getName() + " - здоров'я: " + droid.getHealth() + "\n");
                }
                System.out.println("Бій записано у файл battle_log.txt");
            } catch (IOException e) {
                System.out.println("Помилка запису бою у файл: " + e.getMessage());
            }
        }

        public static void loadBattle() {
            try (BufferedReader reader = new BufferedReader(new FileReader("battle_log.txt"))) {
                String line;
                System.out.println("\n--- Історія бою ---");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Помилка завантаження бою з файлу: " + e.getMessage());
            }
        }
    }