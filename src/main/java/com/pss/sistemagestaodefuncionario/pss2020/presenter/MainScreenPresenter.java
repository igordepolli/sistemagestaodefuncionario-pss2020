package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.XmlLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.state.KeepEmployeePresenterIncludeState;
import com.pss.sistemagestaodefuncionario.pss2020.view.MainScreenView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainScreenPresenter implements IObserver {

    private static MainScreenPresenter instance = null;
    private final MainScreenView view;
    private final EmployeeCollection employeeCollection;
    private KeepEmployeePresenter keepEmployeePresenter;
    private SearchEmployeePresenter searchEmployeePresenter;
    private CalculateSalaryPresenter calculateSalaryPresenter;
    private final ManagerLog log;

    private MainScreenPresenter() throws Exception {
        view = new MainScreenView();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
        log = new ManagerLog(new XmlLog());
        employeeCollection = EmployeeCollection.getInstance();

        initPresenters();
        registerAllObservers();
        initListeners();
        setNumberOfEmployees();
    }

    public static MainScreenPresenter getInstance() throws Exception {
        if (instance == null) {
            instance = new MainScreenPresenter();
        }
        return instance;
    }

    private void initPresenters() throws Exception {
        keepEmployeePresenter = KeepEmployeePresenter.getInstance(employeeCollection, log);
        searchEmployeePresenter = SearchEmployeePresenter.getInstance(employeeCollection, log);
        calculateSalaryPresenter = CalculateSalaryPresenter.getInstance(employeeCollection, log);

        view.add(keepEmployeePresenter.getView());
        view.add(searchEmployeePresenter.getView());
        view.add(calculateSalaryPresenter.getView());

    }

    private void initListeners() throws Exception {
        try {
            view.getMniKeepEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keepEmployeePresenter.setState(new KeepEmployeePresenterIncludeState(keepEmployeePresenter, employeeCollection));
                }
            });

            view.getMniSearchEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    searchEmployeePresenter.getView().setVisible(true);
                }
            });

            view.getMniCalculateWages().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    calculateSalaryPresenter.getView().setVisible(true);
                }
            });

            view.getMniSetupLog().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    new SetupLogPresenter(log);
                }
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setNumberOfEmployees() {
        view.getLblAmountEmployees().setText(String.valueOf(employeeCollection.getEmployees().size()));
    }

    private void registerAllObservers() {
        employeeCollection.registerObserver(searchEmployeePresenter);
        employeeCollection.registerObserver(this);
    }

    public MainScreenView getView() {
        return view;
    }

    @Override
    public void update(List<Employee> employees) {
        view.getLblAmountEmployees().setText(String.valueOf(employees.size()));
    }

}
