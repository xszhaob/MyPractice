package bo.zhao.practice.jp.io;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInAction {
    public static void main(String[] args) throws URISyntaxException {
//        getInAction();
//        resolveInAction();
        pathInAction();
    }


    private static void getInAction() throws URISyntaxException {
        URI uri = PathInAction.class.getClassLoader().getResource("tinyUF.txt").toURI();
        Path path = Paths.get(uri);
        System.out.println(path);
    }

    private static void resolveInAction() {
        String baseDir = System.getProperty("user.home");
        Path basePath = Paths.get(baseDir);
        Path workRelative = Paths.get("work");
        Path workPath = basePath.resolve(workRelative);
        System.out.println(workPath);
        // 通过解析指定路径的父路径产生其兄弟路径。
        // 比如workPath是C:\\Users\\user\\work，
        // 那么下面的调用将创建C:\\Users\\user\\temp
        Path tempPath = workPath.resolveSibling("temp");
        System.out.println(tempPath);
    }


    private static void pathInAction() {
        Path path = Paths.get("C:", "Users", "azhao", "Desktop", "test.properties");
        System.out.println(path);
        System.out.println(path.getParent());
        System.out.println(path.getFileName());
        System.out.println(path.getRoot());
        Path workPath = Paths.get("myWork");
        System.out.println(workPath);
        // 返回给定路径的绝对路径，从根部件开始
        System.out.println(workPath.toAbsolutePath());
    }
}
