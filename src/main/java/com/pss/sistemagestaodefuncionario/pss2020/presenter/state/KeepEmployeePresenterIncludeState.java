package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.command.KeepEmployeePresenterIncludeCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterIncludeState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterIncludeState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        super(presenter, employeeCollection);
        
        setAllView();
        initListeners();
    }
    
    private void initListeners() {
        presenter.getView().getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                save();
            }
        });
        
        presenter.getView().getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                presenter.getView().dispose();
            }
        });
    }
    
    @Override
    public void save() {
        try {
            presenter.checkFieldsIsEmpty();
            Employee employee = presenter.getTextInFieldsAndCreateEmployee();
            presenter.setCommand(new KeepEmployeePresenterIncludeCommand(employee, employeeCollection));
            presenter.getCommand().execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setAllView() {
        cleanFields();
        setFields();
        setButtons();
    }
    
    private void setFields() {
        presenter.getView().getCbxOccupation().setEditable(true);
        presenter.getView().getTfdName().setEditable(true);
        presenter.getView().getFfdAge().setEditable(true);
        presenter.getView().getCbxBonus().setEditable(true);
        presenter.getView().getTfdSalary().setEditable(true);
        presenter.getView().getTfdAbsence().setEditable(true);
        presenter.getView().getChbEmployeeOfTheMonth().setEnabled(true);
        presenter.getView().getFfdAdmission().setEditable(true);
    }
    
    private void setButtons() {
        presenter.getView().getBtnSave().setEnabled(true);
        presenter.getView().getBtnEdit().setEnabled(false);
        presenter.getView().getBtnDelete().setEnabled(false);
    }
}
