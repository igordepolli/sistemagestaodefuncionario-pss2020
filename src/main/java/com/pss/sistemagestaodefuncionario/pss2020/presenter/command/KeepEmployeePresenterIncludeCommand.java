package com.pss.sistemagestaodefuncionario.pss2020.presenter.command;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public class KeepEmployeePresenterIncludeCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterIncludeCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() throws Exception {
        employeeCollection.addEmployee(employee);
    }

}
