package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.view.CalculateSalaryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculateSalaryPresenter implements IObserver {

    private static CalculateSalaryPresenter instance = null;
    private final CalculateSalaryView view;
    private final EmployeeCollection employeeCollection;

    private CalculateSalaryPresenter(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;

        view = new CalculateSalaryView();
        view.setLocation(800, 20);

        initListeners();
    }
    
    public static CalculateSalaryPresenter getInstance(EmployeeCollection employeeCollection) {
        if (instance == null) {
            instance = new CalculateSalaryPresenter(employeeCollection);
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
    }

    @Override
    public void update(List<Employee> employees) {
    }

    public CalculateSalaryView getView() {
        return view;
    }

}
