package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public class ManagerLog {

    private ILog log;

    public ManagerLog(ILog log) throws Exception {
        this.log = log;
    }

    public void write(Employee employee, String action) throws Exception {
        log.write(employee, action);
    }

    public void write(Employee employee) throws Exception {
        log.write(employee);
    }

    public void write(EmployeeCollection employeeCollection) throws Exception {
        log.write(employeeCollection);
    }

    public void write(String errorMessage) throws Exception {
        log.write(errorMessage);
    }

    public void setLog(ILog log) {
        this.log = log;
    }

}
