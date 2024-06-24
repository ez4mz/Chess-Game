package XXLChess;


import processing.core.PApplet;
import processing.data.JSONObject;
import processing.event.MouseEvent;
import java.io.File;


public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH * CELLSIZE;

    public static final int FPS = 60;

    public String configPath;

    public Game game;

    private boolean isEKeyPressed = false;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
    public void setup() {
        frameRate(60);
        // load config
        JSONObject config = loadJSONObject(new File(this.configPath));
        game = new Game(config, this);
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    public void keyPressed() {
        if (key == 'e' || key == 'E') {
            isEKeyPressed = true;
            game.endGame();
        }
        if (key == 'r' || key == 'R') {
            JSONObject config = loadJSONObject(new File(this.configPath));
            game = new Game(config, this);
            isEKeyPressed = false;
            }
        }


    /**
     * Receive key released signal from the keyboard.
     */
    public void keyReleased() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.humanMove(mouseX,mouseY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Draw all elements in the game by current frame.
     */
    public void draw() {
        game.draw(this);
        if (isEKeyPressed) {
            fill(255);
            textSize(15);
            text("You resigned", 680, 300);
        }
    }

    // Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
    }

}
