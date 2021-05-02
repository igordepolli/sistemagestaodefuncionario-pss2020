package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.BonusHistory;
import com.pss.sistemagestaodefuncionario.pss2020.model.CalculateAllBonusByEmployee;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.utils.DateManipulation;
import com.pss.sistemagestaodefuncionario.pss2020.view.CalculateSalaryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalculateSalaryPresenter {

    private static CalculateSalaryPresenter instance = null;
    private final CalculateSalaryView view;
    private DefaultTableModel tableEmployees;
    private final EmployeeCollection employeeCollection;
    private final List<BonusHistory> listBonusHistory;
    private final ManagerLog log;

    private CalculateSalaryPresenter(EmployeeCollection employeeCollection, ManagerLog log) {
        this.employeeCollection = employeeCollection;
        this.listBonusHistory = new ArrayList<>();
        this.log = log;
        
        view = new CalculateSalaryView();
        view.setLocation(800, 20);

        constructTableModel();
        initListeners();
    }

    public static CalculateSalaryPresenter getInstance(EmployeeCollection employeeCollection, ManagerLog log) {
        if (instance == null) {
            instance = new CalculateSalaryPresenter(employeeCollection, log);
        }
        return instance;
    }

    private void initListeners() {
        view.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                view.dispose();
            }
        });

        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    checkIfThereIsBonusCalculated();
                    searchBonusByDate();
                } catch (Exception ex) {
                    try {
                        log.write(ex.getMessage());
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Falha ao escrever no log", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        view.getBtnListAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    checkIfThereIsBonusCalculated();
                    loadBonus();
                } catch (Exception ex) {
                    try {
                        log.write(ex.getMessage());
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Falha ao escrever no log", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        view.getBtnCalculate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    calculateAllBonus();
                    log.write(employeeCollection);
                    loadBonus();
                } catch (Exception ex) {
                    try {
                        log.write(ex.getMessage());
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Falha ao escrever no log", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void constructTableModel() {
        tableEmployees = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Funcionário", "Data", "Salário Base (R$)", "Descrição do Bônus", "Bônus (R$)", "Salário (R$)"}
        );

        view.getTblEmployees().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEmployees.setNumRows(0);
        view.getTblEmployees().setModel(tableEmployees);
    }

    private void clearTable() {
        if (tableEmployees.getRowCount() > 0) {
            for (int i = tableEmployees.getRowCount() - 1; i > -1; i--) {
                tableEmployees.removeRow(i);
            }
        }
    }

    private void calculateAllBonus() throws Exception {
        LocalDate localDate = getDateToCalculate();

        for (Employee emp : employeeCollection.getEmployees()) {
            CalculateAllBonusByEmployee calculateAllBonusByEmployee = new CalculateAllBonusByEmployee(emp, localDate);
            calculateAllBonusByEmployee.calculate();
            
            for (Bonus bonus : emp.getBonusCollection().getListBonus()) {
                emp.setSalary(emp.getSalary() + bonus.getValue());

                BonusHistory bonusHistory = new BonusHistory();
                bonusHistory.setNameEmployee(emp.getName());
                bonusHistory.setDateBonus(bonus.getDate());
                bonusHistory.setBaseSalaryOfEmployee(emp.getBaseSalary());
                bonusHistory.setDescriptionBonus(bonus.getDescription());
                bonusHistory.setValueOfBonus(bonus.getValue());
                bonusHistory.setSalaryOfEmployee(emp.getSalary());
                
                listBonusHistory.add(bonusHistory);
            }
        }

    }

    private LocalDate getDateToCalculate() throws Exception {
        if (view.getDchCalculateDate().getDate() == null) {
            throw new Exception("Por favor, entre com uma data válida para realizar o cálculo!");
        }

        return DateManipulation.dateToLocalDate(view.getDchCalculateDate().getDate());
    }

    private LocalDate getDateToSearch() throws Exception {
        if (view.getDchSearchDate().getDate() == null) {
            throw new Exception("Por favor, entre com uma data válida para realizar a busca!");
        }

        return DateManipulation.dateToLocalDate(view.getDchSearchDate().getDate());
    }

    private void searchBonusByDate() throws Exception {
        LocalDate localDate = getDateToSearch();

        clearTable();
        for (BonusHistory bonusHistory : listBonusHistory) {
            if (bonusHistory.getDateBonus().isEqual(localDate)) {
                tableEmployees.addRow(new Object[]{
                    bonusHistory.getNameEmployee(),
                    bonusHistory.getDateBonus(),
                    bonusHistory.getBaseSalaryOfEmployee(),
                    bonusHistory.getDescriptionBonus(),
                    bonusHistory.getValueOfBonus(),
                    bonusHistory.getSalaryOfEmployee()
                });
            }
        }

    }

    private void checkIfThereIsBonusCalculated() throws Exception {
        if (listBonusHistory.isEmpty()) {
            throw new Exception("Não há elementos para serem exibidos!");
        }
    }

    private void loadBonus() throws Exception {

        clearTable();
        for (BonusHistory bonusHistory : listBonusHistory) {
            tableEmployees.addRow(new Object[]{
                bonusHistory.getNameEmployee(),
                bonusHistory.getDateBonus(),
                bonusHistory.getBaseSalaryOfEmployee(),
                bonusHistory.getDescriptionBonus(),
                bonusHistory.getValueOfBonus(),
                bonusHistory.getSalaryOfEmployee()
            });
        }

    }

    public CalculateSalaryView getView() {
        return view;
    }

}
