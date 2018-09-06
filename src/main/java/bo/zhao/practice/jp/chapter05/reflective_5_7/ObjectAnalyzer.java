package bo.zhao.practice.jp.chapter05.reflective_5_7;

import bo.zhao.practice.java8.chapter03.Apple;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) {
            return "null";
        }

        if (visited.contains(obj)) {
            return "...";
        }
        visited.add(obj);

        Class<?> clazz = obj.getClass();
        if (clazz == String.class) {
            return (String) obj;
        }
        if (clazz.isArray()) {
            StringBuilder r = new StringBuilder(clazz.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(obj, i);
                if (clazz.getComponentType().isPrimitive()) {
                    r.append(val);
                } else {
                    r.append(toString(val));
                }
            }
            return r.toString();
        }

        StringBuilder name = new StringBuilder(clazz.getName());
        do {
            name.append("[");
            Field[] fields = clazz.getDeclaredFields();

            AccessibleObject.setAccessible(fields, true);

            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!name.toString().endsWith("[")) {
                        name.append(",");
                    }
                    name.append(field.getName()).append("=");

                    try {
                        Class<?> type = field.getType();
                        Object val = field.get(obj);
                        if (type.isPrimitive()) {
                            name.append(val);
                        } else {
                            name.append(toString(val));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            name.append("]");
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return name.toString();
    }


    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.setColor("red");
        apple.setWeight(150);

        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            s.add(i);
        }
        String str = new ObjectAnalyzer().toString(s);
        System.out.println(str);
    }
}
