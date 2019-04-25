import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Line;
import de.ur.mi.graphics.Rect;

/* This class is used to draw a ground on the canvas on which the player is moving.
 * It consists of a line representing the floor and a Rect representing an underground. */

public class Ground extends Configuration {

    /* private constants */
    private static final Color UNDERGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Color OVERGROUND_COLOR = Color.GREEN;

    /* instance variables */
    private Rect underground;
    private Line overground;

    public Ground(){
        double xStart = 0;
        double yStart = CANVAS_HEIGHT-GROUND_HEIGHT;
        underground = new Rect(xStart, yStart, GROUND_WIDTH, GROUND_HEIGHT, UNDERGROUND_COLOR);
        double xEnd = CANVAS_WIDTH;
        overground = new Line(xStart, yStart, xEnd, yStart, OVERGROUND_COLOR);
    }

    /* Called to draw the ground */
    public void draw(){
        underground.draw();
        overground.setBorderWeight(OVERGROUND_HEIGHT);
        overground.draw();
    }
}
