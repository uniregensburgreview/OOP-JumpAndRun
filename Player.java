// Datei wurde reviewt
import de.ur.mi.graphics.Color; 
import de.ur.mi.graphics.Rect;
import de.ur.mi.sound.Sound;

/**
 * This class represent a player, which is setup as a rect and is able to jump.
 */

public class Player {

    // Hier hättest du die Configuration Klasse benutzen sollen. Config.CANVAS_HEIGHT/2 z.B.
    /* private constants for the jump */
    private static final double MAX_TURN_POINT = CANVAS_HEIGHT/2;
    private static final double X_SPEED = 0;
    private static final double Y_SPEED = -3;

    // Konstanten immer in Capital Letters
    private static final Sound jump_sound = new Sound("/data/assets/jump.wav");

    /* setup for player */
    private static final Color PLAYER_COLOR = Color.BLACK;
    private static final int PLAYER_WIDTH = CANVAS_WIDTH / 50;
    private static final int PLAYER_HEIGHT = GROUND_HEIGHT / 3;

    /* instance variables for player */
    private Rect player; // Represents the player
    private double dx; // Velocity in the x direction
    private double dy; // Velocity in the y direction

    /* instance vars for jump */
    private boolean jumped = false;
    private double jumpY;

    public Player() {
        double xPos =(CANVAS_WIDTH / 8);
        double yPos = GROUND_Y_POS - PLAYER_HEIGHT;
        int width = PLAYER_WIDTH;
        int height = PLAYER_HEIGHT;
        Color color = PLAYER_COLOR;
        player = new Rect(xPos, yPos, width, height, color);
        dx = X_SPEED;
        dy = Y_SPEED;
    }

    /* Called to update, move the yPosition of the player */
    public void update() {
        // jumped muss hier nicht false gesetzt werden, da die Variable damit initialisiert wurde
        if(jumped == false) {
            player.move(dx,dy);
            checkForMaxHeight();
            checkForGround();
        }
        getPlayerBounds().setPosition(player.getX(), player.getY());
    }

    /* When the maximal height of the jump is reached, the y-direction is going to be reversed:
    the player falls back */
    private void checkForMaxHeight() {
        if(player.getY() <= MAX_TURN_POINT) {
            dy = -dy;
        }
    }

    /* When the bottom of the player reaches the ground again, it stops falling down */
    private void checkForGround() {
        if (player.getBottomBorder() >= Configuration.GROUND_Y_POS) {
            dy = 0;
            jumped = false;
        }
    }

    /* Called to draw the player */
    public void draw() {
        player.draw();
    }


    /* controls if player jumps upwards */
    public void jump() {
        if (!jumped) {
            jump_sound.play();
            jumpY = player.getBottomBorder();
            // Lässt sich vereinfachen durch jumpY = jumpY--;
            jumpY = jumpY - 1;
            jumped = true;
        }
    }

    /* Called when player is falling into a pit to make it look like the player falls off the Canvas */
    public void fallsInPit() {
        double currentXPos = player.getX();
        double currentYPos = player.getY();
        currentYPos += 10;
        player.setPosition(currentXPos, currentYPos);
        player.draw();
    }


    /* Creates a rect that represents the current movement of the player */
    public Rect getPlayerBounds() {
        double xPos = player.getX();
        double yPos = player.getY();
        int width = (int) player.getWidth();
        int height  = (int) player.getHeight();
        Color color = player.getColor();
        Rect playerBounds = new Rect(player.getX(), yPos, width, height, color);
        return playerBounds;
    }
}

