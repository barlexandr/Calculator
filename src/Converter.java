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
        put(400, "CD");
        put(500, "D");
        put(900, "CM");
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

        // Если пользователь ввел числа в римской нотации, то переводим выражение в арабскую нотацию
        if (Validator.thisIsRoman) {

            // Разделяем по оператору
            String[] tempRomanToArabicString = inputString.split("[()+*/-]");

            // Создаем лист, чтобы удалить из него пустые элементы
            List<String> tempRomanToArabicList = new ArrayList<>(Arrays.asList(tempRomanToArabicString));

            while (tempRomanToArabicList.contains("")) {
                tempRomanToArabicList.remove("");
            }
            
            // Пока входящая строка не пустая
            while ((inputString.length() > 0) ) {

                // Если временный лист с операндами не пустой
                if (!tempRomanToArabicList.isEmpty()) {
                    
                    //Берем 0 значение 
                    String sy = String.valueOf(tempRomanToArabicList.get(0));

                    // Если входящая строка начинается с этого значения, то удаляем из временного листа значение,
                    // конвертируем его в арабскую систему счисления, записываем в строку, содержащую конвертированные символы
                    // и обрезаем входящую строку на длину этого значения
                    if (inputString.startsWith(sy)) {
                        sy = String.valueOf(tempRomanToArabicList.remove(0));
                        romanToArabicString.append(Converter.toArabic(sy));
                        inputString = inputString.substring(sy.length());
                    }
                }

                // Если входящая строка начинается с оператора
                if (inputString.startsWith("(") ||
                            inputString.startsWith(")") ||
                            inputString.startsWith("*") ||
                            inputString.startsWith("/") ||
                            inputString.startsWith("-") ||
                            inputString.startsWith("+")) {
                    
                    // Добавляем оператор в строку, содержащую конвертированные символы
                    // и обрезаем входящую строку на 1 символ
                    romanToArabicString.append(inputString.charAt(0));
                    inputString = inputString.substring(1);
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
