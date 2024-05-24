package src.code.func;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import src.code.entity.GameObject;
import src.code.entity.plant.Lilypad;
import src.code.entity.plant.Peashooter;
import src.code.entity.plant.Snowpea;
import src.code.entity.plant.Squash;
import src.code.entity.plant.Sunflower;

public class GameManager extends JPanel implements Runnable, MouseInputListener {

    // 1000 x 750 screen size
    public final int SCREEN_WIDTH = 1000;
    public final int SCREEN_HEIGHT = 750;

    // Game Thread
    private final Thread GAME_LOOP;
    // List of all Plants in the game
    public List<Class<?>> plantAlmanac;
    // BackgroundManager handle the behind-the-screen operation
    public BackgroundManager backgroundManager;
    // DeckManager handle the operations within game deck
    public DeckManager deckManager;
    // objectList contains all the game object
    public List<GameObject> objectList;
    // positionArray contains the placing coordinate for every grid
    public Point[][] positionArray;
    // Game state
    private String gameState;

    // Constructor
    public GameManager() {
        // Set dimension
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // Set default background color
        setBackground(Color.white);
        // Rendering Optimization
        setDoubleBuffered(true);

        // Initiate GAME_LOOP
        GAME_LOOP = new Thread(this);
        // Generate plantAlmanac
        generateAlmanac();
        // Initiate backgroundManager
        backgroundManager = new BackgroundManager(this);
        // Initiate deckManager
        deckManager = new DeckManager(this);
        // Initiate objectList
        objectList = new ArrayList<>();

        // Default gameState
        setGameState("MENU");
    }

    // Generate the plantAlmanac list
    private void generateAlmanac() {
        plantAlmanac = new ArrayList<>();
        // Contains all implemented plant classes
        plantAlmanac.add(Peashooter.class);
        plantAlmanac.add(Snowpea.class);
        plantAlmanac.add(Squash.class);
        plantAlmanac.add(Lilypad.class);
        plantAlmanac.add(Sunflower.class);
    }

    // Start Game
    public void startGame() {
        // Start the Thread
        GAME_LOOP.start();
        // Adding mouse listener to get user's input
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // Thread built-in method
    @Override
    public void run() {
        // Augmented bottleneck to maintain 100fps

        // Redraw frame every 0.01 second or 100fps
        double interval = 10000000;
        // Delta is the frame that should be drawn in the elapsed time
        double delta = 0;
        // Declare current time and last draw time
        long curTime, lastTime = System.nanoTime();

        while (GAME_LOOP != null) {
            // Get current time
            curTime = System.nanoTime();
            // Update delta
            delta += (curTime - lastTime) / interval;
            // Update last draw time
            lastTime = curTime;
            // Draw frame
            if (delta >= 1) {
                update(); // Update every component
                repaint(); // Draw game components
                // Reset delta
                delta--;
            }
        }
    }

    // Update method
    private void update() {
        // update every GameObject in the render list
        for (Iterator<GameObject> i = objectList.iterator(); i.hasNext();) {
            (i.next()).update();
        }
    }

    // JPanel built-in paintComponent method
    @Override
    public void paintComponent(Graphics g) {
        // Paint Parent
        super.paintComponent(g);

        // Get instances of Graphics2D
        Graphics2D g2D = (Graphics2D) g;

        // Draw background
        backgroundManager.drawBackground(g2D);
        // Draw deck
        deckManager.drawDeck(g2D);

        // Draw all GameObject in the objectList
        for (Iterator<GameObject> i = objectList.iterator(); i.hasNext();) {
            (i.next()).draw(g2D);
        }

        // Memory friendly :)
        {
            g2D.dispose();
        }
    }

    // gameState getter
    public final String getGameState() {
        return gameState;
    }

    // gameState setter
    public final void setGameState(String gameState) {
        this.gameState = gameState;
    }

    // Built-in MouseInputListener methods
    @Override
    public final void mouseClicked(MouseEvent e) {
        backgroundManager.handleClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.printf("PRESSED IN X : %d Y : %d\n", e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // System.out.printf("RELEASED IN X = %d Y = %d\n", e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.printf("ENTERED IN X = %d Y = %d\n", e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // System.out.printf("EXITED IN X = %d Y = %d\n", e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.printf("DRAGGED IN X = %d Y = %d\n", e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.printf("MOVED IN X = %d Y = %d\n", e.getX(), e.getY());
    }
}
