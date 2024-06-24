package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Chancellor extends Chess {

    public Chancellor(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value = 8.5;
    }
    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<Point>();
        int x = point.getX();
        int y = point.getY();
        int boardSize = 14;
        // Add rook moves
        // Add rook moves
        // Move right
        for(int i = 1; i < boardSize; i++) {
            if(board.findChess(x + i, y) != null) {
                if(board.findChess(x + i, y).isWhite() != this.isWhite()) {
                    ls.add(new Point(x + i, y));
                }
                break;
            }
            ls.add(new Point(x + i, y));
        }
        // Move left
        for(int i = 1; i < boardSize; i++) {
            if(board.findChess(x - i, y) != null) {
                if(board.findChess(x - i, y).isWhite() != this.isWhite()) {
                    ls.add(new Point(x - i, y));
                }
                break;
            }
            ls.add(new Point(x - i, y));
        }
        // Move down
        for(int i = 1; i < boardSize; i++) {
            if(board.findChess(x, y + i) != null) {
                if(board.findChess(x, y + i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x, y + i));
                }
                break;
            }
            ls.add(new Point(x, y + i));
        }
        // Move up
        for(int i = 1; i < boardSize; i++) {
            if(board.findChess(x, y - i) != null) {
                if(board.findChess(x, y - i).isWhite() != this.isWhite()) {
                    ls.add(new Point(x, y - i));
                }
                break;
            }
            ls.add(new Point(x, y - i));
        }

        // Add knight moves // Add knight moves (they can jump over pieces)
        ls.add(new Point(x - 2, y - 1));  // Up-Left
        ls.add(new Point(x - 2, y + 1));  // Up-Right
        ls.add(new Point(x + 2, y - 1));  // Down-Left
        ls.add(new Point(x + 2, y + 1));  // Down-Right
        ls.add(new Point(x - 1, y - 2));  // Left-Up
        ls.add(new Point(x + 1, y - 2));  // Right-Up
        ls.add(new Point(x - 1, y + 2));  // Left-Down
        ls.add(new Point(x + 1, y + 2));  // Right-Down

        return ls;
    }

}
