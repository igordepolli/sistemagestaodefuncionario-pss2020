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
import com.pss.sistemagestaodefuncionario.pss2020.utils.DateManipulation;
import com.pss.sistemagestaodefuncionario.pss2020.view.KeepEmployeeView;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.swing.JButton;

public class KeepEmployeePresenter {

    private final KeepEmployeeView view;
    private KeepEmployeePresenterCommand command;
    private KeepEmployeePresenterState state;
    private Employee employee;
    private EmployeeCollection employeeCollection;

    public KeepEmployeePresenter(Employee employee, EmployeeCollection employeeCollection) throws Exception {
        this.employee = employee;
        this.employeeCollection = employeeCollection;
        view = new KeepEmployeeView();
        view.setLocation(20, 20);
        
        state = defineState();
    }
    
    public void clearListeners() {
        for (Component component : getView().getParent().getComponents()) {
            if (component instanceof JButton) {
                for (ActionListener actionListener : ((JButton) component).getActionListeners()) {
                    ((JButton) component).removeActionListener(actionListener);
                }
            }
        }
    }

    public Employee getTextInFieldsAndCreateEmployee() throws Exception {
        String id = generateRandomId();
        String occupation = String.valueOf(view.getCbxOccupation().getSelectedItem());
        String name = view.getTfdName().getText();
        int age = Integer.parseInt(view.getFfdAge().getText());
        int absences = Integer.parseInt(view.getTfdAbsence().getText());
        boolean employeeOfTheMonth = view.getChbEmployeeOfTheMonth().isSelected();
        LocalDate dateAdmission = DateManipulation.stringToLocalDate(view.getFfdAdmission().getText());
        BonusCollection bonusCollection = addFirstBonusInCollection();
        double salary = getAndConvertSalaryField();

        return new Employee(id, name, age, salary, occupation, bonusCollection, absences, dateAdmission, employeeOfTheMonth);
    }

    private BonusCollection addFirstBonusInCollection() {
        Bonus bonus = getInstanceOfBonus();

        return new BonusCollection(bonus);
    }

    private double getAndConvertSalaryField() {
        BigDecimal wageValueBigDecimal = view.getTfdSalary().getValue();

        return wageValueBigDecimal.doubleValue();
    }

    public void checkIfEmployeeOfTheMonthIsUnique(EmployeeCollection employeeCollection) throws Exception {
        for (Employee employee : employeeCollection.getEmployees()) {
            if (employee.isEmployeeOfTheMonth()) {
                throw new Exception("O funcionário " + employee.getName() + " já está marcado como funcionário do mês!");
            }
        }
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

    public void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("TODOS os campos devem ser preenchidos!");
        }
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString();
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
    
    private KeepEmployeePresenterState defineState() {
        if (employee == null) {
            return new KeepEmployeePresenterIncludeState(this, employeeCollection);
        }
        
        return null;
    }

    public KeepEmployeeView getView() {
        return view;
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
