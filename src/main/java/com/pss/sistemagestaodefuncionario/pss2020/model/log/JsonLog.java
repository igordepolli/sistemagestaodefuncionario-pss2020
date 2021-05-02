package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.FileWriter;

public class JsonLog extends Log {

    private final FileWriter file;

    public JsonLog(String filePath) throws Exception {
        super(filePath);
        
        file = new FileWriter("jsonlog.json");
    }
     
    @Override
    public void write(Employee employee, String action) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(Employee employee) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(EmployeeCollection employeeCollection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(String errorMessage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
