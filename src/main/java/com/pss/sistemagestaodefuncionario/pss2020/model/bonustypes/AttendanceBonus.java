package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class AttendanceBonus extends Bonus {

    public AttendanceBonus(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public void calculate(Employee employee) throws Exception {
        double bonusPercentage = getBonusPercentage(employee.getNumberOfAbsence());
        value = employee.getSalary() + (employee.getSalary() * bonusPercentage);
    }
    
    private double getBonusPercentage(int numberOfAbsence) {
        if (numberOfAbsence == 0) { return 0.10; }
        if (numberOfAbsence >= 1 && numberOfAbsence <= 3) { return 0.05; }
        if (numberOfAbsence >= 4) { return 0.01; }
        
        return 0;
    }

}
