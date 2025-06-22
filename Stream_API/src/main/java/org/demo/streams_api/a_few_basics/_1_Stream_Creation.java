package org.demo.streams_api.a_few_basics;

import java.util.Arrays;
import java.util.stream.Stream;

public class _1_Stream_Creation {

    /**
     * Input: { 10, 20, 30, 40, 50 };
     * Output:
     * 10
     * 20
     * 30
     * 40
     * 50
     */
    public static void main(String[] args) {
        int[] input = { 10, 20, 30, 40, 50 }; // primitive type "int" here
        Stream<Integer> stream = toStream(input);
        stream.forEach(System.out::println);
    }

    /**
     * Converts an array of primitive ints into a Stream of Integer objects.
     */
    public static Stream<Integer> toStream(int[] arr) {
        return Arrays.stream(arr)    // creates an IntStream from the int[]
                .boxed();       // boxes each int to an Integer
    }

}
