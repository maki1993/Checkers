package checkers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

    CheckersField[][] Field = new CheckersField[CheckersFigure.size][CheckersFigure.size];

    CheckersField field = new CheckersField(20, 20);

    CheckersFigure figure;

    private Font mainFont;

    private final int PANEL_DIMENSION = 40 + 70 * figure.getSize();
    CheckersMove[] moves;

    GameFrame gameFrame;
    CheckersHelpFrame helpFrame;
    CheckersScore score;

    private Image background;

    int currentPlayer;
    static Boolean inGame;

    int selectedRow, selectedCol;

    static int redWin = 0;
    static int blueWin = 0;

    public CheckersPanel() {

        mainFont = new Font("Monotype Corsiva", Font.BOLD, 25);
        loadImages();

        setPreferredSize(new Dimension(PANEL_DIMENSION, PANEL_DIMENSION));
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);

        inGame = false;
        addMouseListener(this);

        figure = new CheckersFigure();

        gameFrame = new GameFrame(this);

        helpFrame = new CheckersHelpFrame(this);

        score = new CheckersScore(this);
        System.out.println(figure.getSize());
        for (int i = 0; i < figure.getSize(); i++) {
            for (int j = 0; j < figure.getSize(); j++) {
                Field[i][j] = new CheckersField(field.getX() + i * field.getFILED_DIMENSION(), field.getY() + j * field. getFILED_DIMENSION());
            }
        }
    }

    public static int getRedWin() {
        return redWin;
    }

    public static int getBlueWin() {
        return blueWin;
    }

    private void loadImages() {

        try {
            background = ImageIO.read(new File("src/images/Checkers-300x300.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static Boolean getInGame() {
        return inGame;
    }

    private void drawBackground(Graphics2D g2d) {

        g2d.drawImage(background, 0, 0, PANEL_DIMENSION + 5, PANEL_DIMENSION + 5, null);

    }

    void newGame() {
        figure.setUpGame();
        currentPlayer = CheckersFigure.RED;
        moves = figure.getMoves(CheckersFigure.RED);
        inGame = true;
        System.out.println("Crveni je na potezu!");
        selectedCol = -1;
        repaint();

    }

    void gameOver(String str) {

        inGame = false;

        int answer;
        answer = javax.swing.JOptionPane.showConfirmDialog(null, str + "Da li želite da igrate ponovo ?", "Game Over",
                javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
        if (answer == javax.swing.JOptionPane.NO_OPTION) {
            int answer1;
            answer1 = javax.swing.JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da napustite igru ?", "Game Over",
                    javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
            if (answer1 == javax.swing.JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            newGame();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        newGame();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(mainFont);

        drawBackground(g2d);
        if (inGame) {

            for (int row = 0; row < figure.getSize(); row++) {
                for (int col = 0; col < figure.getSize(); col++) {
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
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("K", 45 + row * 70, 60 + col * 70);
                            break;
                        case CheckersFigure.BLUE_QUEEN:
                            g2d.setColor(Color.BLUE);
                            g2d.fillOval(32 + row * 70, 32 + col * 70, 45, 45);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString("K", 45 + row * 70, 60 + col * 70);
                            break;

                    }

                }
            }
            g2d.setColor(Color.ORANGE);
            for (int i = 0; i < moves.length; i++) {
                g2d.drawRect(21 + moves[i].row1 * 70, 21 + moves[i].col1 * 70, 69, 69);
                g2d.drawRect(22 + moves[i].row1 * 70, 22 + moves[i].col1 * 70, 67, 67);
            }
            if (selectedCol >= 0) {
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
                redWin++;
                gameOver("Plavi ne može da se pomeri. Crveni je pobedio.");
            } else {
                System.out.println("Plavi je na potezu!");
            }

        } else {
            currentPlayer = CheckersFigure.RED;
            moves = figure.getMoves(currentPlayer);
            if (moves == null) {
                blueWin++;
                gameOver("Crveni ne može da se pomeri. Plavi je pobedio.");

            } else {
                System.out.println("Crveni je na potezu!");
            }

        }

        selectedCol = -1;

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
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (inGame == false) {
            System.out.println("Game over");
        } else {
            int row = (e.getX()) / field.getFILED_DIMENSION();
            int col = (e.getY()) / field.getFILED_DIMENSION();
            if (col >= 0 && col < 8 && row >= 0 && row < 8) {
                doClickSquare(row, col);
            }
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
