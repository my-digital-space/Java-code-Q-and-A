package org.demo.streams_api;

import org.demo.utility.Employee;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo1b1 {
    // 5: Find Nth Highest Salary Using Java Streams API

    public static void main(String[] args) {
        Employee emp1 = new Employee(1, "EmpName1", "Dept1", BigDecimal.valueOf(200.00)); // 4th Highest
        Employee emp2 = new Employee(2, "EmpName2", "Dept2", BigDecimal.valueOf(400.00)); // 2nd Highest
        Employee emp3 = new Employee(3, "EmpName3", "Dept3", BigDecimal.valueOf(100.00)); // 5th Highest
        Employee emp4 = new Employee(4, "EmpName4", "Dept4", BigDecimal.valueOf(300.00)); // 3rd Highest
        Employee emp5 = new Employee(5, "EmpName5", "Dept5", BigDecimal.valueOf(500.00)); // 1st Highest

        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);
        empList.add(emp5);

        int n = 3;
        Employee output = getNthHighestSalary(empList, n);
        System.out.println(n + "th Highest: " + output);

    }

    public static Employee getNthHighestSalary(List<Employee> myData, int n) {
        return myData.stream()
                .sorted(Comparator.comparing(Employee::getEmpSalary).reversed())
                .collect(Collectors.toList())
                .get(n - 1);
    }
}
