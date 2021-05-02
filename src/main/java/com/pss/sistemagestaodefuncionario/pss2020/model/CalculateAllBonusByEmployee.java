package com.pss.sistemagestaodefuncionario.pss2020.model;

import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.AttendanceBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.EmployeeOfTheMonthBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.OccupationBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.ServiceTimeBonus;
import java.time.LocalDate;

public class CalculateAllBonusByEmployee {

    private final Employee employee;
    private final LocalDate localDate;
    
    public CalculateAllBonusByEmployee(Employee employee, LocalDate localDate) {
        this.employee = employee;
        this.localDate = localDate;
    }
    
    public void calculate() throws Exception {
        employee.getBonusCollection().addBonus(new AttendanceBonus("Assiduidade"));
        employee.getBonusCollection().addBonus(new EmployeeOfTheMonthBonus("Funcionário do Mês"));
        employee.getBonusCollection().addBonus(new OccupationBonus("Cargo"));
        employee.getBonusCollection().addBonus(new ServiceTimeBonus("Tempo de serviço"));
        
        for (Bonus bonus : employee.getBonusCollection().getListBonus()) {
            bonus.calculate(employee, localDate);
        }
        
    }
    
}
