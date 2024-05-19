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
import src.code.entity.Peashooter;

public class Game extends JPanel implements Runnable {
    // 1000 x 750 screen size
    public final int screenWidth = 1000;
    public final int screenHeight = 750;

    // background images
    private Image gameplayBackground;

    Peashooter x = new Peashooter(this, 320, 180);

    // game loop
    Thread gameLoop;

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // optimization
        loadImage();
    }

    private void loadImage() {
        try {
            gameplayBackground = ImageIO.read(new File("src\\assets\\image\\background\\GameplayBackground.png"));
        } catch (IOException ex) {
            System.out.println("GameplayBackground Image NOT FOUND!");
        }
    }

    public void startGame() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void run() {
        // redraw frame every 0.01 second or 100fps
        double interval = 10000000;
        // delta is the frame that should be drawn in the elapsed time
        double delta = 0;
        // declare time
        long curTime, lastTime = System.nanoTime();

        while (gameLoop != null) {
            // get curTime
            curTime = System.nanoTime();
            // update delta
            delta += (curTime-lastTime)/interval;
            // update lastTime
            lastTime = curTime;

            // draw frame
            if(delta >= 1)
            {
                repaint();
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
        g2D.drawImage(gameplayBackground, 0, 0, this);

        x.draw(g2D);
        
        // memory :)
        g2D.dispose();
    }
}
