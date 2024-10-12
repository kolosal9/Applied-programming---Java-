package Main;

import Fibonacci.FibonacciGenerator;
import Filter.DigitFilter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int n = scanner.nextInt();
        FibonacciGenerator Nfibonacci = new FibonacciGenerator(n);
        n = Nfibonacci.getFnum();
        long[] fibonacciNumbers = Nfibonacci.generate();

        System.out.print("Enter the digit to check for: ");
        int digit = scanner.nextInt();
        DigitFilter CheckDigit = new DigitFilter(digit);
        digit = CheckDigit.getDigit();
        for(int i=0; i<n;i++){
            if(CheckDigit.endDigit(fibonacciNumbers[i])) {
                counter++;
                System.out.println(fibonacciNumbers[i]);
            }
        }
        System.out.println("The number of Fibonacci numbers ending with '" + digit + "' => " + counter);
    }
}
// 1 1 2 3 5 8 13 21 34 55 89 144