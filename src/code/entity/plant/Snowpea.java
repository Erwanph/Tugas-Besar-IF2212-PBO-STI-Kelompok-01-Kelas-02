package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.func.GamePanel;

public class Snowpea extends Plant implements GameObject {

    private Image[] texture;
    private int curFrame = 0; // current frame to be drawn
    private int frameCount = 0; // internal clock (one frame is 0.01 seconds)

    public Snowpea(int Position_X, int Position_Y, GamePanel gamePanel) {
        super(Position_X, Position_Y, gamePanel);
        this.width = 80;
        this.height = 80;
        this.name = "Snowpea";
        this.cost = 175;
        this.health = 100;
        this.attack_damage = 25;
        this.attack_speed = 4;
        this.range = -1;
        this.cooldown = 10;
        renderTexture();
    }

    private void renderTexture() {
        texture = new Image[77];
        for (int i = 0; i < 77; i++) {
            String path = "src\\assets\\image\\entity\\snowpea\\texture\\";
            if (i < 10) {
                path += "0";
            }
            path += String.valueOf(i);
            path += ".png";
            try {
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                System.out.println("Snowpea's texture NOT FOUND!");
            }
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(texture[curFrame], Position_X, Position_Y, Position_X + width, Position_Y + width, 0, 0, 352, 355, gamePanel);
    }

    @Override
    public void update() {
        // update frame to draw every 0.05 seconds
        if (frameCount == 2) {
            curFrame = (curFrame + 1) % 77;
            frameCount = 0;
        }
        frameCount++;
    }

}
