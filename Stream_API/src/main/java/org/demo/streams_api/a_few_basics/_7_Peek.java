package org.demo.streams_api.a_few_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _7_Peek {
    /**
     * Sample Input:
     * [1, 2, 3, 4, 5, 6]
     *
     * Expected Sample Output:
     * "Even numbers: [2, 4, 6]"
     */
    public static String processNumbers(List<Integer> numbers) {
        // 1. Begin a stream pipeline from the input list
        return numbers.stream()
                // 2. Log each element before the filter is applied
                .peek(n -> System.out.println("Before filter: " + n))
                // 3. Filter to retain only even numbers
                .filter(n -> n % 2 == 0)
                // 4. Log each element after the filter is applied
                .peek(n -> System.out.println("After filter: " + n))
                // 5. Collect the remaining elements into a list
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        evens -> "Even numbers: " + evens
                ));
    }

    public static void main(String[] args) {
        // Generate sample input
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Process the input and capture the result
        String result = processNumbers(input);

        // Print the final result
        System.out.println(result);
    }
}

