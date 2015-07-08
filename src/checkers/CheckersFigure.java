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

}
