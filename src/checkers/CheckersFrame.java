package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help  F1");
        JMenu scoreMenu = new JMenu("Scores  F3");

        JMenuItem game = new JMenuItem("New Game  F2");
        JMenuItem exit = new JMenuItem("Exit");

        game.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (CheckersPanel.getInGame()) {
                    int answer;
                    answer = javax.swing.JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da prekinete igru ?", "QUESTION ?",
                            javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                    if (answer == javax.swing.JOptionPane.YES_OPTION) {
                        panel.newGame();
                        /*panel.frame.setVisible(true);
                         panel.frame.setLocationRelativeTo(panel);*/
                    }
                }
                /*panel.frame.setVisible(true);
                 panel.frame.setLocationRelativeTo(panel);*/
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
        helpMenu.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                panel.readHelpTextFileLineByLine();

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
        scoreMenu.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                panel.readScoreTextFileLineByLine();
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
        
        gameMenu.add(game);
        gameMenu.add(exit);

        menuBar.add(gameMenu);
        menuBar.add(scoreMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

}
