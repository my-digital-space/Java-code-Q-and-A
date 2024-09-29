package org.demo.utility;

import java.util.List;
import java.util.Map;

public class CommonUtils {

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static <T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

}
