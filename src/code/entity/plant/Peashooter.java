package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.main.GamePanel;

public class Peashooter extends Plant implements GameObject{
    // Peashooter's texture (gif)
    private Image[] texture;
    private int curFrame = 0; // current frame to draw
    private int frameCount = 0; // count variable

    public Peashooter(int Position_X, int Position_Y, GamePanel gamePanel)
    {
        super(Position_X, Position_Y, gamePanel);
        this.width = 100;
        this.height = 100;
        this.name = "Peashooter";
        this.cost = 100;
        this.health = 100;
        this.attack_damage = 25;
        this.attack_speed = 4;
        this.range = -1;
        this.cooldown = 10;
        renderTexture();
    }

    private void renderTexture()
    {
        // load texture images from path
        texture = new Image[77];
        for(int i = 0; i < 77; i++)
        {
            String path = "src\\assets\\image\\entity\\peashooter\\texture\\";
            if(i < 10) path += "0";
            path += String.valueOf(i);
            path += ".png";
            try {
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException e) {System.out.println("Peashooter's texture NOT FOUND!");}
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        // draw animation every 2 frame (0.02 seconds)
        g2D.drawImage(texture[curFrame], Position_X, Position_Y, Position_X+width, Position_Y+height, 0,0,352,355, gamePanel);
        // keep track of frameCount
        frameCount++;
        if(frameCount == 2)
        {
            frameCount = 0;
            curFrame = (curFrame+1)%77; // prepare to draw next frame
        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
