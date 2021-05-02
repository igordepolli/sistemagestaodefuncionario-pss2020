package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class EmployeeOfTheMonthBonus extends Bonus {

    public EmployeeOfTheMonthBonus(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public void calculate(Employee employee) throws Exception {
        if (employee.isEmployeeOfTheMonth()) {
            value = 500.00;
        }
    }
    
}
