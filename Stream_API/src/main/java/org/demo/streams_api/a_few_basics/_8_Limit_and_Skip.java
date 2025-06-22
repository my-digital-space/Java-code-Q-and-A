package org.demo.streams_api.a_few_basics;

import java.util.stream.Stream;
import java.util.List;
import java.util.stream.Collectors;

public class _8_Limit_and_Skip {

    /**
     * Sample input: infinite sequence starting at 0:
     *   0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
     *
     * After skipping the first 10 and taking the next 5, the sample output is:
     *   [10, 11, 12, 13, 14]
     */

    public static List<Integer> getSkippedAndLimitedSequence() {
        // 1. Generate an infinite Stream of integers, starting from 0, incrementing by 1
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);

        // 2. Skip the first 10 elements (i.e., 0 through 9)
        Stream<Integer> skipped = infiniteStream.skip(10);

        // 3. Limit the stream to the next 5 elements (i.e., 10 through 14)
        Stream<Integer> limited = skipped.limit(5);

        // 4. Collect the resulting elements into a List<Integer>
        return limited.collect(Collectors.toList());

        // Or the above 4 steps can be done together as below:
        // Generate an infinite stream, skip 10, take 5, then collect—all in one chain
//        return Stream.iterate(0, n -> n + 1)
//                .skip(10)                // drop 0–9
//                .limit(5)                // keep next 5
//                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Invoke the method and print out the result
        List<Integer> result = getSkippedAndLimitedSequence();
        System.out.println("Result: " + result);
        // Expected console output:
        // Result: [10, 11, 12, 13, 14]
    }
}

