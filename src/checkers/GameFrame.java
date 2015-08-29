package checkers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends javax.swing.JFrame {

    static int sizeOf;

    CheckersPanel panel;

    public GameFrame(CheckersPanel panel) {
        this.setAlwaysOnTop(true);
        this.panel = panel;
        this.setTitle("Game");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        size = new javax.swing.JCheckBox();
        size1 = new javax.swing.JCheckBox();
        option = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        size.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        size.setText("8x8");
        size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeActionPerformed(evt);
            }
        });

        size1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        size1.setText("12x12");
        size1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                size1ActionPerformed(evt);
            }
        });

        option.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        option.setText("OK");
        option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(size1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(size, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(option)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(size))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(option)))
                .addGap(18, 18, 18)
                .addComponent(size1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeActionPerformed
        if (size.isSelected()) {
            size1.setSelected(false);
        }
        sizeOf = 8;
    }//GEN-LAST:event_sizeActionPerformed

    private void size1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_size1ActionPerformed
        if (size1.isSelected()) {
            size.setSelected(false);
        }
        sizeOf = 12;
    }//GEN-LAST:event_size1ActionPerformed

    private void optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionActionPerformed
        if (!(size.isSelected() || size1.isSelected())) {
            this.toBack();
            JOptionPane.showMessageDialog(new JFrame(), "Select size",
                    "Warning", 0);
            this.toFront();
        } else {
            this.dispose();
            panel.setVisible(true);
            panel.newGame();
        }
    }//GEN-LAST:event_optionActionPerformed

    public static int getSizeOf() {
        return sizeOf;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton option;
    private javax.swing.JCheckBox size;
    private javax.swing.JCheckBox size1;
    // End of variables declaration//GEN-END:variables
}
