package bo.zhao.practice.dcl;

public class DoubleCheckedLocking {

    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
//            synchronized (DoubleCheckedLocking.class) {
//                if (resource == null) {
                    resource = new Resource("test", 12);
//                }
//            }
        }
        return resource;
    }
}
