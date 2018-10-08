package bo.zhao.practice.jp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapInAction {

    private final Map<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public MapInAction(Map<String, Point> map) {
        locations = new ConcurrentHashMap<>(map);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }

    public static void main(String[] args) {
        Map<String, Point> map = new HashMap<>();
        map.put("111", new Point(1, 2));
        map.put("112", new Point(3, 4));
        MapInAction action = new MapInAction(map);

        Map<String, Point> snapshotMap = action.getLocations();
        System.out.println(snapshotMap);
        action.setLocation("111", 11, 12);
        System.out.println(snapshotMap);
    }


    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
