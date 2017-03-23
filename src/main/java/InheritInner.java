/**
 * author:xszhaobo
 * <p/>
 * date:2017/3/23
 * <p/>
 * package_name:PACKAGE_NAME
 * <p/>
 * project: MyPractice
 */
public class InheritInner extends WithInner.Inner {

    InheritInner(WithInner wi) {
        wi.super();
    }
}

class WithInner {
    class Inner {}
}
