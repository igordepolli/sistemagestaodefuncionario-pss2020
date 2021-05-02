package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.GenerousBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.NormalBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.command.KeepEmployeePresenterCommand;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterState;
import com.pss.sistemagestaodefuncionario.pss2020.utils.DateManipulation;
import com.pss.sistemagestaodefuncionario.pss2020.view.KeepEmployeeView;
import java.math.BigDecimal;
import java.util.UUID;

public class KeepEmployeePresenter {

    private static KeepEmployeePresenter instance = null;
    private final KeepEmployeeView view;
    private KeepEmployeePresenterCommand command;
    private KeepEmployeePresenterState state;
    private Employee employee;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog log;

    private KeepEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog log) throws Exception {
        this.employee = null;
        this.employeeCollection = employeeCollection;
        this.log = log;
        
        view = new KeepEmployeeView();
        view.setLocation(20, 20);

    }

    public static KeepEmployeePresenter getInstance(EmployeeCollection employeeCollection, ManagerLog log) throws Exception {
        if (instance == null) {
            instance = new KeepEmployeePresenter(employeeCollection, log);
        }

        return instance;
    }

    public void cleanFields() {
        getView().getCbxOccupation().setSelectedIndex(-1);
        getView().getTfdName().setText("");
        getView().getFfdAge().setText("");
        getView().getCbxBonus().setSelectedIndex(-1);
        getView().getTfdSalary().setText("");
        getView().getTfdAbsence().setText("");
        getView().getChbEmployeeOfTheMonth().setSelected(false);
        getView().getFfdAdmission().setText("");
    }

    private void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("TODOS os campos devem ser preenchidos!");
        }
    }

    private boolean checkIfEmployeeOfTheMonthIsUnique() throws Exception {
        for (Employee emp : employeeCollection.getEmployees()) {
            if (emp.isEmployeeOfTheMonth()) {
                throw new Exception("O funcionário " + emp.getName() + " já está marcado como funcionário do mês!");
            }
        }
        return true;
    }

    public void createNewEmployee() throws Exception {
        if (employee != null) {
            throw new Exception("Não é possível criar um novo usuário!");
        }

        checkFieldsIsEmpty();
        
        employee = new Employee();
        
        employee.setId(generateRandomId());
        employee.setOccupation(String.valueOf(view.getCbxOccupation().getSelectedItem()));
        employee.setName(view.getTfdName().getText());
        employee.setAge(Integer.parseInt(view.getFfdAge().getText()));
        employee.getBonusCollection().addBonus(getInstanceOfBonus());
        employee.setBaseSalary(getAndConvertSalaryField());
        employee.setSalary(getAndConvertSalaryField());
        employee.setNumberOfAbsence(Integer.parseInt(view.getTfdAbsence().getText()));
        employee.setAdmissionDate(DateManipulation.stringToLocalDate(view.getFfdAdmission().getText()));
        
        verifyEmployeeOfTheMonthCondition();
    }

    private boolean fieldsIsEmpty() {
        return view.getFfdAdmission().getText().replaceAll("\\s", "").equals("//")
                || view.getTfdName().getText().equals("")
                || view.getTfdSalary().getText().equals("")
                || view.getTfdAbsence().getText().equals("")
                || view.getFfdAdmission().getText().equals("")
                || view.getFfdAge().getText().equals("")
                || view.getCbxOccupation().getSelectedIndex() == -1
                || view.getCbxBonus().getSelectedIndex() == -1;
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    private double getAndConvertSalaryField() {
        BigDecimal wageValueBigDecimal = view.getTfdSalary().getValue();

        return wageValueBigDecimal.doubleValue();
    }

    private Bonus getInstanceOfBonus() {
        String valueItem = String.valueOf(view.getCbxBonus().getSelectedItem());

        switch (valueItem) {
            case "Normal":
                return new NormalBonus("Normal");
            case "Generoso":
                return new GenerousBonus("Generoso");
        }

        return null;
    }

    public void getTextInFieldsAndSetEmployee() throws Exception {
        checkFieldsIsEmpty();

        employee.setOccupation(String.valueOf(view.getCbxOccupation().getSelectedItem()));
        employee.setName(view.getTfdName().getText());
        employee.setAge(Integer.parseInt(view.getFfdAge().getText()));
        employee.getBonusCollection().getListBonus().set(0, getInstanceOfBonus());
        employee.setSalary(getAndConvertSalaryField());
        employee.setNumberOfAbsence(Integer.parseInt(view.getTfdAbsence().getText()));
        employee.setAdmissionDate(DateManipulation.stringToLocalDate(view.getFfdAdmission().getText()));

        verifyEmployeeOfTheMonthCondition();
    }
    
    public void verifyEmployeeOfTheMonthCondition() throws Exception {
        if (view.getChbEmployeeOfTheMonth().isSelected()) {
            if (checkIfEmployeeOfTheMonthIsUnique()) {
                employee.setEmployeeOfTheMonth(true);
            }
        } else {
            employee.setEmployeeOfTheMonth(false);
        }
    }

    public void loadFields() throws Exception {

        view.getCbxOccupation().setSelectedItem(employee.getOccupation());
        view.getTfdName().setText(employee.getName());
        view.getFfdAge().setText(String.valueOf(employee.getAge()));
        view.getCbxBonus().setSelectedItem(employee.getBonusCollection().getListBonus().get(0).getDescription());
        view.getTfdSalary().setValue(new BigDecimal(employee.getSalary()));
        view.getTfdAbsence().setText(String.valueOf(employee.getNumberOfAbsence()));
        view.getChbEmployeeOfTheMonth().setSelected(employee.isEmployeeOfTheMonth());
        view.getFfdAdmission().setText(DateManipulation.localDateToString(employee.getAdmissionDate()));

    }

    public KeepEmployeeView getView() {
        return view;
    }

    public ManagerLog getLog() {
        return log;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public KeepEmployeePresenterCommand getCommand() {
        return command;
    }

    public void setCommand(KeepEmployeePresenterCommand command) {
        this.command = command;
    }

    public KeepEmployeePresenterState getState() {
        return state;
    }

    public void setState(KeepEmployeePresenterState state) {
        this.state = state;
    }

}
