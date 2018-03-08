package net.avax.scratchpad;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.minSubArrayLen(
                7,
                new int[]{2, 3, 1, 2, 4, 3}
        ));

        for (String input : List.of(

                // Expected: "aba"

                "aab",

                // Expected: ""

                "aaab",

                // Expected: "vlvov"

                "vvvlo",

                // Expected:
                // "eweweweweweweweweweweweweweueueueueueueueueueueueueueueuhuhuhuhuhuhshshshshshshshshshshshshshshshshshshshphphphpcpcpcpcpcpcpcpcpcpcpcpcpcpcpcrcrcrcrcrcrcrcrcrcrcrcrmrmrmrmrmrmrmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmgmgvgvgvgvgvgvgvgvgvgvgvgvgvgvgvgvovovovovovovovovonononononononononbnbnbnbnbnbnbnbnbnbnbnbnbnbnbabaiaiaiaiaiaiaiaiaiaiaiaiaiaiaiaiatatatatatftftftftftftftftftftftfdfdfdfdfdfdfdfdfdfdfdydydydydyzyzyzyzyzyzyzyzyzyzyzyzyzyzyjyjyjyjkjkjkjkjkjkjkjklklklklklklklklklklklkqkqkqwqwqwqwqwqwqwqwq"

                "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao"

        )) {
            String result = solution.reorganizeString(input);

            System.out.println(input + ": \"" + result + "\"");

            int adjCount = 0;
            Character prevC = null;

            for (char c : result.toCharArray()) {
                if (prevC != null && prevC.equals(c)) {
                    adjCount++;
                }
                prevC = c;
            }

            if (adjCount > 0) {
                System.out.println("FAIL: " + adjCount
                        + " identical adjacent charater(s)");
            }
        }
    }
}
