package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Log {

    protected String logHistory;

    public Log(String filePath) throws FileNotFoundException {
        if (filePath != null) {
            openFile(filePath);
        }
    }

    public abstract void write(Employee employee, String action) throws Exception;

    public abstract void write(Employee employee) throws Exception;

    public abstract void write(EmployeeCollection employeeCollection) throws Exception;

    public abstract void write(String errorMessage) throws Exception;

    private void openFile(String filePath) throws FileNotFoundException {
        try {
            Path path = Paths.get(filePath);
            logHistory = Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(TxtLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
