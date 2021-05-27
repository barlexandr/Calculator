import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Вы зашли в калькулятор римских и арабских чисел.\n" +
                "Калькулятор умеет выполнять следующие действия: + - * /\n" +
                "Выражение вводите в 1 строчку без знака равенства. При записи можно использовать скобки.\n" +
                "Калькулятор выполняет действия только с целыми числами.\n" +
                "Записывать выражение можно только в 1 из нотаций: римской или арабской.\n" +
                "Введите Ваше выражение:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        Validator.validation(inputString);
        Calculate.Computation(inputString);

        System.out.println("Результат : " + Calculate.Computation(inputString));
    }
}
