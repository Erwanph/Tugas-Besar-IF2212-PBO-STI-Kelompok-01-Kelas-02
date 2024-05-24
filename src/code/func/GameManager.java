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
    public final SunManager sunManager;
    public final ZombieManager zombieManager;
    public final CollisionChecker collisionChecker;
    // List of all Plants in the game
    public List<Class<?>> plantAlmanac;
    // BackgroundManager handle the behind-the-screen operation
    public BackgroundManager backgroundManager;
    // DeckManager handle the operations within game deck
    public DeckManager deckManager;
    // objectList contains all the game object
    public List<GameObject> objectList;
    public List<GameObject> insertList;
    // positionArray contains the placing coordinate for every grid
    public Point[][] positionArray;
    public int[][] elementInGrid;
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
        sunManager = new SunManager(this);
        zombieManager = new ZombieManager(this);
        collisionChecker = new CollisionChecker(this);
        // Generate plantAlmanac
        generateAlmanac();
        // Generate positionArray
        generatePositionArray();
        // Initiate backgroundManager
        backgroundManager = new BackgroundManager(this);
        // Initiate deckManager
        deckManager = new DeckManager(this);
        // Initiate objectList
        objectList = new ArrayList<>();
        insertList = new ArrayList<>();

        elementInGrid = new int[6][9];

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

    private void generatePositionArray() {
        positionArray = new Point[6][9];
        positionArray[0][0] = new Point(252, 187);
        positionArray[0][1] = new Point(331, 190);
        positionArray[0][2] = new Point(414, 188);
        positionArray[0][3] = new Point(487, 185);
        positionArray[0][4] = new Point(570, 186);
        positionArray[0][5] = new Point(649, 185);
        positionArray[0][6] = new Point(725, 193);
        positionArray[0][7] = new Point(801, 193);
        positionArray[0][8] = new Point(872, 199);

        positionArray[1][0] = new Point(251, 280);
        positionArray[1][1] = new Point(329, 281);
        positionArray[1][2] = new Point(406, 284);
        positionArray[1][3] = new Point(487, 285);
        positionArray[1][4] = new Point(568, 287);
        positionArray[1][5] = new Point(647, 285);
        positionArray[1][6] = new Point(725, 286);
        positionArray[1][7] = new Point(797, 289);
        positionArray[1][8] = new Point(875, 287);

        positionArray[2][0] = new Point(251, 380);
        positionArray[2][1] = new Point(329, 380);
        positionArray[2][2] = new Point(406, 380);
        positionArray[2][3] = new Point(487, 380);
        positionArray[2][4] = new Point(568, 380);
        positionArray[2][5] = new Point(647, 380);
        positionArray[2][6] = new Point(725, 380);
        positionArray[2][7] = new Point(797, 380);
        positionArray[2][8] = new Point(875, 380);

        positionArray[3][0] = new Point(251, 470);
        positionArray[3][1] = new Point(329, 470);
        positionArray[3][2] = new Point(406, 470);
        positionArray[3][3] = new Point(487, 470);
        positionArray[3][4] = new Point(568, 470);
        positionArray[3][5] = new Point(647, 470);
        positionArray[3][6] = new Point(725, 470);
        positionArray[3][7] = new Point(797, 470);
        positionArray[3][8] = new Point(875, 470);

        positionArray[4][0] = new Point(255, 535);
        positionArray[4][1] = new Point(336, 541);
        positionArray[4][2] = new Point(412, 542);
        positionArray[4][3] = new Point(492, 535);
        positionArray[4][4] = new Point(572, 535);
        positionArray[4][5] = new Point(653, 535);
        positionArray[4][6] = new Point(725, 535);
        positionArray[4][7] = new Point(806, 535);
        positionArray[4][8] = new Point(889, 535);

        positionArray[5][0] = new Point(258, 621);
        positionArray[5][1] = new Point(335, 622);
        positionArray[5][2] = new Point(418, 625);
        positionArray[5][3] = new Point(497, 616);
        positionArray[5][4] = new Point(574, 628);
        positionArray[5][5] = new Point(653, 620);
        positionArray[5][6] = new Point(732, 615);
        positionArray[5][7] = new Point(810, 626);
        positionArray[5][8] = new Point(891, 616);
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
        objectList.addAll(insertList);
        insertList.clear();
        // update every GameObject in the render list
        for (GameObject cur : objectList) {
            cur.update();
        }
        // Check for interaction
        for (Iterator<GameObject> i = objectList.iterator(); i.hasNext();) {
            GameObject cur = i.next();
            collisionChecker.checkCollision(cur);
            if (!cur.isAlive()) {
                i.remove();
            }
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
        }
    }

    public synchronized void addObject(GameObject x) {
        insertList.add(x);
    }

    // Getter for planting position for a specific grid
    public final Point getPlantingPosition(int i, int j) {
        return positionArray[i][j];
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
        System.out.printf("%d %d\n", e.getX(), e.getY());
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
