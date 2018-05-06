package bo.zhao.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/4/11
 */
public class MergeIntervals {


    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 1 || intervals.size() == 0) {
            return intervals;
        }

        Interval temp = intervals.get(0);
        List<Interval> result = new ArrayList<>();
        result.add(temp);
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.end <= temp.end) {
                continue;
            } else if (current.start <= temp.end) {
                temp.end = current.end;
            } else {
                temp = current;
                result.add(temp);
            }
        }
        return result;
    }


    private static class Interval {
        int start;
        int end;

        public Interval() {
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
