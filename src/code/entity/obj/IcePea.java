package src.code.entity.obj;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.Entity;
import src.code.entity.GameObject;
import src.code.func.GameManager;

public class IcePea extends Entity implements GameObject {

    private Image texture;
    public boolean alive;

    public IcePea(int Position_X, int Position_Y, GameManager gameManager) {
        super(Position_X, Position_Y, gameManager);
        alive = true;
        renderTexture();
    }

    private void renderTexture() {
        try {
            texture = ImageIO.read(new File("src\\assets\\image\\entity\\snowpea\\obj\\Ice_Pea.png"));
        } catch (IOException e) {
            System.out.println("Pea's texture NOT FOUND!");
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture, Position_X, Position_Y, Position_X + 20, Position_Y + 20, 0, 0, 33, 33, gameManager);
    }

    @Override
    public void update() {
        Position_X++;
        if (Position_X > 1000) {
            alive = false;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRow(int i) {
        row = i;
    }
}
