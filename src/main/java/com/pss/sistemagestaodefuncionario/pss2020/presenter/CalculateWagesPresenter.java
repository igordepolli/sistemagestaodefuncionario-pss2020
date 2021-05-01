package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.observer.IObserver;
import com.pss.sistemagestaodefuncionario.pss2020.view.CalculateWagesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculateWagesPresenter implements IObserver {

    private static CalculateWagesPresenter instance = null;
    private final CalculateWagesView view;
    private final EmployeeCollection employeeCollection;

    private CalculateWagesPresenter(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;

        view = new CalculateWagesView();
        view.setLocation(800, 20);

        initListeners();
    }
    
    public static CalculateWagesPresenter getInstance(EmployeeCollection employeeCollection) {
        if (instance == null) {
            instance = new CalculateWagesPresenter(employeeCollection);
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

    public CalculateWagesView getView() {
        return view;
    }

}
