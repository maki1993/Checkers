package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CheckersFrame extends JFrame {

    CheckersPanel panel = new CheckersPanel();

    public CheckersFrame() {

        setResizable(false);
        setJMenuBar(initMenu());
        add(panel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JMenuBar initMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        JMenu menu1 = new JMenu("Help");

        JMenuItem game = new JMenuItem("8x8");
        JMenuItem game1 = new JMenuItem("12x12");
        JMenuItem exit = new JMenuItem("Exit");

        game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.newGame();

            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int answer;
                answer = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?", "QUESTION ?",
                        javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                if (answer == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });

        menu.add(game);
        menu.add(game1);
        menu.add(exit);

        menuBar.add(menu);
        menuBar.add(menu1);

        return menuBar;
    }
}
