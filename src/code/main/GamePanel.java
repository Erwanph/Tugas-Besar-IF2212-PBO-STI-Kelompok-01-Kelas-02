package src.code.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.code.entity.GameObject;
import src.code.entity.plant.Lilypad;
import src.code.entity.plant.Peashooter;
import src.code.entity.plant.Snowpea;
import src.code.entity.plant.Squash;
import src.code.entity.plant.Sunflower;

public class GamePanel extends JPanel implements Runnable {

    // 1000 x 750 screen size
    public final int SCREEN_WIDTH = 1000;
    public final int SCREEN_HEIGHT = 750;

    // background images
    private Image gameplayBackgroundImage;

    // game loop
    private final Thread GAME_LOOP;

    // list of game object to be rendered
    public List<GameObject> renderList;

    public GamePanel() {
        // set dimension
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // set default background color
        this.setBackground(Color.BLACK);
        // optimization
        this.setDoubleBuffered(true);

        // initialization
        GAME_LOOP = new Thread(this); // initiate game loop
        renderList = new ArrayList<>(); // initiate list of game objects
        renderBackground(); // render game background

        renderList.add(new Peashooter(100, 100, this));
        renderList.add(new Snowpea(300, 100, this));
        renderList.add(new Sunflower(300, 300, this));
        renderList.add(new Squash(500, 100, this));
        renderList.add(new Lilypad(241, 465, this));

    }

    private void renderBackground() {
        try {
            gameplayBackgroundImage = ImageIO.read(new File("src\\assets\\image\\background\\GameplayBackground.png"));
        } catch (IOException ex) {
            System.out.println("GameplayBackgroundImage NOT FOUND!");
        }
    }

    public void startGame() {
        GAME_LOOP.start();
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
        g2D.drawImage(gameplayBackgroundImage, 0, 0, this);

        // draw every component in the render list
        for (Iterator<GameObject> i = renderList.iterator(); i.hasNext();) {
            (i.next()).draw(g2D);
        }

        // memory :)
        {
            g2D.dispose();
        }
    }
}
