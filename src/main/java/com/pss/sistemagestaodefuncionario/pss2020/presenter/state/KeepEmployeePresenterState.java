package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.model.EmployeeCollection;
import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;

public abstract class KeepEmployeePresenterState {
    
    protected KeepEmployeePresenter presenter;
    protected EmployeeCollection employeeCollection;

    public KeepEmployeePresenterState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        this.presenter = presenter;
        this.employeeCollection = employeeCollection;
        presenter.cleanListeners();
    }
    
    public void save() {
    }
    
    public void update() {
    }
    
    public void delete() {
    }
    
}
