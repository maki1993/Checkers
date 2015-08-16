package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class CheckersPanel extends JPanel implements ActionListener, MouseListener {

    CheckersField[][] Field = new CheckersField[8][8];

    CheckersField field = new CheckersField(20, 20);

    CheckersFigure figure;

    private final int PANEL_DIMENSION = 600;
    CheckersMove[] moves;

    private Image background;

    int currentPlayer;
    Boolean inGame;

    int selectedRow, selectedCol;

    public CheckersPanel() {

        loadImages();

        setPreferredSize(new Dimension(PANEL_DIMENSION, PANEL_DIMENSION));
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);

        inGame = false;

        addMouseListener(this);

        figure = new CheckersFigure();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field[i][j] = new CheckersField(field.getX() + i * field.getFILED_HIGHT(), field.getX() + j * field.getFILED_HIGHT());
            }
        }
    }

    private void loadImages() {

        try {
            background = ImageIO.read(new File("src/images/Checkers-300x300.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void drawBackground(Graphics2D g2d) {

        g2d.drawImage(background, 0, 0, PANEL_DIMENSION + 5, PANEL_DIMENSION + 5, null);

    }

    void newGame() {

        figure.setUpGame();
        currentPlayer = CheckersFigure.RED;
        moves = figure.getMoves(CheckersFigure.RED);
        inGame = true;
        selectedRow = -1;
        repaint();

    }

    void gameOver(String str) {

        inGame = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);

        if (inGame) {

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
            g2d.setColor(Color.ORANGE);
            for (int i = 0; i < moves.length; i++) {
                g2d.drawRect(21 + moves[i].row1 * 70, 21 + moves[i].col1 * 70, 69, 69);
                g2d.drawRect(22 + moves[i].row1 * 70, 22 + moves[i].col1 * 70, 67, 67);
            }
            if (selectedRow >= 0) {
                g.setColor(Color.pink);
                g.drawRect(21 + selectedRow * 70, 21 + selectedCol * 70, 69, 69);
                g.drawRect(21 + selectedRow * 70, 22 + selectedCol * 70, 67, 67);
                g.setColor(Color.green);
                for (int i = 0; i < moves.length; i++) {
                    if (moves[i].col1 == selectedCol && moves[i].row1 == selectedRow) {
                        g.drawRect(21 + moves[i].row2 * 70, 21 + moves[i].col2 * 70, 69, 69);
                        g.drawRect(22 + moves[i].row2 * 70, 22 + moves[i].col2 * 70, 67, 67);
                    }
                }
            }
        }

    }

    void doClickSquare(int row, int col) {

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].row1 == row && moves[i].col1 == col) {
                selectedRow = row;
                selectedCol = col;

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
            if (moves == null) {
                gameOver("Plavi ne može da se pomeri.Crveni je pobedio.");
            } else {
                System.out.println("Plavi je na potezu!");
            }

        } else {
            currentPlayer = CheckersFigure.RED;
            moves = figure.getMoves(currentPlayer);
            if (moves == null) {
                gameOver("Crveni ne može da se pomeri.Plavi je pobedio.");
            } else {
                System.out.println("Crveni je na potezu!");
            }

        }
        selectedRow = -1;

        if (moves != null) {
            boolean sameStartField = true;
            for (int i = 1; i < moves.length; i++) {
                if (moves[i].row1 != moves[0].row1
                        || moves[i].col1 != moves[0].col1) {
                    sameStartField = false;
                    break;
                }
            }
            if (sameStartField) {

                selectedRow = moves[0].row1;
                selectedCol = moves[0].col1;
            }
            repaint();

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row = (e.getX()) / field.getFILED_HIGHT();
        int col = (e.getY()) / field.getFILED_HIGHT();
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
