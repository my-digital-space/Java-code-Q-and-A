package org.demo.streams_api.a_few_basics;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class _3_Map {

    /**
     * Transforms a list of Person objects into a list of their email addresses.
     *
     * Sample Input:
     * List<Person> persons = Arrays.asList(
     *     new Person("Alice", "alice@example.com"),
     *     new Person("Bob", "bob@example.com"),
     *     new Person("Carol", "carol@example.com")
     * );
     *
     * Sample Output:
     * [ "alice@example.com", "bob@example.com", "carol@example.com" ]
     */
    public static List<String> extractEmails(List<Person> persons) {
        // 1. Convert the List<Person> into a Stream<Person>
        return persons.stream()
                // 2. Apply map() to transform each Person into their email address (String)
                .map(Person::getEmail)
                // 3. Collect the resulting Stream<String> back into a List<String>
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Prepare sample input: a list of Person objects
        List<Person> persons = Arrays.asList(
                new Person("Alice", "alice@example.com"),
                new Person("Bob",   "bob@example.com"),
                new Person("Carol", "carol@example.com")
        );

        // Invoke the method to get the list of email addresses
        List<String> emails = extractEmails(persons);

        // Print the output to verify results
        System.out.println(emails);
        // Expected console output:
        // [alice@example.com, bob@example.com, carol@example.com]
    }
}

// Simple Person class with name and email fields
class Person {
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name  = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

