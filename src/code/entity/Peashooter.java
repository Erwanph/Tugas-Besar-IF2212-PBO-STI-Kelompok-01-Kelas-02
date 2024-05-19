package src.code.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.code.main.Game;

public class Peashooter extends Entity {
    private final Image[] texture = new Image[77]; // Peashooter texture (animation/gif)
    private int frame = 0; // current frame to be drawn

    public Peashooter(Game game, int x, int y) 
    {
        super(game, x, y);
        renderTexture();
    }

    private void renderTexture()
    {
        // render all texture
        for(int i = 0; i < 77; i++)
        {
            // path to peashooters's texture folder
            String path = "src\\assets\\image\\entity\\peashooter\\texture\\";

            // generating path to the i-th texture
            if(i < 10) path += "0";
            path += String.valueOf(i);
            path += ".png";

            try {
                // load texture
                texture[i] = ImageIO.read(new File(path));
            } catch (IOException ex) {System.out.println("Peashooter's Texture NOT FOUND!");}
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // since this method will be called 100 times a second (100 fps),
        // 77 Frame will be drawn smoothly without any augmented bottleneck
        g.drawImage(texture[frame], this.posX, this.posY, this.posX+100, this.posY+100, 0,0,352,355,this.game);
        // move to next frame
        frame = (frame+1)%77;
    }

    @Override
    public void update(Game game) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
