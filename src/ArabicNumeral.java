import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabicNumeral {

    // Проверка является ли число арабским
    public boolean isArabic(String number){
        String regexArabicAlphabet = "^[\\d+*/()-]+$";

        Pattern arabicPattern = Pattern.compile(regexArabicAlphabet);
        Matcher matcherArabic = arabicPattern.matcher(number);

        int countOpenHooks = number.split("\\(", -1).length-1;
        int countCloseHooks = number.split("\\)",-1).length-1;

        return matcherArabic.find() && countOpenHooks == countCloseHooks;
    }

}