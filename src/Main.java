import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        Validator.validation(inputString);
        Calculate.Computation(inputString);

        System.out.println("Результат : " + Calculate.Computation(inputString));
    }
}
