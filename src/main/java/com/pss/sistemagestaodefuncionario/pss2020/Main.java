package com.pss.sistemagestaodefuncionario.pss2020;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.BonusCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.GenerousBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.NormalBonus;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.MainScreenPresenter;
import java.time.LocalDate;

public class Main {
    
    public static void main(String[] args) {

        try {
            LocalDate date = LocalDate.of(2005, 05, 05);
            Bonus bonus = new GenerousBonus("Generoso");
            Bonus bonus2 = new NormalBonus("Normal");
            BonusCollection bonusCollection = new BonusCollection();
            BonusCollection bonusCollection2 = new BonusCollection();
            bonusCollection.addBonus(bonus);
            bonusCollection2.addBonus(bonus2);
            
            Employee employee = new Employee("Random", "Igor", 25, 2200.00, "Diretor", bonusCollection, 3, date, true);
            Employee employee2 = new Employee("Random2", "Cascão", 22, 2500.00, "Gerente", bonusCollection2, 2, date, false);
            EmployeeCollection employeeCollection = EmployeeCollection.getInstance();
            employeeCollection.addEmployee(employee);
            employeeCollection.addEmployee(employee2);
            
            MainScreenPresenter.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
