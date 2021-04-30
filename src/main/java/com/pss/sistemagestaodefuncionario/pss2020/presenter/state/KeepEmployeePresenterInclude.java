package com.pss.sistemagestaodefuncionario.pss2020.presenter.state;

import com.pss.sistemagestaodefuncionario.pss2020.presenter.KeepEmployeePresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeepEmployeePresenterInclude extends KeepEmployeePresenterState {

    public KeepEmployeePresenterInclude(KeepEmployeePresenter presenter) {
        super(presenter);
        
        presenter.cleanFields();
    }
    
    private void initListeners() {
        presenter.getView().getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        presenter.getView().getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                presenter.getView().dispose();
            }
        });
    }
}
