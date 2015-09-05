package checkers;

import java.util.ArrayList;

public class CheckersFigure {

    static final int EMPTY = 0, RED = 1, RED_QUEEN = 2, BLUE = 3, BLUE_QUEEN = 4;

    int[][] figure;

    static int size = 8;

    public CheckersFigure() {
        figure = new int[size][size];
        setUpGame();

    }

    void setUpGame() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row % 2 == col % 2) {
                    if (col < 3) {
                        figure[row][col] = BLUE;
                    } else if (col > size - 4) {
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

    public static int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        if (col2 == 0 && figure[row2][col2] == RED) {
            figure[row2][col2] = RED_QUEEN;
        }
        if (col2 == size - 1 && figure[row2][col2] == BLUE) {
            figure[row2][col2] = BLUE_QUEEN;
        }
    }

    void moveFigure(CheckersMove move) {
        moveFigure(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

    private boolean canJump(int player, int row1, int col1, int row2, int col2, int row3, int col3) {

        if (row3 < 0 || row3 >= size || col3 < 0 || col3 >= size) {
            return false;
        }

        if (figure[row3][col3] != EMPTY) {
            return false;
        }

        if (player == RED) {
            if (figure[row1][col1] == RED && col3 > col1) {
                return false;
            }
            if (figure[row2][col2] != BLUE && figure[row2][col2] != BLUE_QUEEN) {
                return false;
            }
            return true;
        } else {
            if (figure[row1][col1] == BLUE && col3 < col1) {
                return false;
            }
            if (figure[row2][col2] != RED && figure[row2][col2] != RED_QUEEN) {
                return false;
            }
            return true;
        }

    }

    private boolean canMove(int player, int row1, int col1, int row2, int col2) {

        if (row2 < 0 || row2 >= size || col2 < 0 || col2 >= size) {
            return false;
        }

        if (figure[row2][col2] != EMPTY) {
            return false;
        }

        if (player == RED) {
            if (figure[row1][col1] == RED && col2 > col1) {
                return false;
            }
            return true;
        } else {
            if (figure[row1][col1] == BLUE && col2 < col1) {
                return false;
            }
            return true;
        }

    }

    CheckersMove[] getMoves(int player) {

        if (player != RED && player != BLUE) {
            return null;
        }
        int playerQueen = 0;
        if (player == RED) {
            playerQueen = RED_QUEEN;
        } else {
            playerQueen = BLUE_QUEEN;
        }

        ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (figure[row][col] == player || figure[row][col] == playerQueen) {
                    if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2)) {

                        moves.add(new CheckersMove(row, col, row + 2, col + 2));
                    }
                    if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2)) {
                        moves.add(new CheckersMove(row, col, row - 2, col + 2));

                    }
                    if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2)) {
                        moves.add(new CheckersMove(row, col, row + 2, col - 2));

                    }
                    if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2)) {
                        moves.add(new CheckersMove(row, col, row - 2, col - 2));

                    }
                    if (canMove(player, row, col, row + 1, col + 1)) {
                        moves.add(new CheckersMove(row, col, row + 1, col + 1));

                    }
                    if (canMove(player, row, col, row - 1, col + 1)) {
                        moves.add(new CheckersMove(row, col, row - 1, col + 1));

                    }
                    if (canMove(player, row, col, row + 1, col - 1)) {
                        moves.add(new CheckersMove(row, col, row + 1, col - 1));

                    }
                    if (canMove(player, row, col, row - 1, col - 1)) {
                        moves.add(new CheckersMove(row, col, row - 1, col - 1));

                    }
                }
            }
        }

        if (moves.size() == 0) {
            return null;
        } else {
            return moves.toArray(new CheckersMove[moves.size()]);

        }
    }

    CheckersMove[] getJumps(int player, int row, int col) {

        if (player != RED && player != BLUE) {
            return null;
        }
        int playerQueen = 0;
        if (player == RED) {
            playerQueen = RED_QUEEN;
        } else {
            playerQueen = BLUE_QUEEN;
        }

        ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();

        if (figure[row][col] == player || figure[row][col] == playerQueen) {
            if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2)) {
                moves.add(new CheckersMove(row, col, row + 2, col + 2));
            }
            if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2)) {
                moves.add(new CheckersMove(row, col, row - 2, col + 2));
            }
            if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2)) {
                moves.add(new CheckersMove(row, col, row + 2, col - 2));
            }
            if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2)) {
                moves.add(new CheckersMove(row, col, row - 2, col - 2));
            }
        }

        if (moves.size() == 0) {
            return null;
        } else {

            return moves.toArray(new CheckersMove[moves.size()]);

        }
    }
}
