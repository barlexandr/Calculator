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
        if (("+-/*()".indexOf(input) != -1))
            return true;
        return false;
    }

    //Метод возвращает приоритет оператора
    private static byte GetPriority(char operator)
    {
        switch (operator)
        {
            case '(': return 0;
            case ')': return 1;
            case '-': return 2;
            case '+': return 3;
            case '*': return 4;
            case '/': return 4;
            default: return 5;
        }
    }

    // Метод, преобразующий в обратную польскую запись
    private static String getExpression(String input) {
        String output = new String(); // Строка для хранения выражения
        Stack<Character> operatorStack = new Stack<>();

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

            // Если символ оператор
            if (isOperator(input.charAt(i))) {
                //Если оператор открывающая скобка, то записываем ее в стек
                if (input.charAt(i) == '(')
                    operatorStack.push(input.charAt(i));

                //Если оператор закрывающая скобка, то выписываем все операторы до открывающей скобки
                else if (input.charAt(i) == ')')
                {
                    char operator = operatorStack.pop();

                    while (operator != '(') {
                        output += String.valueOf(operator) + " ";
                        operator = operatorStack.pop();
                    }
                }

                // Если любой другой оператор
                else {
                    // Если в стеке есть элементы
                    if (!operatorStack.empty()) {
                        //И если приоритет нашего оператора меньше или равен приоритету оператора на вершине стека,
                        // то добавляем последний оператор из стека в строку
                        if (GetPriority(input.charAt(i)) <= GetPriority(operatorStack.peek())) {
                            output += operatorStack.pop() + " ";
                        }
                    }
                    // Если стек пуст - добавляем операторов на вершину стека
                    operatorStack.push(input.charAt(i));
                }
            }
        }

        //Когда прошли по всем символам, выкидываем из стека все оставшиеся там операторы в строку
        while (!operatorStack.empty())
            output += operatorStack.pop() + " ";

        return output; // Возвращаем выражение в постфиксной записи
    }
}
