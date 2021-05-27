import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Converter {
    // Карта арабских и римских значений
    private static TreeMap<Integer, String> romanSymbols = new TreeMap<>()
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

        // Создаем и заполняем массив значениями ключей римских написаний цифр
        int[] arrayKeyOfValue = new int[inputOperand.length()];

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
}
