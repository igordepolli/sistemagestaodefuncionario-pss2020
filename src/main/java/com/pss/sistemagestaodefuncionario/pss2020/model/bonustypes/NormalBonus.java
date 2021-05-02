package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class NormalBonus extends Bonus {

    public NormalBonus(String description) {
        super(description);
    }

    @Override
    public void calculate(Employee employee, LocalDate localDate) throws Exception {
        date = localDate;
        value = 100.00;
    }

}
