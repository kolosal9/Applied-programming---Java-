import java.util.Scanner;

public class FibonacciGenerator {
    private int n;

    public FibonacciGenerator(int n){
        setFnum(n);
    }

    public void setFnum(int n) {
        if (n < 0) {
            System.out.println("Number of Fibonacci numbers cannot be negative.");
            this.n = getValidNumberFromUser(); // Prompt user for a valid number
        } else
            this.n = n;
    }

    public int getFnum()  {
        return n;
    }

    private int getValidNumberFromUser() {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print("Enter a non-negative number for the Fibonacci sequence: ");
            number = scanner.nextInt();
            if (number >= 0)
                break; // Exit loop if number is valid
            else
                System.out.println("Invalid input. The number must be non-negative.");
        }
        return number;
    }

    public long[] generate(){
        long[] fibNumbs = new long[n];

        if(n>0) fibNumbs[0] = 1;
        if(n>1) fibNumbs[1] = 1;

        for(int i=2;i<n;i++) {
            fibNumbs[i] = fibNumbs[i - 1] + fibNumbs[i - 2];
        }
        return fibNumbs;
    }
}