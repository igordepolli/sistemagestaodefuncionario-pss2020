package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.time.LocalDate;

public abstract class Bonus {
    
    protected String description;
    protected double value;
    protected LocalDate date;

    public Bonus(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }
    
    public abstract void calculate(Employee employee) throws Exception;

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

}
