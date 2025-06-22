package org.demo.streams_api.a_few_basics;

import java.util.*;
import java.util.stream.Collectors;

public class _6_Sorted {

    /**
     * Sorts a list of Employee objects by salary in descending order.
     *
     * Sample Input:
     *   [ John ($50000.0), Alice ($75000.0), Bob ($60000.0) ]
     *
     * Sample Output:
     *   [ Alice ($75000.0), Bob ($60000.0), John ($50000.0) ]
     *
     * @param employees the input list of employees
     * @return a new list sorted by salary descending
     */
    public static List<Employee> sortBySalaryDesc(List<Employee> employees) {
        // 1. Convert the List<Employee> to a Stream<Employee>
        return employees.stream()
                // 2. Apply sorted with a Comparator that compares salary in reverse (descending)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                // 3. Collect the sorted stream back into a List<Employee>
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample input list
        List<Employee> inputList = Arrays.asList(
                new Employee("John", 50000.0),
                new Employee("Alice", 75000.0),
                new Employee("Bob", 60000.0)
        );

        // Invoke the sorting method
        List<Employee> sortedList = sortBySalaryDesc(inputList);

        // Print out the sorted list
        System.out.println("Sorted by salary descending:");
        sortedList.forEach(System.out::println);
    }

    // Employee class with name and salary fields
    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " ($" + salary + ")";
        }
    }
}

