public class Validator {

    static ArabicNumeral arabicNumber = new ArabicNumeral();
    static RomanNumeral romanNumber = new RomanNumeral();
    static public boolean thisIsRoman;

    // Проверка на корректный ввод
    public static void validation(String input) throws Exception {
        if (!romanNumber.isRoman(input) && !arabicNumber.isArabic(input) ||
            romanNumber.isRoman(input) && arabicNumber.isArabic(input)){
            throw new Exception("Введенные данные не верны");
        }

        if (romanNumber.isRoman(input) && !arabicNumber.isArabic(input)){
            thisIsRoman = true;
        }

        if (!romanNumber.isRoman(input) && arabicNumber.isArabic(input)){
            thisIsRoman = false;
        }
    }
}
