package bo.zhao.practice.designpattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/4
 */
public class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0f;

        for (Item item : items) {
            cost += item.price();
        }

        return cost;
    }


    public void showItems() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append("Item: ").append(item.name())
                    .append(",Packing: ").append(item.packing().pack())
                    .append(",Price: ").append(item.price())
                    .append("\n");
        }
        System.out.println(sb.toString());
    }
}
