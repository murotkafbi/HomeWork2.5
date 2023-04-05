package com.example.home.service;

import com.example.home.exception.EmployeeAlreedyAddedException;
import com.example.home.exception.EmployeeNotFoundException;
import com.example.home.exception.EmployeeStorageIsFullException;
import com.example.home.model.Employee;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {
    private static final int SIZE = 10;
    private final Employee[] employees = new Employee[SIZE];

    public Employee add(String firstName, String lastName) {
        int indexForAdding = -1;
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null && indexForAdding == -1) {
                indexForAdding = i;
                continue;
            }
            if (employees[i].equals(employee)) {
                throw new EmployeeAlreedyAddedException();
            }
        }

        if (indexForAdding != -1) {
            employees[indexForAdding] = employee;
            return employees[indexForAdding];
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (Employee emp : employees) {
            if (employee.equals(emp)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i= 0; i< employees.length;i++) {
            if (employee.equals(employees[i])) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException();

    }
}