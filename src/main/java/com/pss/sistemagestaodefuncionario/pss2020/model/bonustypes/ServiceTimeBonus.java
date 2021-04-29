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
        
        if (employee.getYearsOfService() >= 1 && employee.getYearsOfService() <= 5) {
            value = employee.getWage() + (employee.getWage() * 0.02);
        } else if (employee.getYearsOfService() >= 6 && employee.getYearsOfService() <= 10) {
            value = employee.getWage() + (employee.getWage() * 0.03); 
        } else if (employee.getYearsOfService() >= 11 && employee.getYearsOfService() <= 15) {
            value = employee.getWage() + (employee.getWage() * 0.08); 
        } else if (employee.getYearsOfService() >= 16 && employee.getYearsOfService() <= 20) {
            value = employee.getWage() + (employee.getWage() * 0.1); 
        } else if (employee.getYearsOfService() >= 20) {
            value = employee.getWage() + (employee.getWage() * 0.15); 
        }
        
    }
    
}
