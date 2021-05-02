package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.log.JsonLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.ManagerLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.TxtLog;
import com.pss.sistemagestaodefuncionario.pss2020.model.log.XmlLog;
import com.pss.sistemagestaodefuncionario.pss2020.view.SetupLogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SetupLogPresenter {

    private final SetupLogView view;
    private final ManagerLog log;

    public SetupLogPresenter(ManagerLog log) {
        view = new SetupLogView(null, true);
        this.log = log;

        initListeners();

        view.setModal(true);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void initListeners() {
        view.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    defineInstanceOfSelectedItem();
                    JOptionPane.showMessageDialog(view, "Log configurado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    try {
                        log.write(ex.getMessage());
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(view, ex.getMessage(), "Falha ao escrever no log", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private void defineInstanceOfSelectedItem() throws Exception {
        int indexItem = view.getCbxLog().getSelectedIndex();

        switch (indexItem) {
            case 0:
                log.setLog(new JsonLog());
                break;
            case 1:
                log.setLog(new XmlLog());
                break;
            case 2:
                log.setLog(new TxtLog());
                break;
        }
    }

    public SetupLogView getView() {
        return view;
    }

}
