package bo.zhao.practice.jp.chapter09.class_9_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassLoaderTest {


    class CryptoClassLoader extends ClassLoader {
        private int key;

        public CryptoClassLoader(int key) {
            this.key = key;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] classBytes = loadClassBytes(name);

                Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
                if (clazz == null) {
                    throw new ClassNotFoundException(name);
                }
                return clazz;
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }

        private byte[] loadClassBytes(String name) throws IOException {
            String cName = name.replace(".", "/") + "caesar";

            byte[] bytes = Files.readAllBytes(Paths.get(cName));

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] - key);
            }
            return bytes;
        }
    }
}
