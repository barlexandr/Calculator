import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeral {
    private String regexRomanAlphabet = "^-?(M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})[+*/-])+";

    // Проверка является ли элемент римской цифрой через regex
    public boolean isRoman(String number) {
        Pattern romanPattern = Pattern.compile(regexRomanAlphabet);
        Matcher matcherRoman = romanPattern.matcher(number);

        if (matcherRoman.find()) {
            return true;
        } else
            return false;
    }
}
