package com.pss.sistemagestaodefuncionario.pss2020.view;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchEmployeeView extends javax.swing.JInternalFrame {

    /**
     * Creates new form SearchEmployeeView
     */
    public SearchEmployeeView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfdName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnViewEmployee = new javax.swing.JButton();
        btnViewBonus = new javax.swing.JButton();
        btnNewEmployee = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Buscar Funcionário");
        setPreferredSize(new java.awt.Dimension(714, 502));

        jLabel1.setText("Nome");

        btnSearch.setText("Buscar");
        btnSearch.setEnabled(false);

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Idade", "Função", "Salário base (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEmployees);
        if (tblEmployees.getColumnModel().getColumnCount() > 0) {
            tblEmployees.getColumnModel().getColumn(0).setMinWidth(40);
            tblEmployees.getColumnModel().getColumn(0).setMaxWidth(40);
            tblEmployees.getColumnModel().getColumn(1).setMinWidth(200);
            tblEmployees.getColumnModel().getColumn(1).setMaxWidth(200);
            tblEmployees.getColumnModel().getColumn(2).setMinWidth(60);
            tblEmployees.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        btnClose.setText("Fechar");

        btnViewEmployee.setText("Visualizar");
        btnViewEmployee.setEnabled(false);

        btnViewBonus.setText("Ver bônus");
        btnViewBonus.setEnabled(false);

        btnNewEmployee.setText("Novo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewEmployee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewBonus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewEmployee))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdName, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnViewEmployee)
                    .addComponent(btnViewBonus)
                    .addComponent(btnNewEmployee))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewEmployee;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewBonus;
    private javax.swing.JButton btnViewEmployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField tfdName;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnClose() {
        return btnClose;
    }

    public JButton getBtnNewEmployee() {
        return btnNewEmployee;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnViewBonus() {
        return btnViewBonus;
    }

    public JButton getBtnViewEmployee() {
        return btnViewEmployee;
    }

    public JTable getTblEmployees() {
        return tblEmployees;
    }

    public JTextField getTfdName() {
        return tfdName;
    }
}
