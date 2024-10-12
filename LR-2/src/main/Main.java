package main;

import abiturient.Abiturient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Створюємо масив абітурієнтів
        List<Abiturient> abiturients = createAbiturientArray();

        Scanner scanner = new Scanner(System.in);

       // Викликаємо метод для виведення абітурієнтів із заданим ім'ям
        System.out.print("Введіть ім'я для пошуку: ");
        String searchName = scanner.nextLine();
        List<Abiturient> filteredByName = filterAbiturientsByName(abiturients, searchName);
        if(filteredByName.isEmpty())
            System.out.print("Не знайдено абітурієнта з заданим іменем" + '\n');
        else {
            System.out.println("Список абітурієнтів з ім'ям " + searchName + ":");
            printAbiturients(filteredByName);
        }

        // Викликаємо метод для виведення абітурієнтів, середній бал яких вище заданого
        System.out.print("\nВведіть середній бал для фільтрації: ");
        double minMark = scanner.nextDouble();
        List<Abiturient> filteredByMark = filterAbiturientsByMark(abiturients, minMark);
        if(filteredByMark.isEmpty())
            System.out.print("Не знайдено абітурієнта з середнім балом вище чим заданий" + '\n');
        else {
            System.out.println("Список абітурієнтів з середнім балом вище " + minMark + ":");
            printAbiturients(filteredByMark);
        }


        // Викликаємо метод для вибору абітурієнтів з найвищим середнім балом
        System.out.print("\nВведіть кількість абітурієнтів для відбору з найвищим середнім балом: ");
        int topN = scanner.nextInt();
        List<Abiturient> topAbiturients = getTopAbiturientsByMark(abiturients, topN);
        if(topAbiturients.isEmpty())
            System.out.print("Некоректно задана кількість абітурієнтів для пошуку" + '\n');
        else {
            System.out.println("Топ " + topN + " абітурієнтів з найвищим середнім балом:");
            printAbiturients(topAbiturients);
        }
    }

    // Метод для створення масиву абітурієнтів
    public static List<Abiturient> createAbiturientArray() {
        List<Abiturient> abiturients = new ArrayList<>();
        abiturients.add(new Abiturient(1, "Крайній", "Данило", "Олександрович", "Львів", "0931111115596", 99.9));
        abiturients.add(new Abiturient(2, "Улитько", "Максим", "Васильович", "Полтава", "095672201", 50.1));
        abiturients.add(new Abiturient(1, "Крайній", "Данило", "Олександрович", "Львів", "0664312111", 99.9));
        abiturients.add(new Abiturient(2, "Улитько", "Максим", "Васильович", "Полтава", "093531242", 50.1));
        abiturients.add(new Abiturient(3, "Побережець", "Олександр", "Олександрович", "Умань", "0634512231", 78.3));
        abiturients.add(new Abiturient(4, "Верес", "Олексій", "Іванович", "Львів", "093908912", 92.4));
        return abiturients;
    }

    // Метод для фільтрації абітурієнтів за ім'ям
    public static List<Abiturient> filterAbiturientsByName(List<Abiturient> abiturients, String name) {
        return abiturients.stream()
                .filter(ab -> ab.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    //The same with loop, but API make my lab work easier
    /*public static List<Abiturient> filterAbiturientsByName(List<Abiturient> abiturients, String name) {
        List<Abiturient> result = new ArrayList<>();
        for (Abiturient ab : abiturients) {
            if (ab.getFirstName().equalsIgnoreCase(name)) {
                result.add(ab);
            }
        }
        return result;
    }*/

    // Метод для фільтрації абітурієнтів за середнім балом
    public static List<Abiturient> filterAbiturientsByMark(List<Abiturient> abiturients, double minMark) {
        return abiturients.stream()
                .filter(ab -> ab.getAverageMark() > minMark)
                .collect(Collectors.toList());
    }

    // Метод для отримання топ n абітурієнтів за середнім балом
    public static List<Abiturient> getTopAbiturientsByMark(List<Abiturient> abiturients, int n) {
        return abiturients.stream()
                .sorted(Comparator.comparingDouble(Abiturient::getAverageMark).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // Метод для виведення списку абітурієнтів на екран
    public static void printAbiturients(List<Abiturient> abiturients) {
        abiturients.forEach(System.out::println);
    }
}
