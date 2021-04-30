package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;

public abstract class KeepEmployeePresenterState {
    
    protected KeepEmployeePresenter presenter;
    protected EmployeeCollection employeeCollection;

    public KeepEmployeePresenterState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        this.presenter = presenter;
        presenter.clearListeners();
    }
    
    public void save() {
    }
    
    public void edit() {
    }
    
    public void remove() {
    }
    
    protected void cleanFields() {
        presenter.getView().getCbxOccupation().setSelectedIndex(-1);
        presenter.getView().getTfdName().setText("");
        presenter.getView().getFfdAge().setText("");
        presenter.getView().getCbxBonus().setSelectedIndex(-1);
        presenter.getView().getTfdSalary().setText("");
        presenter.getView().getTfdAbsence().setText("");
        presenter.getView().getChbEmployeeOfTheMonth().setSelected(false);
        presenter.getView().getFfdAdmission().setText("");
    }
}
