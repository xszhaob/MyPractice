package bo.zhao.practice.nio;

import bo.zhao.practice.domain.Employee;
import bo.zhao.practice.domain.Manager;
import org.junit.Test;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/12/25
 */
public class ObjectIO {

    @Test
    public void test() {
        Employee harry = new Employee("harry", 5000, 1989, 10, 1);
        Manager boss = new Manager("Carl Cracker", 8000, 1987, 12, 15);

    }
}
