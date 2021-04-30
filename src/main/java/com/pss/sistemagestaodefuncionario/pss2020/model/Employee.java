package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    
    private String id;
    private String name;
    private int age;
    private double baseSalary;
    private double salary;
    private String occupation;
    private BonusCollection bonusCollection;
    private int numberOfAbsence;
    private LocalDate admissionDate;
    private boolean employeeOfTheMonth;

    public Employee() {
    }

    public Employee(String name, int age, double salary, String occupation, BonusCollection bonusCollection, int numberOfAbsence, LocalDate admissionDate, boolean employeeOfTheMonth) {
        this.name = name;
        this.age = age;
        this.baseSalary = salary;
        this.salary = salary;
        this.occupation = occupation;
        this.bonusCollection = bonusCollection;
        this.numberOfAbsence = numberOfAbsence;
        this.admissionDate = admissionDate;
        this.employeeOfTheMonth = employeeOfTheMonth;
    }

    public Employee(String id, String name, int age, double salary, String occupation, BonusCollection bonusCollection, int numberOfAbsence, LocalDate admissionDate, boolean employeeOfTheMonth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.baseSalary = salary;
        this.salary = salary;
        this.occupation = occupation;
        this.bonusCollection = bonusCollection;
        this.numberOfAbsence = numberOfAbsence;
        this.admissionDate = admissionDate;
        this.employeeOfTheMonth = employeeOfTheMonth;
    }
    
    public int getYearsOfService() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(admissionDate, dateNow);
        return period.getYears();
    }

    public double getSalary() {
        return salary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
    
    public int getNumberOfAbsence() {
        return numberOfAbsence;
    }

    public boolean isEmployeeOfTheMonth() {
        return employeeOfTheMonth;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public BonusCollection getBonusCollection() {
        return bonusCollection;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setBonusCollection(BonusCollection bonusCollection) {
        this.bonusCollection = bonusCollection;
    }

    public void setNumberOfAbsence(int numberOfAbsence) {
        this.numberOfAbsence = numberOfAbsence;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setEmployeeOfTheMonth(boolean employeeOfTheMonth) {
        this.employeeOfTheMonth = employeeOfTheMonth;
    }
    
}
