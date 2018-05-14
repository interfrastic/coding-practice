package net.avax.scratchpad;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.canPlaceFlowers(new int[]{1, 0, 0, 0, 1},
                1));
        System.out.println(solution.canPlaceFlowers(new int[]{1, 0, 0, 0, 1},
                2));

        System.out.println(solution.findDuplicate(new String[]{
                "root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}));

        System.out.println(solution.findDuplicate(new String[]{
                "root/a 1.txt(abcd) 2.txt(efsfgh)", "root/c 3.txt(abdfcd)",
                "root/c/d 4.txt(efggdfh)"}));

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

        solution.rotate(array, 3);
        System.out.println(Arrays.toString(array));

        array = new int[]{-1, -100, 3, 99};

        solution.rotate(array, 2);
        System.out.println(Arrays.toString(array));
    }
}
