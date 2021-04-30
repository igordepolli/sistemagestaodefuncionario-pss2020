package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCollection {
    
    private static EmployeeCollection instance = null;
    private final List<Employee> employees;

    private EmployeeCollection() {
        employees = new ArrayList<>();
    }
    
    public static EmployeeCollection getInstance() {
        if (instance == null) {
            instance = new EmployeeCollection();
        }
        return instance;
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    
}
