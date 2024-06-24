package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Chess {

    public Pawn(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value = 1;
    }

    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();
        int boardSize = 7;

        if (isWhite) {
            ls.add(new Point(x, y - 1));  // Move forward one square
            if (y == 12) {  // If it's the pawn's first move
                ls.add(new Point(x, y - 2));  // Move forward two squares
            }
            if (y <= 6) {
                for(int i = 1; i < boardSize; i++) {
                    ls.add(new Point(x + i, y)); // Move right
                    ls.add(new Point(x - i, y)); // Move left
                    ls.add(new Point(x, y + i)); // Move down
                    ls.add(new Point(x, y - i)); // Move up
                    ls.add(new Point(x + i, y + i)); // Move down right
                    ls.add(new Point(x - i, y - i)); // Move up left
                    ls.add(new Point(x + i, y - i)); // Move up right
                    ls.add(new Point(x - i, y + i)); // Move down left
                }
            } else {
                if( board.findChess(x - 1, y - 1) != null && board.findChess(x - 1, y - 1).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - 1, y - 1));
                }
                if( board.findChess(x + 1, y - 1) != null && board.findChess(x + 1, y - 1).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + 1, y - 1));
                }
            }
        } else {
            ls.add(new Point(x, y + 1));  // Move forward one square
            if (y == 1) {  // If it's the pawn's first move
                ls.add(new Point(x, y + 2));  // Move forward two squares
            }
            if (y >= 7) {
                for(int i = 1; i < boardSize; i++) {
                    ls.add(new Point(x + i, y)); // Move right
                    ls.add(new Point(x - i, y)); // Move left
                    ls.add(new Point(x, y + i)); // Move down
                    ls.add(new Point(x, y - i)); // Move up
                    ls.add(new Point(x + i, y + i)); // Move down right
                    ls.add(new Point(x - i, y - i)); // Move up left
                    ls.add(new Point(x + i, y - i)); // Move up right
                    ls.add(new Point(x - i, y + i)); // Move down left
                }
            } else {
                if( board.findChess(x - 1, y + 1) != null && board.findChess(x - 1, y + 1).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - 1, y + 1));
                }
                if( board.findChess(x + 1, y + 1) != null && board.findChess(x + 1, y + 1).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + 1, y + 1));
                }
            }
        }
        return ls;
    }
}