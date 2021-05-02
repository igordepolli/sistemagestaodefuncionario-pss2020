package com.pss.sistemagestaodefuncionario.pss2020.presenter;

import com.pss.sistemagestaodefuncionario.pss2020.model.Bonus;
import com.pss.sistemagestaodefuncionario.pss2020.model.Employee;
import com.pss.sistemagestaodefuncionario.pss2020.view.BonusHistoryView;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BonusHistoryPresenter {
    
    private final BonusHistoryView view;
    private final Employee employee;
    private DefaultTableModel tabelBonus;

    public BonusHistoryPresenter(Employee employee) {
        this.employee = employee;
        
        view = new BonusHistoryView(null, true);
        
        constructTableModel();
        loadEmployee();
        loadBonus();
        
        view.setModal(true);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    private void constructTableModel() {
        tabelBonus = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Tipo de bônus", "Valor do bônus", "Data do recebimento"}
        );

        view.getTblBonus().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelBonus.setNumRows(0);
        view.getTblBonus().setModel(tabelBonus);
    }
    
    private void loadBonus() {
        for (Bonus bonus : employee.getBonusCollection().getListBonus()) {
            tabelBonus.addRow(new Object[]{
                bonus.getDescription(),
                bonus.getValue(),
                bonus.getDate()
            });
        }
    }
    
    private void loadEmployee() {
        view.getLblName().setText(employee.getName());
        view.getLblOccupation().setText(employee.getOccupation());
    }
    
}
