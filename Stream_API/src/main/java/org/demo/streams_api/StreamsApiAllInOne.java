package org.demo.streams_api;

import org.demo.utility.CommonUtils;
import org.demo.utility.Employee;
import org.demo.utility.EmployeesUtil;
import org.demo.utility.Product;
import org.demo.utility.ProductsUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class StreamsApiAllInOne {
    public static void main(String[] args) {
        /** 1.
           Employee list - department wise total salary.

           Input:
            List of Employees
            Employee(Integer empId, String empName, String empDepartment, BigDecimal empSalary, Integer age)
            new Employee(1, "Name 1", "HR Department", BigDecimal.valueOf(100.00), 30);
            new Employee(2, "Name 2", "HR Department", BigDecimal.valueOf(300.00), 26);
            new Employee(3, "Name 3", "IT Department", BigDecimal.valueOf(600.00), 45);
            new Employee(4, "Name 4", "HR Department", BigDecimal.valueOf(200.00), 55);
            new Employee(5, "Name 5", "IT Department", BigDecimal.valueOf(400.00), 60);

           Output:
            IT Department : 1000.0
            HR Department : 600.0
         */
        List<Employee> employeeList = EmployeesUtil.getEmployeeList();
        Map<String, BigDecimal> totalSalariesByDepartment =
                employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getEmpDepartment,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Employee::getEmpSalary,
                                BigDecimal::add
                        )
                ));
        System.out.println("\n\n\n1. Employee list - department wise total salary");
        CommonUtils.printMap(totalSalariesByDepartment);

        /** 2.
           Employee list - filter with age greater than 45

           Input:
            List of Employees
            Employee(Integer empId, String empName, String empDepartment, BigDecimal empSalary, Integer age)
            new Employee(1, "Name 1", "HR Department", BigDecimal.valueOf(100.00), 30);
            new Employee(2, "Name 2", "HR Department", BigDecimal.valueOf(300.00), 26);
            new Employee(3, "Name 3", "IT Department", BigDecimal.valueOf(600.00), 45);
            new Employee(4, "Name 4", "HR Department", BigDecimal.valueOf(200.00), 55);
            new Employee(5, "Name 5", "IT Department", BigDecimal.valueOf(400.00), 60);

           Output:
            Employee{empId=4, empName='Name 4', empDepartment='HR Department', empSalary=200.0, age=55}
            Employee{empId=5, empName='Name 5', empDepartment='IT Department', empSalary=400.0, age=60}
         */
        employeeList = EmployeesUtil.getEmployeeList();
        List<Employee> filteredEmployees = employeeList.stream()
                .filter(employee -> employee.getAge() > 45)
                .toList();

        System.out.println("\n\n\n2. Employee list - filter with age greater than 45");
        CommonUtils.printList(filteredEmployees);

        /** 3.
           Employee list - Find Nth Highest Salary Using Java Streams API

           Note:
           Multiple Employees can have the same salary

           Input:
            List of Employees
            Employee(Integer empId, String empName, String empDepartment, BigDecimal empSalary, Integer age)
            new Employee(1, "EmpName1", "Dept1", BigDecimal.valueOf(200.00), 50); // 4th Highest
            new Employee(2, "EmpName2", "Dept2", BigDecimal.valueOf(400.00), 45); // 2nd Highest
            new Employee(3, "EmpName3", "Dept3a", BigDecimal.valueOf(100.00), 25); // 5th Highest
            new Employee(4, "EmpName4", "Dept3b", BigDecimal.valueOf(100.00), 30); // 5th Highest
            new Employee(5, "EmpName5", "Dept3c", BigDecimal.valueOf(100.00), 46); // 5th Highest
            new Employee(6, "EmpName6", "Dept4", BigDecimal.valueOf(300.00), 45); // 3rd Highest
            new Employee(7, "EmpName7", "Dept5", BigDecimal.valueOf(500.00), 50); // 1st Highest

           Output:
            Employee{empId=3, empName='EmpName3', empDepartment='Dept3a', empSalary=100.0, age=25}
            Employee{empId=4, empName='EmpName4', empDepartment='Dept3b', empSalary=100.0, age=30}
            Employee{empId=5, empName='EmpName5', empDepartment='Dept3c', empSalary=100.0, age=46}
         */
        employeeList = EmployeesUtil.getEmployeeList1();
        int n = 5;

        List<Employee> empListOutput = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getEmpSalary, // Group by salary
                        Collectors.toList()  // Collect employees with the same salary into a list
                ))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey())) // Sort by salary in descending order
                .map(Map.Entry::getValue) // Get only the list of employees for each salary group
                .collect(Collectors.toList())
                .get(n - 1); // Get the nth highest salary group

        System.out.println("\n\n\n3. Employee list - Find Nth (5th) Highest Salary Using Java Streams API");
        CommonUtils.printList(empListOutput);

        /** 4.
           Product list -> each product has an inventory count -> we need total inventory count

           Input:
            List of Products
            Product(Integer id, BigDecimal price, Integer inventoryCount)
            new Product(1, new BigDecimal("29.99"), 100)
            new Product(2, new BigDecimal("49.99"), 200)
            new Product(3, new BigDecimal("19.99"), 150)
            new Product(4, new BigDecimal("99.99"), 50)
            new Product(5, new BigDecimal("9.99"), 300)

           Output:
            Total Inventory: 800
         */
        List<Product> productList = ProductsUtil.getProductsList();

        int totalInventory = productList.stream()
                .mapToInt(Product::getInventoryCount)
                .sum();

        System.out.println("\n\n\n4. Product list -> each product has an inventory count -> we need total inventory count");
        System.out.println("Total Inventory: " + totalInventory);

        /** 5.
           Duplicate Strings in List of Strings to Map containing Count for each String

           Input:
            List of String
            Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana")

           Output:
            orange : 1
            banana : 3
            apple : 2
         */
        List<String> stringList = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");

        Map<String, Integer> stringCountMap =
                stringList.stream()
                .collect(Collectors.toMap(
                        str -> str,                // The key of the map is the string itself
                        str -> 1,                  // The value is initialized to 1 for each occurrence
                        Integer::sum               // If the string is already in the map, sum the occurrences
                ));

        System.out.println("\n\n\n5. Duplicate Strings in List of Strings to Map containing Count for each String");
        CommonUtils.printMap(stringCountMap);

        /** 6.
           Convert each word's first character to uppercase from a given sentence. | Title Case

           Input:
            String | Sentence
            i am your boss

           Output:
            I Am Your Boss
         */
        String mySentence = "i am your boss";

        String result = Arrays.stream(mySentence.split(" "))  // Split sentence into words
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))  // Capitalize first letter
                .collect(Collectors.joining(" "));  // Join words back into a sentence

        System.out.println("\n\n\n6. Convert each word's first character to uppercase from a given sentence. | Title Case");
        System.out.println("Input: " + mySentence);
        System.out.println("Output: " + result);

        /** 7.
           Compare input sentence's each word to given count.

           Inputs:
            String | Sentence
            you are the boss

            int desiredLength = 3;

           Output (Map<Boolean, List<String>>):
            false : [boss]
            true : [you, are, the]

            Group 1 (true): Words whose length is less than or equal to 'desiredLength'
            Group 2 (false): Words whose length is greater than 'desiredLength'
         */
        mySentence = "you are the boss";
        int desiredLength = 3;

        Map<Boolean, List<String>> result1 = Arrays.stream(mySentence.split(" "))  // Split sentence into words
                .collect(Collectors.partitioningBy(word -> word.length() <= desiredLength));
        System.out.println("\n\n\n7. Compare input sentence's each word to given count.");
        System.out.println("Input: " + mySentence + "\nOutput:");
        CommonUtils.printMap(result1);

        /** 8a.
           Partition a List of Integers into Even and Odd Numbers

           Input:
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

           Output:
            false : [boss]
            true : [you, are, the]

         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Map<Boolean, List<Integer>> result2 = numbers.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));  // Partition by even/odd
        System.out.println("\n\n\n8a. Partition a List of Integers into Even and Odd Numbers");
        System.out.println("Input: " + numbers + "\nOutput:");
        CommonUtils.printMap(result2);

        // 8b. in the above code if we want the output like (even/odd) instead of (true/false):
        /**
         *      even : [2, 4, 6, 8]
         *      odd : [1, 3, 5, 7, 9]
         */
        Map<String, List<Integer>> result3 = numbers.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey() ? "even" : "odd",  // Map true to "even" and false to "odd"
                        Map.Entry::getValue
                ));
        System.out.println("\n\n\n8b. Partition a List of Integers into Even and Odd Numbers");
        System.out.println("Input: " + numbers + "\nOutput:");
        CommonUtils.printMap(result3);

        /** 9.
         Partition a List of Strings Based on Whether They Start with a Vowel

         Input:
         [apple, banana, orange, grape, elephant]

         Output:
         false : [banana, grape]
         true : [apple, orange, elephant]

         */
        List<String> words = Arrays.asList("apple", "banana", "orange", "grape", "elephant");

        Map<Boolean, List<String>> result4 =
                words.stream()
                .collect(Collectors.partitioningBy(word ->
                        word.toLowerCase().matches("^[aeiou].*")));  // Check if the word starts with a vowel

        System.out.println("\n\n\n9. Partition a List of Strings Based on Whether They Start with a Vowel");
        System.out.println("Input: " + words + "\nOutput:");
        CommonUtils.printMap(result4);

        /** 10.
         Partition Employees Based on Their Salary

         Inputs:
         List of Employees
         Employee(Integer empId, String empName, String empDepartment, BigDecimal empSalary, Integer age)
         new Employee(1, "Name 1", "HR Department", BigDecimal.valueOf(100.00), 30);
         new Employee(2, "Name 2", "HR Department", BigDecimal.valueOf(300.00), 26);
         new Employee(3, "Name 3", "IT Department", BigDecimal.valueOf(600.00), 45);
         new Employee(4, "Name 4", "HR Department", BigDecimal.valueOf(200.00), 55);
         new Employee(5, "Name 5", "IT Department", BigDecimal.valueOf(400.00), 60);

         salaryThreshold = new BigDecimal("300");

         Output:
         false : Employee{empId=1, empName='EmpName1', empSalary=200.0}, Employee{empId=3, empName='EmpName3', empSalary=100.0},
                Employee{empId=4, empName='EmpName4', empSalary=100.0}, Employee{empId=5, empName='EmpName5', empSalary=100.0},
                Employee{empId=6, empName='EmpName6', empSalary=300.0}

         true : Employee{empId=2, empName='EmpName2', empSalary=400.0},
                Employee{empId=7, empName='EmpName7', empSalary=500.0}

         */
        BigDecimal salaryThreshold = new BigDecimal("300");

        Map<Boolean, List<Employee>> result5 = employeeList.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getEmpSalary().compareTo(salaryThreshold) > 0));

        System.out.println("\n\n\n10. Partition Employees Based on Their Salary");
        System.out.println("Input Salary: " + salaryThreshold + "\nOutput:");
        CommonUtils.printEmployeeMapNameIDAndSalary(result5);

        /** 11.
         Unbounded Random Number Generation

         Inputs:
         NA

         Output:
         -604469257 181441029 -1222526076 1854340550 -962287588 1726102591 -888990578 66195117 999139408 708596861

         */
        Random random = new Random();

        // Unbounded stream of random integers
        Stream<Integer> unboundedRandomNumbers = Stream.generate(random::nextInt);

        // Print the first 10 random integers
        String result6 = unboundedRandomNumbers
                .limit(10) // Limit to 10 numbers
                .map(String::valueOf) // Convert each integer to string
                .collect(Collectors.joining(" ")); // Join by space
        System.out.println("\n\n\n11. Unbounded Random Number Generation");
        System.out.println("Output:");
        System.out.println(result6);

        /** 12.
         Bounded Random Number Generation (within range 1 to 100)

         Inputs:
         NA

         Output:
         91 83 26 32 99 29 23 16 79 8

         */
        random = new Random();

        // Bounded stream of random 10 integers (within range 1 to 100)
        IntStream boundedRandomNumbers = random.ints(10, 1, 101);

        // Print the first 10 random integers
        String result7 = boundedRandomNumbers
                // mapToObj() converts a primitive stream into a stream of objects
                .mapToObj(String::valueOf) // Convert each integer to string
                .collect(Collectors.joining(" ")); // Join by space
        System.out.println("\n\n\n12. Bounded Random Number Generation (within range 1 to 100)");
        System.out.println("Output:");
        System.out.println(result7);

        /** 13.
         Random String Generation (Random Characters)

         Inputs:
         NA

         Output:
         91 83 26 32 99 29 23 16 79 8

         */
        Random random1 = new Random();
        int stringLength = 10; // Length of each generated string

        // Random string generation using lowercase letters
        String randomString = IntStream.range(0, stringLength)
                .map(i -> 'a' + random1.nextInt(26)) // Generate random character from 'a' to 'z'
                .mapToObj(c -> String.valueOf((char) c)) // Convert int to character
                .collect(Collectors.joining()); // Join characters to form the string

        System.out.println("\n\n\n13. Random String Generation (Random Characters)");
        System.out.println("Output: " + randomString);

        /** 14.
         Random String Generation from a Given Set of Words

         Inputs:
         {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"}

         Output:
         banana elderberry fig grape grape

         */
        Random random2 = new Random();
        String[] wordsList = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        int wordCount = 5; // Number of words to be included in the generated string

        // Random string generation from a set of words
        String randomWords = Stream.generate(() -> wordsList[random2.nextInt(wordsList.length)]) // Pick a random word
                .limit(wordCount) // Limit to 'wordCount' number of words
                .collect(Collectors.joining(" ")); // Join words with space

        System.out.println("\n\n\n14. Random String Generation from a Given Set of Words");
        System.out.println("Output: " + randomWords);


    }
}
