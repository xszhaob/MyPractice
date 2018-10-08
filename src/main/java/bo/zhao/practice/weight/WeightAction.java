package bo.zhao.practice.weight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeightAction {

    public static void main(String[] args) {
        Weight a = new Weight("a", 10);
        Weight b = new Weight("b", 20);
        Weight c = new Weight("c", 30);
        Weight d = new Weight("d", 30);
        Weight e = new Weight("e", 5);
        Weight f = new Weight("f", 5);
        Weight[] weightArr = new Weight[]{a, b, c, d, e, f};


        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            String s = getIdByWeight(weightArr);
            Integer cnt = map.get(s);
            if (cnt == null) {
                cnt = 1;
            } else {
                cnt++;
            }
            map.put(s, cnt);
        }

        map.forEach((str, cnt) -> System.out.println(str + ":" + cnt));
    }


    private static String getIdByWeight(Weight[] weightArr) {
        int totalWeight = Arrays.stream(weightArr).mapToInt(Weight::getWeight).sum();
        Random random = new Random();
        int r = random.nextInt(totalWeight);
        for (Weight weight : weightArr) {
            r -= weight.getWeight();
            if (r < 0) {
                return weight.getId();
            }
        }
        throw new RuntimeException("this err do not occur");
    }


    private static class Weight {
        private String id;
        private int weight;

        public Weight(String id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
