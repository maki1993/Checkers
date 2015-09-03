package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class CheckersFrame extends JFrame {

    CheckersPanel panel = new CheckersPanel();

    public CheckersFrame() {

        setResizable(false);
        setTitle("Checkers");
        setJMenuBar(initMenu());
        add(panel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private JMenuBar initMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");
        JMenu scoreMenu = new JMenu("Scores  F3");

        JMenuItem game = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem viewHelp = new JMenuItem("View help");
        JMenuItem about = new JMenuItem("About Checkers");

        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ALT_DOWN_MASK));
        game.setAccelerator(KeyStroke.getKeyStroke("F2"));
        viewHelp.setAccelerator(KeyStroke.getKeyStroke("F1"));

        game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (CheckersPanel.getInGame()) {
                    int answer;
                    answer = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this game ?", "QUESTION ?",
                            javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                    if (answer == javax.swing.JOptionPane.YES_OPTION) {
                        panel.newGame();
                        /*panel.gameFrame.setVisible(true);
                         panel.gameFrame.setLocationRelativeTo(null);*/

                    }
                }

                /*panel.gameFrame.setVisible(true);
                 panel.gameFrame.setLocationRelativeTo(null);*/
                panel.newGame();
            }
        });

        scoreMenu.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                panel.readTextFileLineByLine("src/TextDocuments/scores.txt");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        viewHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.helpFrame.setVisible(true);
                panel.helpFrame.setLocationRelativeTo(null);
            }

        });

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.readTextFileLineByLine("src/TextDocuments/aboutCheckers.txt");
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

        gameMenu.add(game);
        gameMenu.add(exit);

        helpMenu.add(viewHelp);
        helpMenu.add(about);

        menuBar.add(gameMenu);
        menuBar.add(scoreMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

}
