package src.code.entity.zombie;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.func.GameManager;

public class BasicZombie extends Zombie implements GameObject {

    private Image[] texture;
    private int curFrame;
    private int frameCount;

    public BasicZombie(int Position_X, int Position_Y, GameManager gameManager) {
        super(Position_X, Position_Y, gameManager);
        health = 125;
        attack_damage = 100;
        mode = "WALKING";
        curFrame = 0;
        frameCount = 0;
        renderTexture();
    }

    private void renderTexture() {
        texture = new Image[19];
        for (int i = 0; i < 19; i++) {
            String path = "src\\assets\\image\\entity\\basic_zombie\\texture\\";
            if (i < 10) {
                path += "0";
            }
            path += String.valueOf(i);
            path += ".png";
            try {
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                System.out.println("Zombie's texture NOT FOUND!");
            }
        }

    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(texture[curFrame], Position_X, Position_Y, gameManager);
    }

    @Override
    public void update() {
        if (slow > 0) {
            if (frameCount % 26 == 0) {
                curFrame = (curFrame + 1) % 10;
                if (mode.equals("WALKING")) {
                    Position_X--;
                }
            }
            slow--;
        } else {
            if (frameCount % 13 == 0) {
                curFrame = (curFrame + 1) % 10;
                if (mode.equals("WALKING")) {
                    Position_X--;
                }
            }
        }

        if (frameCount == 100) {
            frameCount = 0;
        }
        frameCount++;
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

    @Override
    public void setMode(String s) {
        if (s.equals("WALKING")) {
            attackTime = 99;
        }
        if (s.equals("ATTACKING")) {
            attackTime = (attackTime + 1) % 101;
        }
        mode = s;
    }
}
