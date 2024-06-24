package XXLChess;


import XXLChess.model.Chess;
import XXLChess.model.chess.Amazon;
import XXLChess.util.Point;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
    Chess amazon;

    @BeforeEach
    public void setup(){
        amazon = new Amazon(1,1,true, null);
    }

    @Test
    public void testGetMethod() {
        assertEquals(amazon.getX(),1);
        assertEquals(amazon.getY(), 1);
    }

    @Test
    public void testSetPoint() {
        amazon.setPoint(new Point(2,1));
        assertEquals(amazon.getX(),2);
    }
}
