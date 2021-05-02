package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;

public interface ILog {

    public void write(Employee employee, String action) throws Exception;

    public void write(Employee employee) throws Exception;

    public void write(EmployeeCollection employeeCollection) throws Exception;

    public void write(String errorMessage) throws Exception;

}
