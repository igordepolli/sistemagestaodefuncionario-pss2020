package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.view.SearchEmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class SearchEmployeePresenter implements IObserver {

    private final SearchEmployeeView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;
    private DefaultTableModel tabelEmployees;

    public SearchEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog managerLog) throws Exception {
        this.employeeCollection = employeeCollection;
        this.managerLog = managerLog;

        view = new SearchEmployeeView();
        view.setLocation(20, 350);
        
        constructTableModel();
        initListeners();
    }
    
    private void initListeners() {
        view.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                view.dispose();
            }
        });
    }
    
    private void constructTableModel() {
        tabelEmployees = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário base (R$)"}
        );

        view.getTblEmployees().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelEmployees.setNumRows(0);
        view.getTblEmployees().setModel(tabelEmployees);
    }
    
    private void clearTable() {
        if (tabelEmployees.getRowCount() > 0) {
            for (int i = tabelEmployees.getRowCount() - 1; i > -1; i--) {
                tabelEmployees.removeRow(i);
            }
        }
    }

    @Override
    public void update(List<Employee> employees) {
        clearTable();
        
        for (Employee employee : employees) {
            tabelEmployees.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getOccupation(),
                employee.getBaseSalary()
            });
        }
    }
    
    public SearchEmployeeView getView() {
        return view;
    }
}
