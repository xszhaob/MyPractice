package bo.zhao.practice.designpattern.observer;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/18
 */
public class Demo {

    public static void main(String[] args) {
        NewsProvider provider = new NewsProvider();
        User user;
        for (int i = 0; i < 10; i++) {
            user = new User("user:" + i);
            provider.register(user);
        }
    }
}
