package bo.zhao.practice.designpattern.bridge;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/13
 */
public class HandsetBrandApple extends HandsetBrand {

    @Override
    public void run() {
        handsetSoft.run();
    }


}
