package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterIncludeState;
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
    private DefaultTableModel tableEmployees;
    private final ManagerLog log;

    private SearchEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog log) throws Exception {
        this.employeeCollection = employeeCollection;
        this.log = log;

        view = new SearchEmployeeView();
        view.setLocation(20, 350);

        constructTableModel();
        loadEmployees();
        initListeners();
    }

    public static SearchEmployeePresenter getInstance(EmployeeCollection employeeCollection, ManagerLog log) throws Exception {
        if (instance == null) {
            instance = new SearchEmployeePresenter(employeeCollection, log);
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
                    defineTableBehavior(view.getTfdName().getText());
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

        view.getBtnViewBonus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Employee emp = getEmployeeSelected();
                    new BonusHistoryPresenter(emp);
                    log.write(emp);
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

        view.getBtnViewEmployee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Employee emp = getEmployeeSelected();
                    KeepEmployeePresenter keepEmployeePresenter = KeepEmployeePresenter.getInstance(employeeCollection, log);
                    keepEmployeePresenter.setEmployee(emp);
                    keepEmployeePresenter.setState(new KeepEmployeePresenterViewState(keepEmployeePresenter, employeeCollection));
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

        view.getBtnNewEmployee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    KeepEmployeePresenter keepEmployeePresenter = KeepEmployeePresenter.getInstance(employeeCollection, log);
                    keepEmployeePresenter.setState(new KeepEmployeePresenterIncludeState(keepEmployeePresenter, employeeCollection));
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
        tableEmployees = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário base (R$)"}
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

    private void defineTableBehavior(String textInNameTextField) throws Exception {
        if (textInNameTextField.equals("")) {
            loadEmployees();
        } else {
            searchEmployee(textInNameTextField);
        }
    }

    private void searchEmployee(String name) throws Exception {
        Employee emp = employeeCollection.searchEmployeeByName(name);

        clearTable();
        tableEmployees.addRow(new Object[]{
            emp.getId(),
            emp.getName(),
            emp.getAge(),
            emp.getOccupation(),
            emp.getBaseSalary()
        });

    }

    private void loadEmployees() {
        clearTable();

        for (Employee employee : employeeCollection.getEmployees()) {
            tableEmployees.addRow(new Object[]{
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
            tableEmployees.addRow(new Object[]{
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
