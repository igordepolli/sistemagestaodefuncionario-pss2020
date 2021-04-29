package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class GenerousBonus extends Bonus {

    public GenerousBonus(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public void calculate(Employee employee) throws Exception {
        value = 500.00;
    }
    
}
