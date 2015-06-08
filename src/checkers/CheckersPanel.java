package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CheckersPanel extends JPanel implements ActionListener, KeyListener {

    CheckersField[][] Field = new CheckersField[8][8];

    private final int PANEL_HEIGHT = 600;
    private final int PANEL_WIDTH = 600;

    private Timer timer;

    public CheckersPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBackground(Color.GRAY);
        setFocusable(true);

        timer = new Timer(30, this);
        timer.start();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field[i][j] = new CheckersField(20 + i * 70, 20 + j * 70);

            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == j % 2) {

                    g2d.setColor(Color.BLACK);
                    g2d.fill(Field[i][j].getFiledRectangle());
                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.draw(Field[i][j].getFiledRectangle());
                    if (j == 0 || j == 1 || j == 2 && i % 2 == j % 2) {
                        g2d.setColor(Color.RED);
                        g.fillOval(32 + i * 70, 32 + j * 70, 45, 45);

                    } else if (j == 5 || j == 6 || j == 7 && i % 2 == j % 2) {
                        g2d.setColor(Color.BLUE);
                        g.fillOval(32 + i * 70, 32 + j * 70, 45, 45);
                    }

                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fill(Field[i][j].getFiledRectangle());
                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.draw(Field[i][j].getFiledRectangle());
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
