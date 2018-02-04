package bo.zhao.practice.designpattern.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/13
 */
public class BridgeDemo {
    public static void main(String[] args) {
        HandsetSoft game = new HandsetGame();
        HandsetSoft addressList = new HandsetAddressList();
        HandsetSoft mp3 = new HandsetMP3();

        List<HandsetSoft> softList = new ArrayList<HandsetSoft>(3);
        softList.add(game);
        softList.add(addressList);
        softList.add(mp3);

        run(new HandsetBrandApple(), softList);
        run(new HandsetBrandXiaoMi(), softList);
    }


    private static void run(HandsetBrand hb, List<HandsetSoft> softList) {
        for (HandsetSoft soft : softList) {
            hb.setHandsetSoft(soft);
            hb.run();
        }
    }
}
