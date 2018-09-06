package bo.zhao.practice.jp.chapter05.enum_5_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumInAction {

    public static void main(String[] args) {
        String sizeStr = "SMALL";
        Size size = Enum.valueOf(Size.class, sizeStr);
        assert size == Size.SMALL;
        System.out.println("ordinal = " + size.ordinal());

        List<Size> sizes = new ArrayList<>();
        sizes.add(Size.LARGE);
        sizes.add(Size.SMALL);
        sizes.add(Size.MEDIUM);
        sizes.add(Size.EXTRA_LARGE);
        Collections.sort(sizes);
        System.out.println(sizes);
    }


    private static enum Size {
        SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

        private String desc;

        Size(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
