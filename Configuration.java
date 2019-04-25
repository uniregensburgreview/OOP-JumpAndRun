import de.ur.mi.graphics.Color;

/**
 * Class which saves the most used (used more than once) Configuration settings of the game as public Constants.
 */

public class Configuration {

    public Configuration(){}

    /* setup canvas */
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;
    public static final Color BACKGROUND_COLOR = Color.BLUE;


    /* Setup Ground */
    public static final int UNDERGROUND_HEIGHT = CANVAS_HEIGHT/4; //
    public static final int OVERGROUND_HEIGHT = UNDERGROUND_HEIGHT/20;
    public static final int GROUND_WIDTH = CANVAS_WIDTH;
    public static final int GROUND_HEIGHT = UNDERGROUND_HEIGHT+(2*OVERGROUND_HEIGHT);
    public static final int GROUND_Y_POS = (CANVAS_HEIGHT-GROUND_HEIGHT)-(OVERGROUND_HEIGHT/2);






}
