import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    public static int splitAndSum(String str) {
        if (strIsNullOrEmpty(str)) {
            return 0;
        }
        if (isStrLengthIsOne(str)) {
            return Integer.parseInt(str);
        }
        if (str.contains(":")) {
            return strIsSplitCommaAndColon(str);
        }
        if (str.contains(",")) {
            return strIsSplitComma(str);
        }
        if (str.contains("//")) {
            return find(str);
        }
        return -1;
    }

    private static boolean strIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private static boolean isStrLengthIsOne(String str) {
        return str.length() == 1;
    }

    private static int strIsSplitComma(String str) {
        String[] split = str.split(",");
        for (String s : split) {
            validCheck(s);
        }
        return Arrays.stream(split).mapToInt(Integer::parseInt).sum();
    }

    private static int strIsSplitCommaAndColon(String str) {
        String[] split = str.split("[,:]");
        for (String s : split) {
            validCheck(s);
        }
        return Arrays.stream(split).mapToInt(Integer::parseInt).sum();
    }

    private static int find(String str) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(str);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] tokens = m.group(2).split(customDelimiter);
            return Arrays.stream(tokens).mapToInt(Integer::parseInt).sum();
        }
        return 0;
    }

    private static void validCheck(String split) {
        if (Integer.parseInt(split) < 0) {
            throw new RuntimeException();
        }
    }
}
