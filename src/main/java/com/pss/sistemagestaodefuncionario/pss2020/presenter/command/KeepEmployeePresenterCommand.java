package com.pss.sistemagestaodefuncionario.pss2020.presenter.command;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public abstract class KeepEmployeePresenterCommand {

    protected Employee employee;
    protected EmployeeCollection employeeCollection;

    public KeepEmployeePresenterCommand(Employee employee, EmployeeCollection employeeCollection) {
        this.employee = employee;
        this.employeeCollection = employeeCollection;
    }

    public abstract void execute() throws Exception;

}
