package checkers;

import javax.swing.JFrame;

public class CheckersFrame extends JFrame {

    public CheckersFrame() {
        CheckersPanel panel = new CheckersPanel();
        setResizable(false);

        add(panel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
