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
    private KeepEmployeePresenter keepEmployeePresenter;
    private SearchEmployeePresenter searchEmployeePresenter;
    private CalculateWagesPresenter calculateWagesPresenter;

    public MainScreenPresenter() throws Exception {
        view = new MainScreenView();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);

        employeeCollection = EmployeeCollection.getInstance();
        managerLog = new ManagerLog(new JSONLog());

        initPresenters();
        registerAllObservers();
        initListeners();
    }

    private void initPresenters() throws Exception {
        keepEmployeePresenter = new KeepEmployeePresenter(null, employeeCollection);
        searchEmployeePresenter = new SearchEmployeePresenter(employeeCollection, managerLog);
        calculateWagesPresenter = new CalculateWagesPresenter(employeeCollection, managerLog);
    }

    private void initListeners() throws Exception {
        try {
            view.getMniKeepEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.add(keepEmployeePresenter.getView());
                    keepEmployeePresenter.getView().setVisible(true);
                }
            });

            view.getMniSearchEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    view.add(searchEmployeePresenter.getView());
                    searchEmployeePresenter.getView().setVisible(true);
                }
            });

            view.getMniCalculateWages().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    view.add(calculateWagesPresenter.getView());
                    calculateWagesPresenter.getView().setVisible(true);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerAllObservers() {
        employeeCollection.registerObserver(searchEmployeePresenter);
        employeeCollection.registerObserver(calculateWagesPresenter);
    }

    public MainScreenView getView() {
        return view;
    }

}
