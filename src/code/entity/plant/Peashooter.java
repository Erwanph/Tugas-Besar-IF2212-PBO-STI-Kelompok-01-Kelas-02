package src.code.entity.plant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.func.GameManager;

public class Peashooter extends Plant implements GameObject {

    private Image[] texture;
    private Point[][] positionArray;
    private int curFrame = 0; // current frame to be drawn
    private int frameCount = 0; // internal clock (one frame is 0.01 seconds)

    public Peashooter(int Position_X, int Position_Y, GameManager gamePanel) {
        super(Position_X, Position_Y, gamePanel);
        this.width = 80;
        this.height = 80;
        this.name = "Peashooter";
        this.cost = 100;
        this.health = 100;
        this.attack_damage = 25;
        this.attack_speed = 4;
        this.range = -1;
        this.cooldown = 10;
        renderTexture();
        generatePosition();
    }

    private void renderTexture() {
        texture = new Image[77];
        for (int i = 0; i < 77; i++) {
            String path = "src\\assets\\image\\entity\\peashooter\\texture\\";
            if (i < 10) {
                path += "0";
            }
            path += String.valueOf(i);
            path += ".png";
            try {
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                System.out.println("Peashooter's texture NOT FOUND!");
            }
        }

    }

    private void generatePosition() {
        positionArray = new Point[6][9];
        positionArray[0][0] = new Point(254, 189);
        positionArray[0][1] = new Point(331, 182);
        positionArray[0][2] = new Point(411, 180);
        positionArray[0][3] = new Point(490, 184);
        positionArray[0][4] = new Point(570, 186);
        positionArray[0][5] = new Point(649, 185);
        positionArray[0][6] = new Point(725, 193);
        positionArray[0][7] = new Point(801, 193);
        positionArray[0][8] = new Point(872, 199);

        positionArray[1][0] = new Point(251, 280);
        positionArray[1][1] = new Point(329, 281);
        positionArray[1][2] = new Point(406, 284);
        positionArray[1][3] = new Point(487, 285);
        positionArray[1][4] = new Point(568, 287);
        positionArray[1][5] = new Point(647, 285);
        positionArray[1][6] = new Point(725, 286);
        positionArray[1][7] = new Point(797, 289);
        positionArray[1][8] = new Point(875, 287);

        // TODO : GENERATE POSITION ABOVE LILYPAD
        positionArray[4][0] = new Point(255, 535);
        positionArray[4][1] = new Point(336, 541);
        positionArray[4][2] = new Point(412, 542);
        positionArray[4][3] = new Point(492, 492);
        positionArray[4][4] = new Point(572, 572);
        positionArray[4][5] = new Point(647, 647);
        positionArray[4][6] = new Point(725, 725);
        positionArray[4][7] = new Point(806, 806);
        positionArray[4][8] = new Point(889, 889);

        positionArray[5][0] = new Point(258, 621);
        positionArray[5][1] = new Point(335, 622);
        positionArray[5][2] = new Point(418, 625);
        positionArray[5][3] = new Point(497, 616);
        positionArray[5][4] = new Point(574, 628);
        positionArray[5][5] = new Point(601, 612);
        positionArray[5][6] = new Point(732, 615);
        positionArray[5][7] = new Point(810, 626);
        positionArray[5][8] = new Point(891, 616);
    }

    public Point getPosition(int i, int j) {
        return positionArray[i][j];
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
