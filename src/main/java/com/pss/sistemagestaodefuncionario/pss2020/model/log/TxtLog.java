package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.FileWriter;

public class TxtLog extends Log {

    private final FileWriter file;

    public TxtLog(String filePath) throws Exception {
        super(filePath);
        
        file = new FileWriter("txtlog.txt");
        file.write(logHistory + "\n");
    }
     
    @Override
    public void write(Employee employee, String action) throws Exception {
        file.write("Funcionário " + employee.getName() + " " + action + "\n");
        file.flush();
    }

    @Override
    public void write(Employee employee) throws Exception {
        file.write("Bônus consultado para o funcionário " + employee.getName() + "\n");
        file.flush();
    }

    @Override
    public void write(EmployeeCollection employeeCollection) throws Exception {
        file.write("Salário calculado para o(s) funcionário(s): [\n");
        for (Employee e : employeeCollection.getEmployees()) {
            file.write(e.getName() + "\n");
        }
        file.write("]\n");
        file.flush();
    }

    @Override
    public void write(String errorMessage) throws Exception {
        file.write(errorMessage + "\n");
        file.flush();
    }
    
}