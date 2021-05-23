public class Parser {

    // Объединение строк
    public StringBuffer concatenationString (String inputOperatorLeft, String inputOperatorRight){
        StringBuffer stringBuffer = new StringBuffer(inputOperatorLeft.concat(inputOperatorRight));
        return stringBuffer;
    }
}
