package bo.zhao.practice.designpattern.observer;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/18
 */
public interface MyObservable {

    void register(MyObserver myObserver);

    void remove(MyObserver myObserver);

    void send(NewsModel model);
}
