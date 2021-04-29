package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class OccupationBonus extends Bonus {

    public OccupationBonus(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public void calculate(Employee employee) throws Exception {
        switch (employee.getOccupation()) {
            case "Diretor":
                value = 150;
                break;
            case "Gerente":
                value = 100;
                break;
            case "Vendedor":
                value = 50;
                break;
            case "Zelador":
                value = 20;
                break;
            default:
                break;
        }
    }
    
}
