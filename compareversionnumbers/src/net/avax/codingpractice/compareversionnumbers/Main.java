package net.avax.codingpractice.compareversionnumbers;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (List<String> versions : Arrays.asList(
                Arrays.asList("0.1", "1.1"),
                Arrays.asList("1.0.1", "1"),
                Arrays.asList("7.5.2.4", "7.5.3"),
                Arrays.asList("1.01", "1.001"),
                Arrays.asList("1.0", "1.0.0"),
                Arrays.asList("1.1", "1.10")
        )) {
            String version1 = versions.get(0);
            String version2 = versions.get(1);

            int output = solution.compareVersion(version1, version2);

            System.out.println("Input: version1 = \"" + version1
                    + "\", version2 = \"" + version2
                    + "\"\nOutput: " + output + "\n");
        }
    }
}
