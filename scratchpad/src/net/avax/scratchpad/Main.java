package net.avax.scratchpad;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.largestNumber(
                new int[]{3, 30, 34, 5, 9}));
        System.out.println(solution.largestNumber(
                new int[]{999999998, 999999997, 999999999}));
        System.out.println(solution.largestNumber(
                new int[]{0, 0}));

        System.out.println(solution.searchMatrix(new int[][]
                        {
                        },
                0));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {}
                        },
                0));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {},
                                {}
                        },
                0));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7},
                                {10, 11, 16, 20},
                                {23, 30, 34, 50}
                        },
                0));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7},
                                {10, 11, 16, 20},
                                {23, 30, 34, 50}
                        },
                3));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7},
                                {10, 11, 16, 20},
                                {23, 30, 34, 50}
                        },
                16));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7},
                                {10, 11, 16, 20},
                                {23, 30, 34, 50}
                        },
                50));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3, 5, 7},
                                {10, 11, 16, 20},
                                {23, 30, 34, 50}
                        },
                51));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 3}
                        },
                2));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 2},
                                {5, 7},
                                {10, 13}
                        },
                2));
        System.out.println(solution.searchMatrix(new int[][]
                        {
                                {1, 2, 3, 4},
                                {5, 6, 9, 10},
                                {10, 13, 15, 17}
                        },
                7));
    }
}
