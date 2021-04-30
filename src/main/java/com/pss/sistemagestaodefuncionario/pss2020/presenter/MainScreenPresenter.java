package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.JSONLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.logs.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.view.MainScreenView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainScreenPresenter {
    
    private final MainScreenView view;
    private final EmployeeCollection employeeCollection;
    private final ManagerLog managerLog;
    
    public MainScreenPresenter() throws Exception {
        view = new MainScreenView();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
        
        employeeCollection = EmployeeCollection.getInstance();
        managerLog = new ManagerLog(new JSONLog());
        
        initListeners();
    }
    
    private void initListeners() {
        view.getMniKeepEmployee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    KeepEmployeePresenter keepEmployeePresenter = new KeepEmployeePresenter(employeeCollection, managerLog);
                    view.add(keepEmployeePresenter.getView());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        view.getMniSearchEmployee().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    SearchEmployeePresenter searchEmployeePresenter = new SearchEmployeePresenter(employeeCollection, managerLog);
                    view.add(searchEmployeePresenter.getView());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        view.getMniCalculateWages().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    CalculateWagesPresenter calculateWagesPresenter = new CalculateWagesPresenter(employeeCollection, managerLog);
                    view.add(calculateWagesPresenter.getView());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public MainScreenView getView() {
        return view;
    }
    
}
