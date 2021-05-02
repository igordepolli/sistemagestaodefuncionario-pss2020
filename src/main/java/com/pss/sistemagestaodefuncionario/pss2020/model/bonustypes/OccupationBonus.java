package com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import java.time.LocalDate;

public class OccupationBonus extends Bonus {

    public OccupationBonus(String description) {
        super(description);
    }

    @Override
    public void calculate(Employee employee, LocalDate localDate) throws Exception {
        date = localDate;
        value = getBonus(employee.getOccupation());
    }
    
    private double getBonus(String occupation) throws Exception {
        if (occupation.equals("Diretor")) { return 150; }
        if (occupation.equals("Gerente")) { return 100; }
        if (occupation.equals("Vendedor")) { return 50; }
        if (occupation.equals("Zelador")) { return 20; }
        
        throw new Exception("Não há cargo definido!");
    }
    
}
