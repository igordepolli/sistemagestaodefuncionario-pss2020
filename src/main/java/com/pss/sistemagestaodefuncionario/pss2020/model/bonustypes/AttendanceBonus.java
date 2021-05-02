package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class AttendanceBonus extends Bonus {

    public AttendanceBonus(String description) {
        super(description);
    }

    @Override
    public void calculate(Employee employee, LocalDate localDate) throws Exception {
        date = localDate;
        double bonusPercentage = getBonusPercentage(employee.getNumberOfAbsence());
        value = employee.getBaseSalary() * bonusPercentage;
    }

    private double getBonusPercentage(int numberOfAbsence) throws Exception {
        if (numberOfAbsence == 0) {
            return 0.10;
        }
        if (numberOfAbsence >= 1 && numberOfAbsence <= 3) {
            return 0.05;
        }
        if (numberOfAbsence >= 4) {
            return 0.01;
        }

        throw new Exception("Não é possível realizar o cálculo com número de faltas negativo!");
    }

}
