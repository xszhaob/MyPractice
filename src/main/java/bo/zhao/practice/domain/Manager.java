package bo.zhao.practice.domain;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/12/25
 */
public class Manager extends Employee {

    private Employee secretary;

    public Manager(String name, int salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}
