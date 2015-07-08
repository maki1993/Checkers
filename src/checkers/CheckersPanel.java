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
    CheckersFigure figure;

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

        figure = new CheckersFigure();

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

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == col % 2) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fill(Field[row][col].getFiledRectangle());
                g2d.setPaint(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(Field[row][col].getFiledRectangle());
                switch (figure.pieceAt(row, col)) {
                    case CheckersFigure.RED:
                        g2d.setColor(Color.RED);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        break;
                    case CheckersFigure.BLUE:
                        g2d.setColor(Color.BLUE);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        break;
                    case CheckersFigure.RED_KING:
                        g2d.setColor(Color.RED);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString("K", 20 + row * 70, 20 + col * 70);
                        break;
                    case CheckersFigure.BLUE_KING:
                        g2d.setColor(Color.BLUE);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString("K", 20 + row * 70, 20 + col * 70);
                        break;

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
