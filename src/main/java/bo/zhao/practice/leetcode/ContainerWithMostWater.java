package bo.zhao.practice.leetcode;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int max = 0;
        int j = height.length - 1;
        for (int i = 0; i < height.length && i < j; ) {
            max = Math.max((j - i) * Math.min(height[i], height[j]), max);
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
