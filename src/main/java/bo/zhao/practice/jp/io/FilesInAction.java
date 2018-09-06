package bo.zhao.practice.jp.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class FilesInAction {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        readInAction();
//        writeInAction();
//        copyInAction();
//        moveInAction();
//        deleteInAction();
//        createFileInAction();
//        createTempFileInAction();
        fileInfoInAction();
    }


    private static void readInAction() throws IOException, URISyntaxException {
        URL url = FilesInAction.class.getClassLoader().getResource("tinyUF.txt");
        Path path = Paths.get(url.toURI());
        byte[] bytes = Files.readAllBytes(path);
        System.out.println(new String(bytes));
        List<String> strings = Files.readAllLines(path);
        System.out.println(strings);
    }

    private static void writeInAction() throws URISyntaxException, IOException {
        URL url = FilesInAction.class.getClassLoader().getResource("test.properties");
        Path path = Paths.get(url.toURI());
        Path write = Files.write(path, "name=bo.zhao".getBytes(Charset.forName("utf-8")));
        System.out.println(write);
    }

    private static void copyInAction() throws IOException {
        Path fromPath = Paths.get("C:", "Users", "azhao", "Desktop", "test.properties");
        Path toPath = Paths.get("C:", "Users", "azhao", "Desktop", "test_01.properties");
        Path copy = Files.copy(fromPath, toPath,
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        System.out.println(copy);
    }

    private static void moveInAction() throws IOException {
        Path fromPath = Paths.get("C:", "Users", "azhao", "Desktop", "test.properties");
        Path toPath = Paths.get("C:", "Users", "azhao", "Desktop", "test_02.properties");
        Files.move(fromPath, toPath, StandardCopyOption.ATOMIC_MOVE);
    }

    private static void deleteInAction() throws IOException {
        Path path = Paths.get("C:", "Users", "azhao", "Desktop", "test_02.properties");
        Files.delete(path);
        Files.deleteIfExists(path);
    }

    private static void createFileInAction() throws IOException {
        Path path = Paths.get("C:", "Users", "azhao", "Desktop", "test_02.properties");
        Files.createFile(path);
    }

    private static void createTempFileInAction() throws IOException {
        Path path = Paths.get("C:", "Users", "azhao", "Desktop", "temp");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        Path tempFile = Files.createTempFile(path, "test", ".txt");
        System.out.println(tempFile);
    }


    private static void fileInfoInAction() throws IOException {
        Path path = Paths.get("C:\\Users\\azhao\\Desktop\\temp\\test5366204675345351469.txt");
        UserPrincipal owner = Files.getOwner(path);
        System.out.println(owner);
        BasicFileAttributes readAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("file size: " + readAttributes.size());
        System.out.println("creationTime: " + readAttributes.creationTime());
        System.out.println("isDirectory: " + readAttributes.isDirectory());
        System.out.println("lastAccessTime: " + readAttributes.lastAccessTime());
        System.out.println("lastModifiedTime: " + readAttributes.lastModifiedTime());
    }
}
