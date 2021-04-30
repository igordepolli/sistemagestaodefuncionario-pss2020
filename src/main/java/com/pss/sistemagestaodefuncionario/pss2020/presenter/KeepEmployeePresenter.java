package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.BonusCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.GenerousBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.bonustypes.NormalBonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.utils.DateManipulation;
import com.pss.sistemagestaodefuncionario.pss2020.view.KeepEmployeeView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class KeepEmployeePresenter {

    private final KeepEmployeeView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;

    public KeepEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog managerLog) throws Exception {
        this.employeeCollection = employeeCollection;
        this.managerLog = managerLog;

        view = new KeepEmployeeView();
        view.setLocation(20, 20);

        initListeners();
    }

    private void initListeners() {
        view.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                view.dispose();
            }
        });

        view.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    checkFieldsIsEmpty();
                    addEmployee();
                    JOptionPane.showMessageDialog(view, "Funcionário salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    cleanFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void clearListeners() {
        for (Component component : view.getParent().getComponents()) {
            if (component instanceof JButton) {
                for (ActionListener actionListener : ((JButton) component).getActionListeners()) {
                    ((JButton) component).removeActionListener(actionListener);
                }
            }
        }
    }

    public void addEmployee() throws Exception {
        if (view.getChbEmployeeOfTheMonth().isSelected()) {
            checkIfEmployeeOfTheMonthIsUnique();
        }
        Employee employee = getTextInFieldsAndCreateEmployee();
        employeeCollection.addEmployee(employee);
    }

    private Employee getTextInFieldsAndCreateEmployee() throws Exception {
        String id = generateRandomId();
        String occupation = String.valueOf(view.getCbxOccupation().getSelectedItem());
        String name = view.getTfdName().getText();
        int age = Integer.parseInt(view.getFfdAge().getText());
        int absences = Integer.parseInt(view.getTfdAbsence().getText());
        boolean employeeOfTheMonth = view.getChbEmployeeOfTheMonth().isSelected();
        LocalDate dateAdmission = DateManipulation.stringToLocalDate(view.getFfdAdmission().getText());
        BonusCollection bonusCollection = addBonusInCollection();
        double salary = getAndConvertSalaryField();

        return new Employee(id, name, age, salary, occupation, bonusCollection, absences, dateAdmission, employeeOfTheMonth);
    }

    private BonusCollection addBonusInCollection() {
        Bonus bonus = getInstanceOfBonus();

        return new BonusCollection(bonus);
    }

    private double getAndConvertSalaryField() {
        BigDecimal wageValueBigDecimal = view.getTfdSalary().getValue();

        return wageValueBigDecimal.doubleValue();
    }

    private void checkIfEmployeeOfTheMonthIsUnique() throws Exception {
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

    private void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("TODOS os campos devem ser preenchidos!");
        }
    }

    public void cleanFields() {
        view.getTfdName().setText("");
        view.getTfdSalary().setText("");
        view.getTfdAbsence().setText("");
        view.getFfdAdmission().setText("");
        view.getFfdAge().setText("");
        view.getCbxOccupation().setSelectedIndex(0);
        view.getCbxBonus().setSelectedIndex(0);
        view.getChbEmployeeOfTheMonth().setSelected(false);
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

    public KeepEmployeeView getView() {
        return view;
    }

}
