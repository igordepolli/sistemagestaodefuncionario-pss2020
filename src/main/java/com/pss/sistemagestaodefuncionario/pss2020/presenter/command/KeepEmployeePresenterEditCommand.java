package com.pss.sistemagestaodefuncionario.pss2020.presenter.command;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public class KeepEmployeePresenterEditCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterEditCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() throws Exception {
        employeeCollection.updateEmployee(employee);
    }

}
