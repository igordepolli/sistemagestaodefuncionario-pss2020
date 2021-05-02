package com.pss.sistemagestaodefuncionario.pss2020.model;

import java.time.LocalDate;

public class CalculateAllBonusByEmployee {

    private final Employee employee;
    private final LocalDate localDate;
    
    public CalculateAllBonusByEmployee(Employee employee, LocalDate localDate) {
        this.employee = employee;
        this.localDate = localDate;
    }
    
    public void calculate() throws Exception { 
        for (Bonus bonus : employee.getBonusCollection().getListBonus()) {
            bonus.calculate(employee, localDate);
        }
    }
    
}
