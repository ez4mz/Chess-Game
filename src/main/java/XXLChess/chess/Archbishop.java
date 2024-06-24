package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Archbishop extends Chess {
    public Archbishop(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value = 7.5;
    }

    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();
        int boardSize = 14;


        // Knight's movement
        // Add knight moves (they can jump over pieces)
        ls.add(new Point(x + 2, y + 1));
        ls.add(new Point(x + 2, y - 1));
        ls.add(new Point(x - 2, y + 1));
        ls.add(new Point(x - 2, y - 1));
        ls.add(new Point(x + 1, y + 2));
        ls.add(new Point(x + 1, y - 2));
        ls.add(new Point(x - 1, y + 2));
        ls.add(new Point(x - 1, y - 2));

        // Add bishop moves
        for (int i = 1; i < 14; i++) {
            if (!addPointIfValid(ls, board, x + i, y + i)) break;  // Up-Right
        }
        for (int i = 1; i < 14; i++) {
            if (!addPointIfValid(ls, board, x - i, y - i)) break;  // Down-Left
        }
        for (int i = 1; i < 14; i++) {
            if (!addPointIfValid(ls, board, x + i, y - i)) break;  // Down-Right
        }
        for (int i = 1; i < 14; i++) {
            if (!addPointIfValid(ls, board, x - i, y + i)) break;  // Up-Left
        }

        return ls;
    }

    private boolean addPointIfValid(List<Point> ls, Board board, int x, int y) {
        if (x >= 0 && x < 14 && y >= 0 && y < 14) { // Added boundary check here
            Chess targetChess = board.findChess(x, y);
            if (targetChess != null) {
                if (targetChess.isWhite() != this.isWhite()) {
                    ls.add(new Point(x, y));
                }
                return false;
            }
            ls.add(new Point(x, y));
        }
        return true;
    }
}
