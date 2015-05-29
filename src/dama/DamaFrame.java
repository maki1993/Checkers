package dama;

import javax.swing.JFrame;

public class DamaFrame extends JFrame {

    public DamaFrame() {
        DamaPanel panel = new DamaPanel();
        setResizable(false);

        add(panel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
