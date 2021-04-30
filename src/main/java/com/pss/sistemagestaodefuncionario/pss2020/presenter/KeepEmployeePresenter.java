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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class KeepEmployeePresenter {

    private final KeepEmployeeView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;

    public KeepEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog managerLog) throws Exception {
        this.employeeCollection = employeeCollection;
        this.managerLog = managerLog;

        view = new KeepEmployeeView();
        view.setLocation(20, 20);
        view.setVisible(true);

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
                // PAREI AQUIIIIIIIIIIIIIIIIIIIIIIIIII
            }
        });
    }

    private void addEmployee() throws Exception {
        checkIfEmployeeOfTheMonthIsUnique();

        String id = generateRandomId();
        String occupation = String.valueOf(view.getCbxOccupation().getSelectedItem());
        String name = view.getTfdName().getText();
        int age = Integer.parseInt(view.getFfdAge().getText());
        int absences = Integer.parseInt(view.getFfdAbsence().getText());
        boolean employeeOfTheMonth = view.getChbEmployeeOfTheMonth().isSelected();
        LocalDate dateAdmission = DateManipulation.stringToLocalDate(view.getFfdAdmission().getText());

        BonusCollection bonusCollection = new BonusCollection();
        Bonus bonus = getInstanceOfBonus();
        bonusCollection.addBonus(bonus);

        BigDecimal wageValueBigDecimal = view.getTfdWage().getValue();
        double wage = wageValueBigDecimal.doubleValue();

        Employee employee = new Employee(id, name, age, wage, occupation, bonusCollection, absences, dateAdmission, employeeOfTheMonth);
        employeeCollection.addEmployee(employee);
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
                || view.getTfdWage().getText().equals("")
                || view.getFfdAbsence().getText().equals("")
                || view.getFfdAdmission().getText().equals("")
                || view.getFfdAge().getText().equals("")
                || view.getCbxOccupation().getSelectedIndex() == -1
                || view.getCbxBonus().getSelectedIndex() == -1;
    }
    
    private void cleanFields() {
        view.getTfdName().setText("");
        view.getTfdWage().setText("");
        view.getFfdAbsence().setText("");
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
