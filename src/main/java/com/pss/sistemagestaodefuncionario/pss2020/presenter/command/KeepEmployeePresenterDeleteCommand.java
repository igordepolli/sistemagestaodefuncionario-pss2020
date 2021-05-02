package com.pss.sistemagestaodefuncionario.pss2020.presenter.command;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public class KeepEmployeePresenterDeleteCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterDeleteCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() throws Exception {
        employeeCollection.removeEmployee(employee);
    }
    
}
