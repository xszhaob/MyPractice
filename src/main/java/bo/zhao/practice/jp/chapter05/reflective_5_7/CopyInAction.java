package bo.zhao.practice.jp.chapter05.reflective_5_7;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyInAction {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] newIntArr = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(newIntArr));
    }

    @SuppressWarnings("unchecked")
    public static Object goodCopyOf(Object a, int newLength) {
        Class<?> clazz = a.getClass();

        if (!clazz.isArray()) {
            return null;
        }

        Class<?> componentType = clazz.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);

        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
