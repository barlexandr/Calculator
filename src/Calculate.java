import java.util.Stack;

public class Calculate {
    static String result;
    static Stack <Integer> temp = new Stack<>();
    static int countOfOperator = 0;
    static int countOfElement = 0;

    // Проверка на оператор
    private static boolean isOperator(Character input)
    {
        if (("+-/*()".indexOf(input) != -1)) {
            return true;
        }
        return false;
    }

    // Проверка на разделитель
    private static boolean isDelimiter(Character input){
        if ((" ".indexOf(input)) != -1)
            return true;
        return false;
    }

    // Вычисление
    public static String Computation(String inputString) throws Exception {
        String reverseString = Converter.ConverterToRPN(inputString);

        // Проверяем каждый символ в строке
        for (int i = 0; i < reverseString.length(); i++) {
            // Если символ - цифра, то читаем число пока не разделитель или оператор и записываем в стек
            if (Character.isDigit(reverseString.charAt(i))) {
                String tempString = "";

                while (!isOperator(reverseString.charAt(i)) && !isDelimiter(reverseString.charAt(i))) {
                    tempString += reverseString.charAt(i);
                    i++;
                    if (i == reverseString.length())
                        break;
                }
                temp.push(Integer.parseInt(tempString));
                countOfElement++;
                i--;
            }

            // Если оператор, то выполняем над 2 последними элементами стэка действие
            if (isOperator(reverseString.charAt(i))) {

                if ("+-*/".indexOf(reverseString.charAt(i)) != -1) {
                    countOfOperator++;
                }
                if (countOfOperator >= countOfElement)
                    throw new Exception("Введено слишком много операторов");

                int a = temp.pop();
                int b = temp.pop();

                switch (reverseString.charAt(i)) {
                    case '+':
                        result = String.valueOf(b + a);
                        break;
                    case '-':
                        result = String.valueOf(b - a);
                        break;
                    case '*':
                        result = String.valueOf(b * a);
                        break;
                    case '/':
                        result = String.valueOf(b / a);
                        break;
                }
                temp.push(Integer.parseInt(result));
            }
        }

        // Если вводили в романской нотации, то результат выводим в ней же
        if (Validator.thisIsRoman){
            result = Converter.toRomain(temp.peek());
        }
        return result;
    }
}
