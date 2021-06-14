import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("""
                Вы зашли в калькулятор римских и арабских чисел.
                Калькулятор умеет выполнять следующие действия: + - * /
                Выражение вводите в 1 строчку без знака равенства. При записи можно использовать скобки.
                Калькулятор выполняет действия только с целыми числами.
                Записывать выражение можно только в 1 из нотаций: римской или арабской.
                Чтобы выйти из приложения введите exit.
                Введите Ваше выражение:""");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();

            if (inputString.equals("exit"))
                break;

            Validator.validation(inputString);
            Calculate.Computation(inputString);

            System.out.println("Результат : " + Calculate.Computation(inputString));
        }
    }
}
