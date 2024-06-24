package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Chess {

    public Queen(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value=9.5;
    }
    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<Point>();
        int x = point.getX();
        int y = point.getY();
        int boardSize = 14;

        // Add queen moves
        // Move right
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x + i, y) != null) {
                if (board.findChess(x + i, y).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + i, y));
                }
                break;
            }
            ls.add(new Point(x + i, y));
        }
        // Move left
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x - i, y) != null) {
                if (board.findChess(x - i, y).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - i, y));
                }
                break;
            }
            ls.add(new Point(x - i, y));
        }
        // Move down
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x, y + i) != null) {
                if (board.findChess(x, y + i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x, y + i));
                }
                break;
            }
            ls.add(new Point(x, y + i));
        }
        // Move up
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x, y - i) != null) {
                if (board.findChess(x, y - i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x, y - i));
                }
                break;
            }
            ls.add(new Point(x, y - i));
        }
        // Move down right
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x + i, y + i) != null) {
                if (board.findChess(x + i, y + i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + i, y + i));
                }
                break;
            }
            ls.add(new Point(x + i, y + i));
        }
        // Move up left
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x - i, y - i) != null) {
                if (board.findChess(x - i, y - i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - i, y - i));
                }
                break;
            }
            ls.add(new Point(x - i, y - i));
        }
        // Move up right
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x + i, y - i) != null) {
                if (board.findChess(x + i, y - i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + i, y - i));
                }
                break;
            }
            ls.add(new Point(x + i, y - i));
        }
        // Move down left
        for (int i = 1; i < boardSize; i++) {
            if (board.findChess(x - i, y + i) != null) {
                if (board.findChess(x - i, y + i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - i, y + i));
                }
                break;
            }
            ls.add(new Point(x - i, y + i));
        }

        return ls;
    }


}
