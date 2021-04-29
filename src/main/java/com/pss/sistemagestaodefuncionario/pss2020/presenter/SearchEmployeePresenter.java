package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.view.SearchEmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchEmployeePresenter {

    private final SearchEmployeeView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;

    public SearchEmployeePresenter(EmployeeCollection employeeCollection, ManagerLog managerLog) throws Exception {
        this.employeeCollection = employeeCollection;
        this.managerLog = managerLog;

        view = new SearchEmployeeView();
        view.setLocation(20, 350);
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

    public SearchEmployeeView getView() {
        return view;
    }

}
