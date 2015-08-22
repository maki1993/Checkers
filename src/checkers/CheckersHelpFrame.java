package checkers;

public class CheckersHelpFrame extends javax.swing.JFrame {

    CheckersPanel panel;

    public CheckersHelpFrame(CheckersPanel panel) {

        this.setAlwaysOnTop(true);
        this.panel = panel;
        this.setTitle("Help");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        text.setEditable(false);
        text.setColumns(20);
        text.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        text.setRows(5);
        text.setText("CHECKERS: Rules\n\nMOVEMENT\n\nBasic movement is to move a checker one space diagonally \nforward.You can not move a checker backwards until it  \nbecomes a Queen,as described below. If a jump is available, \nyou must take the jump,as described in the next question \nand answer.\n\nJUMPING\n\nIf one of your opponent’s checkers is on a forward diagonal\nnext to one of your checkers, and the next space beyond \nthe opponent’s checker is empty, then your checker must\njump the opponent’s checker and land in the space beyond.\nYour opponent’s checker is captured and removed from the \nboard. \n\nYOU MUST JUMP IF POSSIBLE\n\nIf a jump is available for one of your pieces, you must make\nthat jump. If more jumps are available with that same piece, \nyou must continue to jump with it until it can jump no more. \nTo make the second and third jump with a piece, you do not \nneed to click that piece again. Just click the next space to\nwhich it will jump. \n\nIf more than one of your pieces has a jump available at the \nstartof your turn, you can choose which piece you will move. \nBut then you must make all the jumps available for that piece. \n\nCROWNING\n\nWhen one of your checkers reaches the opposite side of\nthe board, it is crowned and becomes aQueen. Your turn \nends there.A Queen can move backward as well as forward\nalong the diagonals. It can only move a distance of one \nspace. A Queen can also jump backward and forward. \n");
        jScrollPane1.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables
}
