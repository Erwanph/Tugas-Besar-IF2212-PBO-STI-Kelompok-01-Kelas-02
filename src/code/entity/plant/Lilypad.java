package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.main.GamePanel;

public class Lilypad extends Plant implements GameObject {

    private Image texture;
    // private int curFrame = 0; // current frame to be drawn
    // private int frameCount = 0; // internal clock (one frame is 0.01 seconds)

    public Lilypad(int Position_X, int Position_Y, GamePanel gamePanel) {
        super(Position_X, Position_Y, gamePanel);
        this.width = 100;
        this.height = 100;
        this.name = "Lilypad";
        this.cost = 100;
        this.health = 100;
        this.attack_damage = 25;
        this.attack_speed = 4;
        this.range = -1;
        this.cooldown = 10;
        renderTexture();
    }

    private void renderTexture() {
        try {
            texture = ImageIO.read(new File("src\\assets\\image\\entity\\lilypad\\Lilypad.png"));
        } catch (IOException e) {
            System.out.println("Lilypad's texture NOT FOUND!");
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(texture, Position_X, Position_Y, Position_X + width, Position_Y + width, 0, 0, 225, 225, gamePanel);
    }

    @Override
    public void update() {
        // TODO : LILYPAD ANIMATION?
    }
}
