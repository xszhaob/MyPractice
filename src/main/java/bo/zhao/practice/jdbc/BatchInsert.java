package bo.zhao.practice.jdbc;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.*;

public class BatchInsert {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_test_db?serverTimezone=UTC", "root", "admin123456");

//        testQuery(connection);


        testBatchInsert(connection);

    }

    private static void testBatchInsert(Connection connection) throws SQLException {
        String sqlFormat = "insert into person (first_name, last_name, age) values ('%s','%s',%d)";

        for (int i = 5256792; i < 10000000; i++) {
            String sql = String.format(sqlFormat, "foo" + i, "linus" + i, i % 100 + 1);
            CallableStatement callableStatement = connection.prepareCall(sql);
            boolean execute = callableStatement.execute();
            System.out.println(execute);
        }
    }

    private static void testQuery(Connection connection) throws SQLException {
        String sql = "select * from person";
        CallableStatement callableStatement = connection.prepareCall(sql);

        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int age = resultSet.getInt("age");

        }
    }

    @Getter
    @Setter
    @ToString
    private static class Person {
        private long id;
        private String firstName;
        private String lastName;
        private int age;
    }
}
