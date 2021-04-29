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
        value = getBonus(employee.getOccupation());
    }
    
    private double getBonus(String occupation) {
        if (occupation.equals("Diretor")) { return 150; }
        if (occupation.equals("Gerente")) { return 100; }
        if (occupation.equals("Vendedor")) { return 50; }
        if (occupation.equals("Zelador")) { return 20; }
        
        return 0;
    }
    
}
