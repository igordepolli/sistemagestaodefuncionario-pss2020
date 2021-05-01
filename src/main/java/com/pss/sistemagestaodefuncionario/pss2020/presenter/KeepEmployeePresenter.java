package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.BonusCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.GenerousBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.NormalBonus;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.command.KeepEmployeePresenterCommand;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterIncludeState;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterState;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterViewState;
import com.pss.sistemagestaodefuncionario.pss2020.utils.DateManipulation;
import com.pss.sistemagestaodefuncionario.pss2020.view.KeepEmployeeView;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.swing.JButton;

public class KeepEmployeePresenter {

    private static KeepEmployeePresenter instance = null;
    private final KeepEmployeeView view;
    private KeepEmployeePresenterCommand command;
    private KeepEmployeePresenterState state;
    private Employee employee;
    private EmployeeCollection employeeCollection;

    private KeepEmployeePresenter(EmployeeCollection employeeCollection) throws Exception {
        this.employee = null;
        this.employeeCollection = employeeCollection;
        view = new KeepEmployeeView();
        view.setLocation(20, 20);

        defineState();
    }

    public static KeepEmployeePresenter getInstance(EmployeeCollection employeeCollection) throws Exception {
        if (instance == null) {
            instance = new KeepEmployeePresenter(employeeCollection);
        }

        return instance;
    }

    private BonusCollection addFirstBonusInCollection() {
        Bonus bonus = getInstanceOfBonus();

        return new BonusCollection(bonus);
    }

    private void defineState() throws Exception {
        if (employee == null) {
            state = new KeepEmployeePresenterIncludeState(this, employeeCollection);
        } else {
            state = new KeepEmployeePresenterViewState(this, employeeCollection);
        }
    }

    public void cleanListeners() {
        for (Component component : getView().getComponents()) {
            if (component instanceof JButton) {
                for (ActionListener actionListener : ((JButton) component).getActionListeners()) {
                    ((JButton) component).removeActionListener(actionListener);
                }
            }
        }
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

    public void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("TODOS os campos devem ser preenchidos!");
        }
    }

    public void checkIfEmployeeOfTheMonthIsUnique(EmployeeCollection employeeCollection) throws Exception {
        for (Employee employee : employeeCollection.getEmployees()) {
            if (employee.isEmployeeOfTheMonth()) {
                throw new Exception("O funcionário " + employee.getName() + " já está marcado como funcionário do mês!");
            }
        }
    }

    public void createNewEmployee() throws Exception {
        if (employee != null) {
            throw new Exception("Não é possível criar um novo usuário!");
        }

        employee = new Employee();
        employee.setId(generateRandomId());
        getTextInFieldsAndSetEmployee();
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
        int indexItem = view.getCbxOccupation().getSelectedIndex();

        switch (indexItem) {
            case 0:
                return new NormalBonus("Bônus Normal", LocalDate.now());
            case 1:
                return new GenerousBonus("Bônus Generoso", LocalDate.now());
        }

        return null;
    }

    public void getTextInFieldsAndSetEmployee() throws Exception {

        employee.setOccupation(String.valueOf(view.getCbxOccupation().getSelectedItem()));
        employee.setName(view.getTfdName().getText());
        employee.setAge(Integer.parseInt(view.getFfdAge().getText()));
        employee.setBonusCollection(addFirstBonusInCollection());
        employee.setSalary(getAndConvertSalaryField());
        employee.setNumberOfAbsence(Integer.parseInt(view.getTfdAbsence().getText()));
        employee.setEmployeeOfTheMonth(view.getChbEmployeeOfTheMonth().isSelected());
        employee.setAdmissionDate(DateManipulation.stringToLocalDate(view.getFfdAdmission().getText()));

    }

    public void loadFields() throws Exception {

        view.getCbxOccupation().setSelectedItem(employee.getOccupation());
        view.getTfdName().setText(employee.getName());
        view.getFfdAge().setText(String.valueOf(employee.getAge()));
        view.getCbxBonus().setSelectedItem(employee.getBonusCollection().getListBonus().get(0));
        view.getTfdSalary().setValue(new BigDecimal(employee.getSalary()));
        view.getTfdAbsence().setText(String.valueOf(employee.getNumberOfAbsence()));
        view.getChbEmployeeOfTheMonth().setSelected(employee.isEmployeeOfTheMonth());
        view.getFfdAdmission().setText(DateManipulation.localDateToString(employee.getAdmissionDate()));

    }

    public KeepEmployeeView getView() {
        return view;
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
