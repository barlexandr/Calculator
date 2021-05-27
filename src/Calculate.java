import java.util.HashMap;
import java.util.Stack;

public class Calculate {
    static String result;
    static Stack <Integer> temp = new Stack<>();

    // Проверка на оператор
    private static boolean isOperator(Character input)
    {
        if (("+-/*".indexOf(input) != -1))
            return true;
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
        String reverseString = new String();
        StringBuilder romanToArabicString = new StringBuilder();
        Stack <Integer> operatorIndex = new Stack<>();

        // Если пользователь ввел числа в римской нотации, то переводим выражение в арабскую нотацию
        if(Validator.thisIsRoman){
            // Разделяем по оператору
            String[] tempRomanToArabicString = inputString.split("[+*/-]");

            // Находим индекс вхождения оператора
            for (int i = 0; i < inputString.length(); i++){
                if(isOperator(inputString.charAt(i))){
                    operatorIndex.push(i);
                }
            }

            // Переписываем входящую строку в арабской нотации
            for (int i = tempRomanToArabicString.length - 1; i > 0; i--) {
                romanToArabicString.insert(0, Converter.toArabic(tempRomanToArabicString[i]));
                Converter.toArabic(tempRomanToArabicString[i-1]);
                romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                romanToArabicString.insert(0, Converter.toArabic(tempRomanToArabicString[i-1]));
                i--;

                if (operatorIndex.size() != 0){
                    romanToArabicString.insert(0,inputString.charAt(operatorIndex.pop()));
                }
            }
            if(tempRomanToArabicString.length > 2) {
                romanToArabicString.insert(0, Converter.toArabic(tempRomanToArabicString[0]));
            }

            // Перевод в ОПН
            reverseString = ReversePolishNotation.reverseString(romanToArabicString.toString());
        }
        else {
            // Перевод в ОПН
            reverseString = ReversePolishNotation.reverseString(inputString);
        }

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
                i--;
            }

            // Если оператор, то выполняем над 2 последними элементами стэка действие
            if (isOperator(reverseString.charAt(i))) {
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
