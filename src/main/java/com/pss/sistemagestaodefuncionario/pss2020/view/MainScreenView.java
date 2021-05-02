package com.pss.sistemagestaodefuncionario.pss2020.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class MainScreenView extends javax.swing.JFrame {

    /**
     * Creates new form MainScreenView
     */
    public MainScreenView() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblAmountEmployees = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniKeepEmployee = new javax.swing.JMenuItem();
        mniSearchEmployee = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniCalculateWages = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniSetupLog = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestão de Pessoas");
        setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("1.0");

        lblAmountEmployees.setForeground(new java.awt.Color(0, 0, 0));
        lblAmountEmployees.setText("0");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("XML");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("JSON");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TXT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(306, 306, 306)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(lblAmountEmployees)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblAmountEmployees)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jMenu1.setText("Funcionário");

        mniKeepEmployee.setText("Manter Funcionário");
        jMenu1.add(mniKeepEmployee);

        mniSearchEmployee.setText("Buscar Funcionário");
        jMenu1.add(mniSearchEmployee);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Salário");

        mniCalculateWages.setText("Calcular Salários");
        jMenu2.add(mniCalculateWages);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ferramentas");

        mniSetupLog.setText("Configurar log");
        jMenu3.add(mniSetupLog);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 598, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAmountEmployees;
    private javax.swing.JMenuItem mniCalculateWages;
    private javax.swing.JMenuItem mniKeepEmployee;
    private javax.swing.JMenuItem mniSearchEmployee;
    private javax.swing.JMenuItem mniSetupLog;
    // End of variables declaration//GEN-END:variables

    public JLabel getLblAmountEmployees() {
        return lblAmountEmployees;
    }

    public JMenuItem getMniCalculateWages() {
        return mniCalculateWages;
    }

    public JMenuItem getMniKeepEmployee() {
        return mniKeepEmployee;
    }

    public JMenuItem getMniSearchEmployee() {
        return mniSearchEmployee;
    }

    public JMenuItem getMniSetupLog() {
        return mniSetupLog;
    }
    
}
