package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeepEmployeePresenterViewState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterViewState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) throws Exception {
        super(presenter, employeeCollection);
        
        setView();
        presenter.loadFields();
        initListeners();
        
        presenter.getView().setVisible(true);
    }
    
    private void initListeners() {        
        presenter.getView().getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                presenter.getView().dispose();
            }
        });
        
        presenter.getView().getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                update();
            }
        });
    }
   
    @Override
    public void update() {
        presenter.setState(new KeepEmployeePresenterEditState(presenter, employeeCollection));
    }
    
    @Override
    public void delete() {
        
    }
    
    private void setView() {
        presenter.cleanFields();
        setFields();
        setButtons();
    }
    
    private void setFields() {
        presenter.getView().getCbxOccupation().setEnabled(false);
        presenter.getView().getTfdName().setEditable(false);
        presenter.getView().getFfdAge().setEditable(false);
        presenter.getView().getCbxBonus().setEnabled(false);
        presenter.getView().getTfdSalary().setEditable(false);
        presenter.getView().getTfdAbsence().setEditable(false);
        presenter.getView().getChbEmployeeOfTheMonth().setEnabled(false);
        presenter.getView().getFfdAdmission().setEditable(false);
    }
    
    private void setButtons() {
        presenter.getView().getBtnSave().setVisible(false);
        presenter.getView().getBtnEdit().setVisible(true);
        presenter.getView().getBtnDelete().setVisible(true);
    }
}
