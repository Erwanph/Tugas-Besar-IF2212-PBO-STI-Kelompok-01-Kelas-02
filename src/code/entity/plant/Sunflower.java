package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.main.GamePanel;

public class Sunflower extends Plant implements GameObject {

    private Image[] texture;
    private int curFrame = 0; // current frame to be drawn
    private int frameCount = 0; // internal clock (one frame is 0.01 seconds)

    public Sunflower(int Position_X, int Position_Y, GamePanel gamePanel) {
        super(Position_X, Position_Y, gamePanel);
        this.width = 80;
        this.height = 80;
        this.name = "Sunflower";
        this.cost = 50;
        this.health = 50;
        this.attack_damage = 0;
        this.attack_speed = 0;
        this.range = 0;
        this.cooldown = 10;
        renderTexture();
    }

    private void renderTexture() {
        texture = new Image[36];
        for (int i = 0; i < 36; i++) {
            String path = "src\\assets\\image\\entity\\sunflower\\texture\\";
            if (i < 10) {
                path += "0";
            }
            path += String.valueOf(i);
            path += ".png";
            try {
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                System.out.println("Sunflower's texture NOT FOUND!");
            }
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(texture[curFrame], Position_X, Position_Y, Position_X + width, Position_Y + width, 0, 0, 362, 363, gamePanel);
    }

    @Override
    public void update() {
        // update frame to draw every 0.05 seconds
        if (frameCount == 5) {
            curFrame = (curFrame + 1) % 36;
            frameCount = 0;
        }
        frameCount++;
    }
}
