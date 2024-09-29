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
import java.util.stream.Collectors;

public class StreamsApiAllInOne {
    public static void main(String[] args) {
        /* 1.
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

        /* 2.
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

        /* 3.
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

        /* 4.
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

        /* 5.
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

    }
}
