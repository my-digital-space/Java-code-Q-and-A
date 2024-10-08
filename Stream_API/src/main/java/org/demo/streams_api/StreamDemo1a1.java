package org.demo.streams_api;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo1a1 {
    // 5: Find Nth Highest Salary Using Java Streams API

    public static void main(String[] args) {
        Map<String, BigDecimal> myEmployeesData = new HashMap<>();
        myEmployeesData.put("EmpName1", BigDecimal.valueOf(200.00)); // 4th Highest
        myEmployeesData.put("EmpName2", BigDecimal.valueOf(400.00)); // 2nd Highest
        myEmployeesData.put("EmpName3", BigDecimal.valueOf(100.00)); // 5th Highest
        myEmployeesData.put("EmpName4", BigDecimal.valueOf(300.00)); // 3rd Highest
        myEmployeesData.put("EmpName5", BigDecimal.valueOf(500.00)); // 1st Highest

        int n = 5;
        Map.Entry<String, BigDecimal> output = getNthHighestSalary(myEmployeesData, n);
        System.out.println(n + "th Highest: " + output);

    }

    public static Map.Entry<String, BigDecimal> getNthHighestSalary(Map<String, BigDecimal> myData,
                                                                    int n) {
        return myData.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList())
                .get(n-1);
    }
}
