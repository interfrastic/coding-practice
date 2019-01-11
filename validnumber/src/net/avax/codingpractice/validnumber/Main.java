package net.avax.codingpractice.validnumber;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")

public class Main {
    public static void main(String[] args) {
        Map<String, Boolean> expecteResultForInput = new HashMap<>();

        expecteResultForInput.put("0", true);
        expecteResultForInput.put(" 0.1 ", true);
        expecteResultForInput.put("abc", false);
        expecteResultForInput.put("1 a", false);
        expecteResultForInput.put("2e10", true);
        expecteResultForInput.put(" -90e3   ", true);
        expecteResultForInput.put(" 1e", false);
        expecteResultForInput.put("e3", false);
        expecteResultForInput.put(" 6e-1", true);
        expecteResultForInput.put(" 99e2.5 ", false);
        expecteResultForInput.put("53.5e93", true);
        expecteResultForInput.put(" --6 ", false);
        expecteResultForInput.put("-+3", false);
        expecteResultForInput.put("95a54e53", false);
        expecteResultForInput.put(".", false);
        expecteResultForInput.put("1 ", true);
        expecteResultForInput.put(".1", true);
        expecteResultForInput.put("46.e3", true);

        Solution solution = new Solution();

        for (Map.Entry<String, Boolean> entry : expecteResultForInput.entrySet()) {
            String input = entry.getKey();
            boolean expectedResult = entry.getValue();
            boolean result = solution.isNumber(input);

            System.out.println(((result == expectedResult) ? "PASS" : "FAIL")
                    + "\t" + result + "\t" + input);
        }
    }
}
