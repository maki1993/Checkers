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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CheckersPanel extends JPanel implements ActionListener, MouseListener {

    CheckersField[][] Field = new CheckersField[8][8];
    CheckersFigure figure;

    private final int PANEL_HEIGHT = 600;
    private final int PANEL_WIDTH = 600;
    CheckersMove[] moves;

    int currentPlayer;

    int selectedRow, selectedCol;

    public CheckersPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBackground(Color.GRAY);
        setFocusable(true);

        addMouseListener(this);

        figure = new CheckersFigure();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field[i][j] = new CheckersField(20 + i * 70, 20 + j * 70);
            }
        }

        currentPlayer = CheckersFigure.RED;
        moves = figure.getMoves(CheckersFigure.RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
                    case CheckersFigure.RED_QUEEN:
                        g2d.setColor(Color.RED);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        g2d.setColor(Color.GREEN);
                        g2d.drawString("K", 50 + row * 70, 50 + col * 70);
                        break;
                    case CheckersFigure.BLUE_QUEEN:
                        g2d.setColor(Color.BLUE);
                        g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                        g2d.setColor(Color.GREEN);
                        g2d.drawString("K", 50 + row * 70, 50 + col * 70);
                        break;

                }

            }
        }
        g2d.setColor(Color.GREEN);
            for (int i = 0; i < moves.length; i++) {
                g2d.drawRect(21 + moves[i].row1 * 70, 21 + moves[i].col1 * 70, 69, 69);
                g2d.drawRect(22 + moves[i].row1 * 70, 22 + moves[i].col1 * 70, 67, 67);
            }

    }

    void doClickSquare(int row, int col) {

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].row1 == row && moves[i].col1 == col) {
                selectedRow = row;
                selectedCol = col;

                if (currentPlayer == CheckersFigure.RED) {
                    System.out.println("Crveni je na potezu!");
                } else {
                    System.out.println("Plavi je na potezu!");
                }
                repaint();
                return;
            }
        }

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].row1 == selectedRow && moves[i].col1 == selectedCol
                    && moves[i].row2 == row && moves[i].col2 == col) {
                doMakeMove(moves[i]);
                return;
            }
        }

    }

    void doMakeMove(CheckersMove move) {

        figure.moveFigure(move);

        if (currentPlayer == CheckersFigure.RED) {
            currentPlayer = CheckersFigure.BLUE;
            moves = figure.getMoves(currentPlayer);

        } else {
            currentPlayer = CheckersFigure.RED;
            moves = figure.getMoves(currentPlayer);

        }
        selectedRow = -1;
        if (moves != null) {

            selectedRow = moves[0].row1;
            selectedCol = moves[0].col1;
        }

        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = (e.getX()) / 70;
        int col = (e.getY()) / 70;
        if (col >= 0 && col < 8 && row >= 0 && row < 8) {
            doClickSquare(row, col);
        }
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

}
