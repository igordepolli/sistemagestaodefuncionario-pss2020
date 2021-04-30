package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class ServiceTimeBonus extends Bonus {

    public ServiceTimeBonus(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public void calculate(Employee employee) throws Exception {
        double bonusPercentage = getBonusPercentage(employee.getYearsOfService());
        value = employee.getSalary() + (employee.getSalary() * bonusPercentage);
    }
    
    private double getBonusPercentage(int yearsOfService) {
        if (yearsOfService >= 20) { return 0.15; }
        if (yearsOfService >= 16) { return 0.1; }
        if (yearsOfService >= 11) { return 0.08; }
        if (yearsOfService >= 6) { return 0.03; }
        if (yearsOfService >= 1) { return 0.02; }
        
        return 0;
    }
    
}
