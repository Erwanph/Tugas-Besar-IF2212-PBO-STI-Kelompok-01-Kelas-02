package src.code.func;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
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

    // game variables
    private final Thread GAME_LOOP;
    public List<Class<?>> plantAlmanac;
    public BackgroundManager backgroundManager;
    public DeckManager deckManager;
    public List<GameObject> renderList;
    private String gameState;

    Image check;

    public GameManager() {
        // set dimension
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // set default background color
        setBackground(Color.white);
        // optimization
        setDoubleBuffered(true);

        // initialization
        GAME_LOOP = new Thread(this); // initiate game loop
        generateAlmanac(); // generate plant almanac
        backgroundManager = new BackgroundManager(this);
        deckManager = new DeckManager(this);
        renderList = new ArrayList<>(); // initiate list of game objects

        // default game state
        gameState = "SELECT TRANSITION";

        try {
            check = ImageIO.read(new File("src\\assets\\image\\func\\Squash_InGameCard.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void generateAlmanac() {
        plantAlmanac = new ArrayList<>();
        plantAlmanac.add(Peashooter.class);
        plantAlmanac.add(Snowpea.class);
        plantAlmanac.add(Squash.class);
        plantAlmanac.add(Lilypad.class);
        plantAlmanac.add(Sunflower.class);
    }

    public void startGame() {
        GAME_LOOP.start();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void run() {
        // augmented bottleneck to maintain 100fps

        // redraw frame every 0.01 second or 100fps
        double interval = 10000000;
        // delta is the frame that should be drawn in the elapsed time
        double delta = 0;
        // declare time
        long curTime, lastTime = System.nanoTime();

        while (GAME_LOOP != null) {
            curTime = System.nanoTime(); // get curTime
            delta += (curTime - lastTime) / interval; // update delta
            lastTime = curTime;

            // draw frame
            if (delta >= 1) {
                update(); // update every component
                repaint(); // draw game components
                // reset delta
                delta--;
            }
        }
    }

    public void update() {
        // update every component in the render list
        for (Iterator<GameObject> i = renderList.iterator(); i.hasNext();) {
            (i.next()).update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // paint parent
        super.paintComponent(g);

        // get instances of Graphics2D
        Graphics2D g2D = (Graphics2D) g;

        // draw background
        backgroundManager.drawBackground(g2D);
        deckManager.drawDeck(g2D);

        //draw every component in the render list
        for (Iterator<GameObject> i = renderList.iterator(); i.hasNext();) {
            (i.next()).draw(g2D);
        }

        // memory :)
        {
            g2D.dispose();
        }
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("%d %d\n", e.getX(), e.getY());
        switch (getGameState()) {
            case "SELECT TRANSITION" -> {
                setGameState("SELECT");
            }
            case "SELECT" -> {
                backgroundManager.checkClick(e.getX(), e.getY());
                deckManager.handleClick(e.getX(), e.getY());
            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
