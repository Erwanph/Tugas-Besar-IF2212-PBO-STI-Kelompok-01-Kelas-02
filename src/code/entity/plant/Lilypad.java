package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.func.GameManager;

public class Lilypad extends Plant implements GameObject {

    private Image texture;
    // private int curFrame = 0; // current frame to be drawn
    // private int frameCount = 0; // internal clock (one frame is 0.01 seconds)

    public Lilypad(int Position_X, int Position_Y, GameManager gamePanel) {
        super(Position_X, Position_Y, gamePanel);
        this.width = 80;
        this.height = 80;
        this.name = "Lilypad";
        this.health = 100;
        this.attack_damage = 25;
        this.attack_speed = 4;
        alive = true;

        this.range = -1;

        // Fixing position to next plant
        if (Position_X != -1 && Position_Y != -1) {
            gamePanel.positionArray[(Position_Y - 210) / 85][(Position_X - 250) / 78].x += 5;
            gamePanel.positionArray[(Position_Y - 210) / 85][(Position_X - 250) / 78].y -= 10;
        }

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
        g2d.drawImage(texture, Position_X, Position_Y, Position_X + width, Position_Y + width, 10, 0, 200, 190, gameManager);
    }

    @Override
    public void update() {
        // TODO : LILYPAD ANIMATION?
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getCost() {
        return 25;
    }

    @Override
    public String getName() {
        return "Lilypad";
    }

    @Override
    public void setRow(int i) {
        row = i;
    }
}
