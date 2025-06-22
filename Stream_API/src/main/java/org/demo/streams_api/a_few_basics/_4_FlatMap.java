package org.demo.streams_api.a_few_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _4_FlatMap {

    /**
     * Given a list of sentences, returns a flat list of all words.
     *
     * Sample Input:
     * List<String> sentences = Arrays.asList(
     *     "Hello World",
     *     "Java Streams are powerful"
     * );
     *
     * Sample Output:
     * ["Hello", "World", "Java", "Streams", "are", "powerful"]
     */
    public static List<String> sentencesToWords(List<String> sentences) {
        return sentences.stream()
                // 1. For each sentence, split it on whitespace into an array of words
                .map(sentence -> sentence.split("\\s+"))
                // 2. Flatten the Stream<String[]> into a Stream<String> of individual words
                .flatMap(Arrays::stream)
                // 3. Collect the words into a List<String>
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample input
        List<String> sentences = Arrays.asList(
                "Hello World",
                "Java Streams are powerful"
        );

        // Invoke the utility method
        List<String> words = sentencesToWords(sentences);

        // Print the results
        System.out.println(words);
        // Expected output: [Hello, World, Java, Streams, are, powerful]
    }
}

