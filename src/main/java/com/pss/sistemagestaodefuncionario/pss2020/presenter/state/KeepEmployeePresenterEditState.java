package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.command.KeepEmployeePresenterEditCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterEditState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterEditState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        super(presenter, employeeCollection);
        
        setView();
        initListeners();
    }

    private void initListeners() {
        presenter.getView().getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                save();
            }
        });
    }
        
    @Override
    public void save() {
        try {
            presenter.checkFieldsIsEmpty();
            presenter.getTextInFieldsAndSetEmployee();
            presenter.setCommand(new KeepEmployeePresenterEditCommand(presenter.getEmployee(), employeeCollection));
            presenter.getCommand().execute();
            JOptionPane.showMessageDialog(presenter.getView(), "Funcionário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
        presenter.getView().getCbxBonus().setEnabled(false);
        presenter.getView().getTfdSalary().setEditable(true);
        presenter.getView().getTfdAbsence().setEditable(true);
        presenter.getView().getChbEmployeeOfTheMonth().setEnabled(true);
        presenter.getView().getFfdAdmission().setEditable(false);
    }
    
    private void setButtons() {
        presenter.getView().getBtnSave().setEnabled(true);
        presenter.getView().getBtnEdit().setEnabled(false);
        presenter.getView().getBtnDelete().setEnabled(false);
    }
}