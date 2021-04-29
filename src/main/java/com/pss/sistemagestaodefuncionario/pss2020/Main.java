package com.pss.sistemagestaodefuncionario.pss2020;

import com.pss.sistemagestaodefuncionario.pss2020.presenter.MainScreenPresenter;

public class Main {
    
    public static void main(String[] args) {

        try {
            new MainScreenPresenter();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
