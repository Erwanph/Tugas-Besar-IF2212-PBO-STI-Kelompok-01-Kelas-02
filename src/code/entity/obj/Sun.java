package src.code.entity.obj;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.Entity;
import src.code.entity.GameObject;
import src.code.func.GameManager;

public class Sun extends Entity implements GameObject {

    private Image texture;
    private boolean alive;

    public Sun(int x, int y, GameManager gameManager) {
        super(x, y, gameManager);
        alive = true;
        renderTexture();
    }

    private void renderTexture() {
        try {
            texture = ImageIO.read(new File("src\\assets\\image\\entity\\sun\\Sun.png"));
        } catch (IOException ex) {
            System.out.println("Sun's Texture NOT FOUND!");
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, Position_X, Position_Y, Position_X + 70, Position_Y + 70, 0, 0, 177, 177, gameManager);
    }

    @Override
    public void update() {
        if (alive) {
            int movX, movY;
            movX = Math.ceilDiv(Position_X - 27, 200);
            movY = Math.ceilDiv(Position_Y - 20, 200);
            Position_X -= movX;
            Position_Y -= movY;
            if (movX == 0 && movY == 0) {
                alive = false;
            }
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getCooldown() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCost() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return "Sun";
    }

    @Override
    public void setRow(int i) {
        row = i;
    }
}
