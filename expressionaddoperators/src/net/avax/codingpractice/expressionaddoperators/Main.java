package net.avax.codingpractice.expressionaddoperators;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.addOperators("123", 6));
        System.out.println(solution.addOperators("232", 8));
        System.out.println(solution.addOperators("105", 5));
        System.out.println(solution.addOperators("00", 0));
        System.out.println(solution.addOperators("3456237490", 9191));
        System.out.println(solution.addOperators("123", 24));
//        System.out.println(solution.addOperators("10737418231073741823",
//                2147483646));
        System.out.println(solution.addOperators("1234", 24));
    }
}
