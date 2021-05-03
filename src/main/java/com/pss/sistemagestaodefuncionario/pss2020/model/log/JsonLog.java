package com.pss.sistemagestaodefuncionario.pss2020.model.log;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonLog implements ILog {

    private final FileWriter file;

    public JsonLog() throws Exception {
        file = new FileWriter("jsonlog.json");
    }

    @Override
    public void write(Employee employee, String action) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Funcionário", employee.getName());
        obj.put("Ação", action);

        file.write(obj.toString());
        file.write("\n");
        file.flush();
    }

    @Override
    public void write(Employee employee) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Bônus consultado para o funcionário", employee.getName());

        file.write(obj.toString());
        file.write("\n");
        file.flush();
    }

    @Override
    public void write(EmployeeCollection employeeCollection) throws Exception {
        JSONArray array = new JSONArray();
        for (Employee employee : employeeCollection.getEmployees()) {
            array.put(employee.getName());
        }
        JSONObject obj = new JSONObject();
        obj.put("Salário calculado para o(s) funcionário(s)", array);
        file.write(obj.toString());
        file.write("\n");
        file.flush();
    }

    @Override
    public void write(String errorMessage) throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Error:", errorMessage);
        file.write(obj.toString());
        file.write("\n");
        file.flush();
    }

}
