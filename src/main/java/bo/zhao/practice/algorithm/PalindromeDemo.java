package bo.zhao.practice.algorithm;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/31
 */
public class PalindromeDemo {

    public static void main(String[] args) {
        System.out.println(isPalindrome("ooo"));
    }

    private static boolean isPalindrome(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (str.length() == 1) {
            return true;
        }
        for (int i = 0; i < (str.length() / 2); i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
