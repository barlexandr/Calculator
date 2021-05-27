import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeral {
    private final String regexRomanAlphabet = "^((M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})){1,5}[+*/()-](M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})){1,5})+$";

    // Проверка является ли элемент римской цифрой через regex
    public boolean isRoman(String number) {
        Pattern romanPattern = Pattern.compile(regexRomanAlphabet);
        Matcher matcherRoman = romanPattern.matcher(number);

        int countOpenHooks = number.split("\\(", -1).length-1;
        int countCloseHooks = number.split("\\)",-1).length-1;

        if (matcherRoman.find() && countOpenHooks == countCloseHooks) {
            return true;
        } else
            return false;
    }
}
