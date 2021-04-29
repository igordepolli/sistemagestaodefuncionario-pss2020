package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    
    private Integer id;
    private String name;
    private int age;
    private double wage;
    private String occupation;
    private BonusCollection bonusCollection;
    private int numberOfAbsence;
    private LocalDate admissionDate;
    private boolean employeeOfTheMonth;

    public Employee() {
    }
    
    public int getYearsOfService() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(admissionDate, dateNow);
        return period.getYears();
    }

    public double getWage() {
        return wage;
    }

    public int getNumberOfAbsence() {
        return numberOfAbsence;
    }

    public boolean isEmployeeOfTheMonth() {
        return employeeOfTheMonth;
    }

    public Integer getId() {
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
    
}
