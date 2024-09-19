import java.util.Scanner;

public class DigitFilter {
    private int digit;

    public DigitFilter(int n){
        setDigit(n);
    }

    public void setDigit(int dig) {
        if (dig < 0 || dig >9) {
            System.out.println("Digit must be between 0 and 9!");
            this.digit = getValidNumberFromUser(); // Prompt user for a valid number
        } else
            this.digit = dig;
    }
    public int getDigit(){
        return digit;
    }

    private int getValidNumberFromUser() {
        Scanner scanner = new Scanner(System.in);
        int digit;
        while (true) {
            System.out.print("Enter a number between 0 and 9: ");
            digit = scanner.nextInt();
            if (digit >= 0 && digit <=9)
                break; // Exit loop if number is valid
            else
                System.out.println("Invalid input. Digit must be between 0 and 9");
        }
        return digit;
    }

    public boolean endDigit(long fnum){
        return fnum % 10 == this.digit;
    }
}