import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import de.ur.mi.util.RandomGenerator;

/**
 * This class manages the obstacles that are represented as pits. The rects (pits) are moving horizontal from the right
 * canvas border to the left.
 * It offers the methods to create, move and draw these pits and to check a possible collision with the player.
 */
public class PitManager {

    /* Min and Max width of the pits */
    private int minWidth = 800 / 10;
    private int maxWidth = 800 / 6;

    private Rect pit;
    private RandomGenerator random;
    private double dx; //horizontal speed of the pit
    private double dy; // vertical speed of the pit
    private Counter counter;
 
    private boolean fallsInPit;

    public PitManager() {
        initRandomGenerator();
        int minSpeed = -6;
        int maxSpeed = -8;
        initSpeed(minSpeed, maxSpeed);
        newPit();
        counter = new Counter(10, 50);
    }

    /* Creates a new pit every time the current pit is disappearing of the very left end of the Canvas */
    public void createNewPit() {
        if(pit.getRightBorder() <= 0) {
            newPit();
        }
    }

    /* Creates an obstacle for the jumper represented as a pit with a random width */
    private void newPit() {
        double xPos = Configuration.GROUND_WIDTH;
        double yPos = Configuration.GROUND_Y_POS;
        int width = random.nextInt(minWidth, maxWidth);
        int height = Configuration.GROUND_HEIGHT + 10;
        Color color = Configuration.BACKGROUND_COLOR;
        pit = new Rect(xPos, yPos, width, height, color);
    }

    /* RandomGenerator is used for the width and speed of the pits */
    private void initRandomGenerator() {
        random = RandomGenerator.getInstance();
    }


    private void initSpeed(double minSpeed, double maxSpeed) {
        dx = random.nextDouble(minSpeed, maxSpeed);
        dy = 0;
    }

    /* Is moving the pits horizontal to create a running effect of the player */
    public void update() {
        pit.move(dx, dy);
    }


    public void draw() {
        pit.setBorderColor(Configuration.BACKGROUND_COLOR);
        pit.draw();
        counter.draw();
    }

    /** Checks the collision of a pit with a player. The player collides with the pit when the player is completely
     * above the pit and the height of the player is equalling the ground height. In the case of a collision the
     * pits stop moving and the Counter is giving back a high score.
     * If the player does not collide with the pit, the counter is adding points.
     */
    public boolean checkIfPlayerFallsInPit(Rect playerBounds) {
        fallsInPit = false;
        if(playerBounds.getLeftBorder() >= pit.getLeftBorder() && playerBounds.getRightBorder() <= pit.getRightBorder()
                && playerBounds.getBottomBorder() >= Configuration.GROUND_Y_POS) {
            dx = 0;
            counter.highScore();
            fallsInPit = true;
        }
        if (playerBounds.getLeftBorder() >= pit.getLeftBorder() && playerBounds.getRightBorder() <= pit.getRightBorder()
                && playerBounds.getBottomBorder() <= Configuration.GROUND_Y_POS) {
            counter.add();
        }
        return fallsInPit;
    }
}

