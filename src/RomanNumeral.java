import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeral {
    private String regexRomanAlphabet = "^(X|IX|V|IV?|I{0,3})[+*/-](X|IX|V|IV?|I{0,3})$";

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
