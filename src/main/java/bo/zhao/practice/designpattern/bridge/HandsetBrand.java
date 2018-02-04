package bo.zhao.practice.designpattern.bridge;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/13
 */
public abstract class HandsetBrand {

    protected HandsetSoft handsetSoft;

    public abstract void run();

    public void setHandsetSoft(HandsetSoft handsetSoft) {
        this.handsetSoft = handsetSoft;
    }
}
