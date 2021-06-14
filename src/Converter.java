import java.util.*;

public class Converter {
    // Карта арабских и римских значений
    private static final TreeMap<Integer, String> romanSymbols = new TreeMap<>()
    {{
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
        put(40, "XL");
        put(50, "L");
        put(90, "XC");
        put(100, "C");
        put(500, "D");
        put(1000, "M");
    }};

    // Поиск ключа по значению
    private static int searchOfKey(String str) throws Exception {
        Set<Map.Entry<Integer, String>> entrySet = romanSymbols.entrySet();

        // Находим наше значение и возвращаем  ключ
        for (Map.Entry<Integer,String> pair : entrySet) {
            if (str.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        throw new Exception("Невозможно найти ключ");
    }

    // Проверка на оператор
    private static boolean isOperator(Character input)
    {
        return "()+-/*".indexOf(input) != -1;
    }

    // Перевод из арабских цифр в римские
    public static String toRomain (int number) throws Exception {
        if (number < 1 || number > 3999){
            throw new Exception("Ответ невозможно представить в римском исчислении");
        }

        // Получаем наибольший ключ, который ближе всего к переданному числу
        int closeToMeaningKey =  romanSymbols.floorKey(number);

        // Если полученное число равно вычисленному ключу, то возвращаем это число
        if (number == closeToMeaningKey) {
            return romanSymbols.get(number);
        }

        // Возвращаем строку из ключей, содержащихся в мапе и эквивалентных римским обозначениям
        return romanSymbols.get(closeToMeaningKey) + toRomain(number-closeToMeaningKey);
    }

    // Перевод из римских цифр в арабские
    public static String toArabic (String inputOperand) throws Exception {
        int resultOfConverse = 0;

        // Проверяем по каждому элементу
        // Если элемент первый или больше предыдущего, то добавляем
        // Если следующий элемент больше, то добавляем больший и отнимаем меньший *2
        for (int i = 0; i < inputOperand.length(); i ++){
            if (i == 0 || searchOfKey(Character.toString(inputOperand.charAt(i))) <= searchOfKey(Character.toString(inputOperand.charAt(i-1)))){
                resultOfConverse += searchOfKey(Character.toString(inputOperand.charAt(i)));
            }
            else{
                resultOfConverse += searchOfKey(Character.toString(inputOperand.charAt(i))) - 2*searchOfKey(Character.toString(inputOperand.charAt(i-1)));
            }
        }

        return Integer.toString(resultOfConverse);
    }

    // Перевод в ОПН
    public static String ConverterToRPN(String inputString) throws Exception {
        String reverseString;
        StringBuilder romanToArabicString = new StringBuilder();
        Stack<Integer> operatorIndex = new Stack<>();

        // Если пользователь ввел числа в римской нотации, то переводим выражение в арабскую нотацию
        if (Validator.thisIsRoman) {
            // Разделяем по оператору
            String[] tempRomanToArabicString = inputString.split("[()+*/-]");

            // Создаем лист, чтобы удалить из него пустые элементы
            List<String> tempRomanToArabicList = new ArrayList<>(Arrays.asList(tempRomanToArabicString));

            while (tempRomanToArabicList.contains("")) {
                tempRomanToArabicList.remove("");
            }

            // Находим индекс вхождения оператора
            for (int i = 0; i < inputString.length(); i++) {
                if (isOperator(inputString.charAt(i))) {
                    operatorIndex.push(i);
                }
            }

            // Переписываем входящую строку в арабской нотации
            for (int i = tempRomanToArabicList.size() - 1; i > 0; i--) {
                // Если верхний символ стека - скобка, то вытаскиваем ее в строку,
                // которая будет конвертирована в ОПН
                while (inputString.charAt(operatorIndex.peek()) == '('||
                        inputString.charAt(operatorIndex.peek()) == ')'||
                        inputString.charAt(operatorIndex.peek()+1) == '('||
                        inputString.charAt(operatorIndex.peek()+1) == ')'){
                    romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                }

                // Заносим операнды и операторы в строку, которая будет конвертирована в ОПН
                romanToArabicString.insert(0, Converter.toArabic(String.valueOf(tempRomanToArabicList.get(i))));
                romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));

                if (!operatorIndex.empty()) {
                    if (inputString.charAt(operatorIndex.peek()) == '(' && !isOperator(romanToArabicString.charAt(0)) ||
                            inputString.charAt(operatorIndex.peek()) == ')') {
                        romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                    }
                }

                romanToArabicString.insert(0, Converter.toArabic(String.valueOf(tempRomanToArabicList.get(i-1))));
                i--;

                // Если не последний операнд, то заносим в строку, подлежащую конвертации в ОПН
                if (operatorIndex.size() != 0) {
                    romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                }
            }

            // Если размер листа с операндами больше 2,
            // то в предыдущем цикле последний операнд не был обработан.
            // Записываем последний операнд в строку.
            if(!operatorIndex.empty()) {
                if (tempRomanToArabicList.size() > 2) {
                    // И последний записанный оператор - скобка,
                    // то записываем в строку следующий оператор
                    if (operatorIndex.size() > 1) {
                        while (inputString.charAt(operatorIndex.peek() + 1) == '(' ||
                                inputString.charAt(operatorIndex.peek() + 1) == ')') {
                            romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                        }
                    } else
                        romanToArabicString.insert(0, inputString.charAt(operatorIndex.pop()));
                    romanToArabicString.insert(0, Converter.toArabic(tempRomanToArabicList.get(0)));
                }
            }

            // Перевод в ОПН
            reverseString = ReversePolishNotation.reverseString(romanToArabicString.toString());
        } else {
            // Перевод в ОПН
            reverseString = ReversePolishNotation.reverseString(inputString);
        }
        return reverseString;
    }
}
