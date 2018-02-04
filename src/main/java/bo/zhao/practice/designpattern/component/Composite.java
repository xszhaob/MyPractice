package bo.zhao.practice.designpattern.component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/13
 */
public class Composite extends Component {

    private List<Component> components = new ArrayList<Component>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        Iterator<Component> iterator = components.iterator();
        while (iterator.hasNext()) {
            Component next = iterator.next();
            if (next.getName().equalsIgnoreCase(component.getName())) {
                iterator.remove();
            }
        }
    }

    @Override
    public void display(int depth) {
        System.out.println(getDepthDesc(depth) + getName());
        for (Component component : components) {
            component.display(depth + 1);
        }
    }
}
