import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;

/**
 * Creates a counter that is counting the points of the player.
 */
public class Counter {

    private static final Color TEXT_COLOR = Color.BLACK;
    private static final int FONT_SIZE = 30;

    private int points = 0;
    private Label counterLabel;

    public Counter(int xPos, int yPos) {
        counterLabel = new Label(xPos, yPos, "0", TEXT_COLOR);
        counterLabel.setFontSize(FONT_SIZE);
    }

    /* Every time the player passes a pit new points are added */
    public void add() {
        points++;
        counterLabel.setText("" + points);
    }

    /* counts up the point to a high score. Is used at the end of the game, when the player collides */
    public void highScore() {
        counterLabel.setText("Your high score: " + points);
    }

    public void draw() {
        counterLabel.draw();
    }
}
