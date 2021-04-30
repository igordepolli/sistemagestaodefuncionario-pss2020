package com.pss.sistemagestaodefuncionario.pss2020.model;

import com.pss.sistemagestaodefuncionario.pss2020.model.observer.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeCollection extends Subject {
    
    private static EmployeeCollection instance = null;
    private final List<Employee> employees;

    private EmployeeCollection() {
        observers = new ArrayList<>();
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
        notifyObservers();
    }
    
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        notifyObservers();
    }
    
    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    protected void notifyObservers() {
        observers.forEach(observer -> {
            observer.update(Collections.unmodifiableList(employees));
        });
    }
    
}
