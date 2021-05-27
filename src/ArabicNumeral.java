import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabicNumeral {
    private final String regexArabicAlphabet = "^[\\d+*/()-]+$" ;
    // "\\(|\\)*^(\\d{1,4}\\(|\\)*[+*/-]\\(|\\)*\\d{1,4}\\(|\\)*)+"

    // Проверка является ли число арабским
    public boolean isArabic(String number){
        Pattern arabicPattern = Pattern.compile(regexArabicAlphabet);
        Matcher matcherArabic = arabicPattern.matcher(number);

        int countOpenHooks = number.split("\\(", -1).length-1;
        int countCloseHooks = number.split("\\)",-1).length-1;

        if (matcherArabic.find() && countOpenHooks == countCloseHooks) {
            return true;
        } else
            return false;
    }

}