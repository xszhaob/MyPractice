package bo.zhao.practice.designpattern.component;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/13
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        // non op
    }

    @Override
    public void remove(Component component) {
        // non op
    }

    @Override
    public void display(int depth) {
        System.out.println(getDepthDesc(depth) + getName());
    }
}
