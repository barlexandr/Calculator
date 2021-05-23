import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabicNumeral {
    private String regexArabicAlphabet = "^([0-9]|10)[+*/-]([0-9]|10)$";

    // Проверка является ли число арабским
    public boolean isArabic(String number){
        Pattern arabicPattern = Pattern.compile(regexArabicAlphabet);
        Matcher matcherArabic = arabicPattern.matcher(number);

        if (matcherArabic.find()){
            return true;
        } else
            return false;
    }

}