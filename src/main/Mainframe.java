package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import main.map.MapGenerator;

public class Mainframe extends JPanel implements Runnable{
    // screen size calculations
    public final int gridSize = 64; 
    public final int maxCol = 13;
    public final int maxRow = 9;
    private final int screenWidth = gridSize*maxCol;
    private final int screenHeight = gridSize*maxRow;

    // game loop
    Thread gameThread;
    // map generator
    MapGenerator mapGenerator;

    // constructor
    public Mainframe()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black); // temporary
        this.setDoubleBuffered(true); // optimization
    }
    
    public void startGame()
    {
        mapGenerator = new MapGenerator(this);
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null)
        {
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // generate map
        mapGenerator.drawMap(g2);
        g2.dispose();
    }
}
