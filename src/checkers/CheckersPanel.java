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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CheckersPanel extends JPanel implements ActionListener, MouseListener, KeyListener {

    CheckersField[][] Field = new CheckersField[CheckersFigure.size][CheckersFigure.size];

    CheckersField field = new CheckersField(20, 20);

    CheckersFigure figure;

    private Font mainFont;

    private final int PANEL_DIMENSION = 40 + 70 * figure.getSize();
    CheckersMove[] moves;

    CheckersHelpFrame helpFrame;

    private Image background;

    int currentPlayer;
    static Boolean inGame;

    int selectedRow, selectedCol;

    static int redWin;
    static int blueWin;

    public CheckersPanel() {

        mainFont = new Font("Monotype Corsiva", Font.BOLD, 25);
        loadImages();

        setPreferredSize(new Dimension(PANEL_DIMENSION, PANEL_DIMENSION));
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

        inGame = false;
        addMouseListener(this);
        addKeyListener(this);

        figure = new CheckersFigure();

        helpFrame = new CheckersHelpFrame(this);

        for (int i = 0; i < figure.getSize(); i++) {
            for (int j = 0; j < figure.getSize(); j++) {
                Field[i][j] = new CheckersField(field.getX() + i * field.getFILED_DIMENSION(), field.getY() + j * field.getFILED_DIMENSION());
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
        System.out.println("Red player's move!");
        selectedCol = -1;
        repaint();

    }

    void gameOver(String str) {

        inGame = false;

        try {

            List<String> scores = load("src/TextDocuments/scores.txt");
            int answer;
            answer = javax.swing.JOptionPane.showConfirmDialog(null, str + "\n\n" + scores.get(0) + "\n"
                    + scores.get(1) + "\n\n Do you want play again ?", "Game Over",
                    javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
            if (answer == javax.swing.JOptionPane.NO_OPTION) {
                int answer1;
                answer1 = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?", "Game Over",
                        javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                if (answer1 == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else {
                newGame();
            }

        } catch (IOException ex) {
            System.out.println("Error : " + ex);
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
                g2d.drawRect(21 + moves[i].fromRow * 70, 21 + moves[i].fromCol * 70, 69, 69);
                g2d.drawRect(22 + moves[i].fromRow * 70, 22 + moves[i].fromCol * 70, 67, 67);
            }
            if (selectedCol >= 0) {
                g.setColor(Color.PINK);
                g.drawRect(21 + selectedRow * 70, 21 + selectedCol * 70, 69, 69);
                g.drawRect(22 + selectedRow * 70, 22 + selectedCol * 70, 67, 67);
                g.setColor(Color.green);
                for (int i = 0; i < moves.length; i++) {
                    if (moves[i].fromCol == selectedCol && moves[i].fromRow == selectedRow) {
                        g.drawRect(21 + moves[i].toRow * 70, 21 + moves[i].toCol * 70, 69, 69);
                        g.drawRect(22 + moves[i].toRow * 70, 22 + moves[i].toCol * 70, 67, 67);
                    }
                }
            }
        } else {
            drawBackground(g2d);
            g2d.dispose();
        }
    }

    void doClickSquare(int row, int col) {

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].fromRow == row && moves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;

                repaint();
                return;
            }
        }

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].fromRow == selectedRow && moves[i].fromCol == selectedCol
                    && moves[i].toRow == row && moves[i].toCol == col) {
                doMakeMove(moves[i]);
                return;
            }
        }

    }

    void doMakeMove(CheckersMove move) {

        figure.moveFigure(move);
        if (move.fromRow - move.toRow == 2 || move.fromRow - move.toRow == -2) {
            moves = figure.getJumps(currentPlayer, move.toRow, move.toCol);
            if (moves != null) {
                selectedRow = move.toRow;
                selectedCol = move.toCol;
                repaint();
                return;
            }
        }
        try {

            List<String> scores = load("src/TextDocuments/scores.txt");
            int indexOfLastSpace = scores.get(0).lastIndexOf(" ");
            redWin = Integer.parseInt((scores.get(0)).substring(indexOfLastSpace + 1, (scores.get(0)).length()));
            blueWin = Integer.parseInt((scores.get(1)).substring(indexOfLastSpace + 1, (scores.get(1)).length()));
            scores.clear();

            if (currentPlayer == CheckersFigure.RED) {
                currentPlayer = CheckersFigure.BLUE;
                moves = figure.getMoves(currentPlayer);
                if (moves == null) {
                    redWin++;

                    scores.add("Red  has wins:  " + redWin);
                    scores.add("Blue has wins:  " + blueWin);
                    save_file("src/TextDocuments/scores.txt", scores);

                    gameOver("Blue can't move. Red player won.");

                } else {
                    System.out.println("Blue player's move!");
                }

            } else {
                currentPlayer = CheckersFigure.RED;
                moves = figure.getMoves(currentPlayer);
                if (moves == null) {
                    blueWin++;
                    scores.add("Red  has wins:  " + redWin);
                    scores.add("Blue has wins:  " + blueWin);
                    save_file("src/TextDocuments/scores.txt", scores);

                    gameOver("Red can't move. Blue player won.");

                } else {
                    System.out.println("Red player's move!");
                }

            }

        } catch (IOException ex) {
            System.out.println("Error : " + ex);
        }

        selectedCol = -1;

        if (moves != null) {
            boolean sameStartField = true;
            for (int i = 1; i < moves.length; i++) {
                if (moves[i].fromRow != moves[0].fromRow
                        || moves[i].fromCol != moves[0].fromCol) {
                    sameStartField = false;
                    break;
                }
            }
            if (sameStartField) {

                selectedRow = moves[0].fromRow;
                selectedCol = moves[0].fromCol;
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (inGame == false) {
            System.out.println("PRESS \"New Game\" OR \"F2\" TO START NEW GAME !");
        } else {
            int row = ((e.getX()) - field.getX()) / field.getFILED_DIMENSION();
            int col = (e.getY() - field.getY()) / field.getFILED_DIMENSION();
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

    private void save_file(String name_fale, List<String> scores) throws IOException {

        File file = new File(name_fale);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            for (String score : scores) {
                writer.println(score);
            }
        }
    }

    private List<String> load(String file_name) throws FileNotFoundException {
        File file = new File(file_name);

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        List<String> scores = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scores.add(scanner.nextLine());

            }
        }

        return scores;
    }

    public static void readTextFileLineByLine(String filePath) {
        FileReader in = null;
        BufferedReader bin = null;

        try {

            File file = new File(filePath);

            in = new FileReader(file);

            bin = new BufferedReader(in);

            String data;
            ArrayList<String> words = new ArrayList<>();

            while ((data = bin.readLine()) != null) {
                words.add(data);
            }

            int d = words.size();

            String strLine = "";

            for (int i = 0; i < d; i++) {
                strLine += words.get(i) + "\n";
            }
            if (filePath.contains("scores")) {
                JOptionPane.showMessageDialog(null, strLine, "Scores", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, strLine, "About checkers", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_F3) {
            this.readTextFileLineByLine("src/TextDocuments/scores.txt");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
