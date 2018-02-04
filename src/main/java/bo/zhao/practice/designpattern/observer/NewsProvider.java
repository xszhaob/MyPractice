package bo.zhao.practice.designpattern.observer;

import java.util.*;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/18
 */
public class NewsProvider implements MyObservable {

    private List<MyObserver> observers;

    public NewsProvider() {
        this.observers = new ArrayList<MyObserver>();
        generateNews();
    }

    private void generateNews() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int titleCount = 1;
            int contentCount = 1;

            @Override
            public void run() {
                send(new NewsModel("title:" + titleCount++, "content:" + contentCount++));
            }
        }, 1000 * 2, 1000);
    }

    @Override
    public void register(MyObserver myObserver) {
        if (myObserver == null) {
            return;
        }
        synchronized (this) {
            if (!observers.contains(myObserver)) {
                observers.add(myObserver);
            }
        }
    }

    @Override
    public void remove(MyObserver myObserver) {
        observers.remove(myObserver);
    }

    @Override
    public void send(NewsModel model) {
        for (MyObserver observer : observers) {
            observer.receive(model);
        }
    }
}
