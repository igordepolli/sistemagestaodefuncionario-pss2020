package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.view.CalculateWagesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateWagesPresenter {
    
    private final CalculateWagesView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;
    
    public CalculateWagesPresenter(EmployeeCollection employeeCollection, ManagerLog managerLog) {
        this.employeeCollection = employeeCollection;
        this.managerLog = managerLog;
        
        view = new CalculateWagesView();
        view.setLocation(800, 20);
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
    }

    public CalculateWagesView getView() {
        return view;
    }

}
