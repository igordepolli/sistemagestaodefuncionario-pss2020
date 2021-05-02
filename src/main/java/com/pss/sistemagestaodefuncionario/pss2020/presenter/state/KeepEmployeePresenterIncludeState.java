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
        
        presenter.setEmployee(null);
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
            presenter.getLog().write(presenter.getEmployee(), "Incluído");
            presenter.setEmployee(null);
            presenter.cleanFields();
        } catch (Exception ex) {
            try {
                presenter.getLog().write(ex.getMessage());
                JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                presenter.setEmployee(null);
            } catch (Exception ex1) {
                JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Falha ao escrever no log", JOptionPane.ERROR_MESSAGE);
            }
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
        presenter.getView().getBtnSave().setEnabled(true);
        presenter.getView().getBtnEdit().setEnabled(false);
        presenter.getView().getBtnDelete().setEnabled(false);
    }
}
