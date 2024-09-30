package org.demo.utility;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtils {

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static <T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

    public static <K, V> void printEmployeeMapNameIDAndSalary(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (null != entry.getValue()) {
                List<Employee> empList = (List<Employee>) entry.getValue();
                String employeeDetails = empList.stream()
                        .map(Employee::printEmpIdNameSalary)
                        .collect(Collectors.joining(", "));
                System.out.println(entry.getKey() + " : " + employeeDetails);
            }
        }
    }

    public static <K, V> void printEmployeeMapNameIDAndSalary(List<Employee> empList) {
        empList.stream()
                .map(Employee::printEmpIdNameSalary)
                .forEach(System.out::println);
    }

}
