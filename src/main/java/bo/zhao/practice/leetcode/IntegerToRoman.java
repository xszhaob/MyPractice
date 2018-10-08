package bo.zhao.practice.leetcode;

public class IntegerToRoman {

    public static String intToRoman(int num) {
        int[] numbs = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbs.length && num != 0; i++) {
            int a = num / numbs[i];
            if (a != 0) {
                for (int j = 0; j < a; j++) {
                    sb.append(roman[i]);
                }
            }
            num = num % numbs[i];
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(intToRoman(1000));
    }
}
