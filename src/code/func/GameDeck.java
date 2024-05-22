package src.code.func;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameDeck {

    private GamePanel gamePanel;
    private Image deckImage;
    private Class<?>[] deck;
    private Image[] cardTexture;
    // private int sun = 50;

    public GameDeck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.deck = new Class<?>[6];
        this.cardTexture = new Image[6];
        try {
            deckImage = ImageIO.read(new File("src\\assets\\image\\func\\DeckImage.png"));
        } catch (IOException ex) {
            System.out.println("DeckImage NOT FOUND!");
        }
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(deckImage, 0, 0, 565, 100, 0, 0, 2210, 450, gamePanel);
        for (int i = 0; i < 6; i++) {
            g2D.drawImage(cardTexture[i], 95 + i * 77, 5, 172 + i * 77, 95, 0, 0, 50, 70, gamePanel);
        }
    }
}
