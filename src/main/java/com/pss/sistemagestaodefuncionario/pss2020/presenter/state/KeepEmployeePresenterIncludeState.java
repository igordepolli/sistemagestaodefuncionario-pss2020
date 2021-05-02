package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.command.KeepEmployeePresenterIncludeCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterIncludeState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterIncludeState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        super(presenter, employeeCollection);
        
        setView();
        initListeners();
        
        presenter.getView().setVisible(true);
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
            presenter.createNewEmployee();
            presenter.setCommand(new KeepEmployeePresenterIncludeCommand(presenter.getEmployee(), employeeCollection));
            presenter.getCommand().execute();
            JOptionPane.showMessageDialog(presenter.getView(), "Funcionário inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            presenter.cleanFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setView() {
        presenter.cleanFields();
        setFields();
        setButtons();
    }
    
    private void setFields() {
        presenter.getView().getCbxOccupation().setEnabled(true);
        presenter.getView().getTfdName().setEditable(true);
        presenter.getView().getFfdAge().setEditable(true);
        presenter.getView().getCbxBonus().setEnabled(true);
        presenter.getView().getTfdSalary().setEditable(true);
        presenter.getView().getTfdAbsence().setEditable(true);
        presenter.getView().getChbEmployeeOfTheMonth().setEnabled(true);
        presenter.getView().getFfdAdmission().setEditable(true);
    }
    
    private void setButtons() {
        presenter.getView().getBtnSave().setVisible(true);
        presenter.getView().getBtnEdit().setVisible(false);
        presenter.getView().getBtnDelete().setVisible(false);
    }
}
