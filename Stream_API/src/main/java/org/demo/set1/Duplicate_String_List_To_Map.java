package org.demo.set1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Duplicate_String_List_To_Map {
    public static void main(String[] args) {
        // List of strings with duplicates
        List<String> stringList = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");

        // Using Stream API to count the occurrences of each string
        Map<String, Integer> stringCountMap = stringList.stream()
                .collect(Collectors.toMap(
                        str -> str,                // The key of the map is the string itself
                        str -> 1,                  // The value is initialized to 1 for each occurrence
                        Integer::sum               // If the string is already in the map, sum the occurrences
                ));

        // Printing the result
        stringCountMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
