package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.view.KeepEmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public KeepEmployeeView getView() {
        return view;
    }
    
}
