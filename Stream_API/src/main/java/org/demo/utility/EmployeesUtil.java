package org.demo.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeesUtil {

    public static List<Employee> getEmployeeList() {
        return Arrays.asList(
                new Employee(
                        1, "Name 1", "HR Department", BigDecimal.valueOf(100.00), 30),
                new Employee(
                        2, "Name 2", "HR Department", BigDecimal.valueOf(300.00), 26),
                new Employee(
                        3, "Name 3", "IT Department", BigDecimal.valueOf(600.00), 45),
                new Employee(
                        4, "Name 4", "HR Department", BigDecimal.valueOf(200.00), 55),
                new Employee(
                        5, "Name 5", "IT Department", BigDecimal.valueOf(400.00), 60)
        );
    }

    public static List<Employee> getEmployeeList1() {
        Employee emp1 = new Employee(1, "EmpName1", "Dept1", BigDecimal.valueOf(200.00), 50); // 4th Highest
        Employee emp2 = new Employee(2, "EmpName2", "Dept2", BigDecimal.valueOf(400.00), 45); // 2nd Highest
        Employee emp3 = new Employee(3, "EmpName3", "Dept3a", BigDecimal.valueOf(100.00), 25); // 5th Highest
        Employee emp4 = new Employee(4, "EmpName4", "Dept3b", BigDecimal.valueOf(100.00), 30); // 5th Highest
        Employee emp5 = new Employee(5, "EmpName5", "Dept3c", BigDecimal.valueOf(100.00), 46); // 5th Highest
        Employee emp6 = new Employee(6, "EmpName6", "Dept4", BigDecimal.valueOf(300.00), 45); // 3rd Highest
        Employee emp7 = new Employee(7, "EmpName7", "Dept5", BigDecimal.valueOf(500.00), 50); // 1st Highest

        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);
        empList.add(emp5);
        empList.add(emp6);
        empList.add(emp7);

        return empList;
    }

    public static List<Employee> getEmployeeList2() {
        return Arrays.asList(
                new Employee(
                        1, "Name 1", "HR Department", BigDecimal.valueOf(300.00), 30),
                new Employee(
                        2, "Name 1", "HR Department", BigDecimal.valueOf(400.00), 26),
                new Employee(
                        3, "Name 3", "IT Department", BigDecimal.valueOf(600.00), 45),
                new Employee(
                        4, "Name 4", "HR Department", BigDecimal.valueOf(800.00), 55),
                new Employee(
                        5, "Name 4", "IT Department", BigDecimal.valueOf(400.00), 60),
                new Employee(
                        6, "Name 4", "IT Department", BigDecimal.valueOf(900.00), 60),
                new Employee(
                        7, "Name 5", "IT Department", BigDecimal.valueOf(400.00), 60)
        );
    }

}
