import java.util.Stack;

public class ReversePolishNotation {

    // "Входной" метод класса
    public static String reverseString(String input) throws Exception {
        input.replaceAll(" ", ""); // Убираем все пробелы
        String output = getExpression(input);

        return output;
    }

    // Возвращает true, если проверяемый символ - оператор
    private static boolean isOperator(Character input)
    {
        if (("+-/*".indexOf(input) != -1))
            return true;
        return false;
    }

    // Метод, преобразующий в обратную польскую запись
    private static String getExpression(String input) throws Exception {
        String output = new String(); // Строка для хранения выражения
        Stack<Character> operatorStack = new Stack<>();
//        int countOperator = 0;

        for (int i = 0; i < input.length(); i++) // Для каждого символа в входной строке
        {
            // Если символ - цифра, то считываем
            if (Character.isDigit(input.charAt(i))) // Если разрешенная цифра
            {
                // Читаем до оператора, что бы получить число
                while (!isOperator(input.charAt(i)))
                {
                    output += input.charAt(i); // Добавляем цифру числа к нашей строке
                    i++; // Переходим к следующему символу

                    if (i == input.length())
                        break; // Если символ - последний, то выходим из цикла
                }
                output += " ";
                i--; // Возвращаемся на один символ назад
            }

            // Если символ - оператор
            if (isOperator(input.charAt(i)))
            {
                if (isOperator(input.charAt(i-1))){
                    throw new Exception("Введен двойной оператор");
                }
//                countOperator++;
                if (!operatorStack.empty()) // Если в стеке есть элементы
                    output += operatorStack.pop() + " "; // Добавляем последний оператор из стека в строку с выражением

//                // Проверка на ввод только 1 оператора
//                if (countOperator > 1){
//                    throw new Exception("Вы ввели больше 1 оператора");
//                }

                operatorStack.push(input.charAt(i));// Если стек пуст - добавляем операторов на вершину стека
            }
        }

        //Когда прошли по всем символам, выкидываем из стека все оставшиеся там операторы в строку
        while (!operatorStack.empty())
            output += operatorStack.pop() + " ";

        return output; // Возвращаем выражение в постфиксной записи
    }
}
