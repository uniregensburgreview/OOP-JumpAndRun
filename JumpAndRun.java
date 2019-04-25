import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import de.ur.mi.graphics.Rect;
import de.ur.mi.graphicsapp.GraphicsApp;

import java.awt.event.KeyEvent;

/**
 * Jump and Run Game:
 * A player has to jump across pits. If the player collides with a pit the game is stopped.
 * Game consisting of a player, a ground on which the player is standing and obstacles (pits).
 *
 * Created by Alexander Bazo on 06/11/15.
 *
 * Sounds in data.assets:
 *  Jump-Sound by Mike Koenig (http://soundbible.com/1601-Mario-Jumping.html)
 *  Fall-Sound by mind_noise (https://archive.org/details/WilhelmScreamSample)
 */
public class JumpAndRun extends GraphicsApp {

    private static final int FRAME_RATE = 60;

    /* KeyCodes */
    private static final int VK_SPACE = 32;

    /* private constants for Label */
    private static final int END_SIZE = 130;


    private Ground ground;
    private Player player;
    private Rect playerBounds;
    private PitManager pit;
    
    @Override
    public void setup() {
        setupCanvas();
        startGame();
    }

    private void setupCanvas() {
        size(Configuration.CANVAS_WIDTH, Configuration.CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth(4);
    }

    private void drawBackground() {
        background(Configuration.BACKGROUND_COLOR);
    }

    private void startGame(){
        ground = new Ground();
        player = new Player();
        pit = new PitManager();
        playerBounds = player.getPlayerBounds();
    }

    public void draw(){
        drawBackground();
        playerBounds = player.getPlayerBounds();
        ground.draw();
        player.update();
        player.draw();
        playerBounds.draw();
        pit.createNewPit();
        pit.update();
        pit.draw();
        playerFallsInPit();
    }

    /* Draws "game over" if the player falls in a pit */
    private void playerFallsInPit() {
        if (pit.checkIfPlayerFallsInPit(playerBounds)) {
            player.fallsInPit();
            endGame();
        }
    }

    private void endGame() {
        double xPos = 0;
        double yPos = Configuration.CANVAS_HEIGHT / 2;
        String text = "GAME OVER";
        Color fontColor = Color.BLACK;
        Label end = new Label(xPos, yPos, text, fontColor);
        end.setFontSize(END_SIZE);
        end.draw();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_SPACE:
                player.jump();
                break;
        }
    }
}
