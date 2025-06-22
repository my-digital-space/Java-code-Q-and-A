package org.demo.streams_api.a_few_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _5_Distinct {

    /**
     * Returns a list of unique integers by removing duplicates from the input list.
     *
     * Sample Input:  [1, 2, 3, 2, 4, 1]
     * Sample Output: [1, 2, 3, 4]
     */
    public static List<Integer> getDistinctList(List<Integer> list) {
        // 1. Create a Stream from the input list
        // 2. Apply distinct() to filter out duplicate elements
        // 3. Collect the resulting Stream back into a List<Integer>
        return list.stream()       // step 1: obtain Stream<Integer>
                .distinct()     // step 2: keep only unique elements
                .collect(Collectors.toList()); // step 3: collect to List
    }

    public static void main(String[] args) {
        // Sample input list with duplicates
        List<Integer> input = Arrays.asList(1, 2, 3, 2, 4, 1);

        // Invoke the method to get unique values
        List<Integer> distinctList = getDistinctList(input);

        // Print the result
        System.out.println("Distinct values: " + distinctList);
        // Expected Output: Distinct values: [1, 2, 3, 4]
    }
}

