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
        if (employee.getNumberOfAbsence() == 0) {
            value = employee.getWage() + (employee.getWage() * 0.15);
        } else if (employee.getNumberOfAbsence() >= 1 && employee.getNumberOfAbsence() <= 3) {
            value = employee.getWage() + (employee.getWage() * 0.1);
        } else {
            value = employee.getWage() + (employee.getWage() * 0.05);
        }
    }

}
