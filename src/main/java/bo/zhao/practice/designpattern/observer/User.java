package bo.zhao.practice.designpattern.observer;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/18
 */
public class User implements MyObserver {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void receive(NewsModel model) {
        System.out.println(name + " receive news:" + model.getTitle() + "  " + model.getContent());
    }
}
