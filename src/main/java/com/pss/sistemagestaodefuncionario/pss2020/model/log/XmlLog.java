package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.FileNotFoundException;

public class XmlLog implements ILog {

    public XmlLog() throws FileNotFoundException {
    }

    @Override
    public void write(Employee employee, String action) throws Exception {
        
    }

    @Override
    public void write(Employee employee) throws Exception {
    }

    @Override
    public void write(EmployeeCollection employeeCollection) throws Exception {
    }

    @Override
    public void write(String errorMessage) {
    }
    
}
