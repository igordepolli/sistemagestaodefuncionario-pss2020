package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.time.LocalDate;

public class BonusHistory {
    
    private String nameEmployee;
    private LocalDate dateBonus;
    private double baseSalaryOfEmployee;
    private String descriptionBonus;
    private double valueOfBonus;
    private double salaryOfEmployee;

    public BonusHistory() {
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public LocalDate getDateBonus() {
        return dateBonus;
    }

    public void setDateBonus(LocalDate dateBonus) {
        this.dateBonus = dateBonus;
    }

    public double getBaseSalaryOfEmployee() {
        return baseSalaryOfEmployee;
    }

    public void setBaseSalaryOfEmployee(double baseSalaryOfEmployee) {
        this.baseSalaryOfEmployee = baseSalaryOfEmployee;
    }

    public String getDescriptionBonus() {
        return descriptionBonus;
    }

    public void setDescriptionBonus(String descriptionBonus) {
        this.descriptionBonus = descriptionBonus;
    }

    public double getValueOfBonus() {
        return valueOfBonus;
    }

    public void setValueOfBonus(double valueOfBonus) {
        this.valueOfBonus = valueOfBonus;
    }

    public double getSalaryOfEmployee() {
        return salaryOfEmployee;
    }

    public void setSalaryOfEmployee(double salaryOfEmployee) {
        this.salaryOfEmployee = salaryOfEmployee;
    }
    
}
