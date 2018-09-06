package bo.zhao.practice.jp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesAction {

    public static void main(String[] args) throws IOException {
        Properties settings = new Properties();
        settings.put("title", "Hello, world");
        FileOutputStream fos = new FileOutputStream("program.properties");
        settings.store(fos, "Program Properties");
        System.out.println(System.getProperty("user.home"));
    }
}
