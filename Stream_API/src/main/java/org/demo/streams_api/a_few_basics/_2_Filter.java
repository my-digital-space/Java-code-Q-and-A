package org.demo.streams_api.a_few_basics;

import java.util.List;
import java.util.stream.Collectors;

public class _2_Filter {

    /**
     * Filters and returns only the strings that start with the letter "A" (case-sensitive).
     *
     * @param inputList List of strings to filter
     * @return List of strings starting with "A"
     *
     * Sample Input:  ["Apple", "Banana", "Avocado", "berry", "Apricot"]
     * Sample Output: ["Apple", "Avocado", "Apricot"]
     */
    public static List<String> filterStartsWithA(List<String> inputList) {
        return inputList.stream()                          // Step 1: Convert the list to a stream
                .filter(s -> s.startsWith("A"))    // Step 2: Keep only strings that start with "A"
                .collect(Collectors.toList());     // Step 3: Collect the filtered results back into a list
    }

    public static void main(String[] args) {
        // Step 4: Prepare sample input
        List<String> input = List.of("Apple", "Banana", "Avocado", "berry", "Apricot");

        // Step 5: Call the filter method
        List<String> result = filterStartsWithA(input);

        // Step 6: Print the output
        System.out.println("Filtered List: " + result);
    }
}
