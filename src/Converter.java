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
        for (int i = 0; i < inputOperand.length(); i++) {
            arrayKeyOfValue[i] = searchOfKey(Character.toString(inputOperand.charAt(i)));
        }

        // Проходим по массиву значений и вносим результат
        for (int i = 0; i < arrayKeyOfValue.length-1; i++){

            //Если левое число меньше правого, то результат = правое - левое
            if (arrayKeyOfValue[i] < arrayKeyOfValue[i+1]){
                resultOfConverse += arrayKeyOfValue[i+1] - arrayKeyOfValue[i];
                i++;
            }

            // Иначе плюсуем к результату текущее число
            else {
                resultOfConverse += arrayKeyOfValue[i];
            }
        }

        // Если 2 последних символа одинаковые, то добавляем римскую интерпритацию символов к ответу
        // ***так как массив по размеру массива -1
        if(arrayKeyOfValue.length >= 2) {
            if (arrayKeyOfValue[arrayKeyOfValue.length - 1] == arrayKeyOfValue[arrayKeyOfValue.length - 2]) {
                resultOfConverse += arrayKeyOfValue[arrayKeyOfValue.length - 1];
            }
        }

        if(arrayKeyOfValue.length == 1){
            resultOfConverse += arrayKeyOfValue[0];
        }

        return Integer.toString(resultOfConverse);
    }
}
