package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterViewState;
import com.pss.sistemagestaodefuncionario.pss2020.view.SearchEmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class SearchEmployeePresenter implements IObserver {

    private static SearchEmployeePresenter instance = null;
    private final SearchEmployeeView view;
    private final EmployeeCollection employeeCollection;
    private DefaultTableModel tabelEmployees;

    private SearchEmployeePresenter(EmployeeCollection employeeCollection) throws Exception {
        this.employeeCollection = employeeCollection;

        view = new SearchEmployeeView();
        view.setLocation(20, 350);

        constructTableModel();
        loadEmployees();
        initListeners();
    }

    public static SearchEmployeePresenter getInstance(EmployeeCollection employeeCollection) throws Exception {
        if (instance == null) {
            instance = new SearchEmployeePresenter(employeeCollection);
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
        
        view.getBtnViewBonus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Employee emp = getEmployeeSelected();
                    new BonusHistoryPresenter(emp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getBtnViewEmployee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Employee emp = getEmployeeSelected();
                    KeepEmployeePresenter keepEmployeePresenter = KeepEmployeePresenter.getInstance(employeeCollection);
                    keepEmployeePresenter.setEmployee(emp);
                    keepEmployeePresenter.setState(new KeepEmployeePresenterViewState(keepEmployeePresenter, employeeCollection));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getTblEmployees().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getTblEmployees().getSelectedRow() > -1) {
                    changeView();
                }
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

    private Employee getEmployeeSelected() throws Exception {
        Employee employee = employeeCollection.searchEmployeeById(getIdOfEmployeeSelected());

        return employee;
    }

    private String getIdOfEmployeeSelected() {
        int rowIndex = view.getTblEmployees().getSelectedRow();

        return (String) view.getTblEmployees().getValueAt(rowIndex, 0);
    }

    private boolean checkIfElementWasSelected() {
        return view.getTblEmployees().getSelectedRow() >= 0;
    }

    private void changeView() {
        if (checkIfElementWasSelected()) {
            view.getBtnViewBonus().setEnabled(true);
            view.getBtnViewEmployee().setEnabled(true);
        } else {
            view.getBtnViewBonus().setEnabled(false);
            view.getBtnViewEmployee().setEnabled(false);
        }
    }

    private void loadEmployees() {
        for (Employee employee : employeeCollection.getEmployees()) {
            tabelEmployees.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getOccupation(),
                employee.getBaseSalary()
            });
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
