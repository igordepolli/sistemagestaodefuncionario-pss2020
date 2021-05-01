package com.pss.sistemagestaodefuncionario.pss2020.model;

public class CalculateAllBonusByEmployee {

    private final Employee employee;
    private double totalValue;

    public CalculateAllBonusByEmployee(Employee employee) {
        this.employee = employee;
        totalValue = 0;
    }
    
    public void calculate() throws Exception {
        for (Bonus bonus : employee.getBonusCollection().getListBonus()) {
            bonus.calculate(employee);
            totalValue += bonus.getValue();
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getTotalValue() {
        return totalValue;
    }
    
}
