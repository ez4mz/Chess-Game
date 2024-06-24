package XXLChess.chess;

import XXLChess.Board;
import XXLChess.Chess;
import XXLChess.util.Point;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Guard extends Chess {
    public Guard(int x, int y, boolean isWhite, PImage sprite) {
        super(x, y, isWhite, sprite);
        this.value = 5;
    }
    @Override
    public List<Point> getPotentialMoves(Board board) {
        List<Point> ls = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();

        // Add king moves
        ls.add(new Point(x - 1, y - 1)); // Top left
        ls.add(new Point(x, y - 1));     // Top
        ls.add(new Point(x + 1, y - 1)); // Top right
        ls.add(new Point(x - 1, y));     // Left
        ls.add(new Point(x + 1, y));     // Right
        ls.add(new Point(x - 1, y + 1)); // Bottom left
        ls.add(new Point(x, y + 1));     // Bottom
        ls.add(new Point(x + 1, y + 1)); // Bottom right

        // Add knight moves
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
