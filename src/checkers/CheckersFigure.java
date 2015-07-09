package checkers;

import java.awt.geom.Ellipse2D;

public class CheckersFigure {

    static final int EMPTY = 0, RED = 1, RED_KING = 2, BLUE = 3, BLUE_KING = 4;
    int[][] figure;

    public CheckersFigure() {
        figure = new int[8][8];
        setUpGame();
    }

    void setUpGame() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == col % 2) {
                    if (col < 3) {
                        figure[row][col] = BLUE;
                    } else if (col > 4) {
                        figure[row][col] = RED;
                    } else {
                        figure[row][col] = EMPTY;
                    }
                } else {
                    figure[row][col] = EMPTY;
                }
            }
        }

    }

    int pieceAt(int row, int col) {
        return figure[row][col];
    }

    void moveFigure(int row1, int col1, int row2, int col2) {
        figure[row2][col2] = figure[row1][col1];
        figure[row1][col1] = EMPTY;
        if (row1 - row2 == 2 || row1 - row2 == -2) {

            int jumpRow = (row1 + row2) / 2;
            int jumpCol = (col1 + col2) / 2;
            figure[jumpRow][jumpCol] = EMPTY;
        }
        if (row2 == 0 && figure[row2][col2] == RED) {
            figure[row2][row2] = RED_KING;
        }
        if (row2 == 0 && figure[row2][col2] == BLUE) {
            figure[row2][col2] = BLUE_KING;
        }
    }

}
