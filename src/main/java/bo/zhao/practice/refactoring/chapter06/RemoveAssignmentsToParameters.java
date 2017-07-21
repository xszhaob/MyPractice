package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/21.
 * 在按值传递的情况下，对参数的任何修改，都不会对调用端造成任何影响；
 * 另外，如果只以参数表示“被传递进来的东西”，那么代码回清晰得多。
 */
public class RemoveAssignmentsToParameters {

    private int discount(int inputVal, int quantity, int yearToDate) {
        if (inputVal > 50) {
            inputVal -= 2;
        }

        if (quantity > 100) {
            inputVal -= 1;
        }

        if (yearToDate > 10000) {
            inputVal -= 4;
        }

        return inputVal;
    }


    private int discountV2(int inputVal, int quantity, int yearToDate) {
        int result = inputVal;
        if (inputVal > 50) {
            result -= 2;
        }

        if (quantity > 100) {
            result -= 1;
        }

        if (yearToDate > 10000) {
            result -= 4;
        }

        return result;
    }
}
