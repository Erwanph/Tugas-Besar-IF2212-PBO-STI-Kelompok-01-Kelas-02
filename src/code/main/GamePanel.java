package src.code.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.code.entity.plant.Peashooter;

public class GamePanel extends JPanel implements Runnable {
    // 1000 x 750 screen size
    public final int screenWidth = 1000;
    public final int screenHeight = 750;

    // background images
    private Image gameplayBackgroundImage;    

    // game loop
    private final Thread GAME_LOOP;

    // list of game object
    Peashooter x = new Peashooter(240, 180, this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set dimension
        this.setBackground(Color.BLACK); // set default background color
        this.setDoubleBuffered(true); // optimization
        GAME_LOOP = new Thread(this); // initiate game loop
        renderBackground(); // render game background
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
            // get curTime
            curTime = System.nanoTime();
            // update delta
            delta += (curTime-lastTime)/interval;
            // update lastTime
            lastTime = curTime;

            // draw frame
            if(delta >= 1)
            {
                repaint(); // draw game components
                // reset delta
                delta--;
            }  
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
        
        x.draw(g2D);



        // memory :)
        g2D.dispose();
    }
}
