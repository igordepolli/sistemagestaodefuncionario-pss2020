package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;

public abstract class KeepEmployeePresenterState {
    
    protected KeepEmployeePresenter presenter;

    public KeepEmployeePresenterState(KeepEmployeePresenter presenter) {
        this.presenter = presenter;
        presenter.clearListeners();
    }
    
    public void save() {
    }
    
    public void edit() {
    }
    
    public void remove() {
    }
    
}
