package src.code.func;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundManager {

    private GameManager gameManager = null;
    private Image[] background;
    public Image[] inGameCard;
    public Image[] inGameAlmanac;
    private int frameCount;
    private int selectedCard;

    public BackgroundManager(GameManager gameManager) {
        this.gameManager = gameManager;
        frameCount = 0;
        selectedCard = -1;
        renderBackground();
    }

    private void renderBackground() {
        background = new Image[2];
        try {
            background[0] = ImageIO.read(new File("src\\assets\\image\\background\\GameplayBackground.png"));
            background[1] = ImageIO.read(new File("src\\assets\\image\\background\\SelectDeckImage.png"));
        } catch (IOException ex) {
            System.out.println("BACKGROUND IMAGES NOT FOUND!");
        }
        inGameCard = new Image[gameManager.plantAlmanac.size()];
        try {
            for (int i = 0; i < gameManager.plantAlmanac.size(); i++) {
                String path = "src\\assets\\image\\func\\";
                path += gameManager.plantAlmanac.get(i).getSimpleName();
                path += "_InGameCard.png";
                inGameCard[i] = ImageIO.read(new File(path));
            }
        } catch (IOException ex) {
            System.out.println("inGameCard IMAGES NOT FOUND!");
        }
        inGameAlmanac = new Image[gameManager.plantAlmanac.size()];
        try {
            for (int i = 0; i < gameManager.plantAlmanac.size(); i++) {
                String path = "src\\assets\\image\\func\\";
                path += gameManager.plantAlmanac.get(i).getSimpleName();
                path += "_InGameAlmanac.png";
                inGameAlmanac[i] = ImageIO.read(new File(path));
            }
        } catch (IOException ex) {
            System.out.println("inGameAlmanac IMAGES NOT FOUND!");
        }

    }

    public void drawBackground(Graphics2D g2D) {
        switch (gameManager.getGameState()) {
            case "GAME" -> {
                g2D.drawImage(background[0], 0, 0, gameManager);
            }
            case "SELECT TRANSITION" -> {
                g2D.drawImage(background[0], 0, 0, gameManager);

                frameCount += 2;
                // transition animation
                if (frameCount < 660) {
                    g2D.drawImage(background[1], 0, 0, frameCount, 750, 0, 0, frameCount, 750, gameManager);
                } else {
                    g2D.drawImage(background[1], 0, 0, gameManager);
                    int totalCard = gameManager.plantAlmanac.size();
                    int toBeDrawn = (frameCount - 660) / 100;
                    if (toBeDrawn == totalCard) {
                        gameManager.setGameState("SELECT");
                    } else {
                        int col = 0;
                        int x = 19, y = 155;
                        while (toBeDrawn > 0) {
                            g2D.drawImage(inGameCard[totalCard - toBeDrawn], x, y, x + 55, y + 77, 0, 0, 50, 70, gameManager);
                            if (col < 9) {
                                x += 61;
                                col++;
                            } else {
                                x = 19;
                                col = 0;
                                y += 83;
                            }
                            toBeDrawn--;
                        }
                    }
                }
            }
            case "SELECT" -> {
                g2D.drawImage(background[0], 0, 0, gameManager);
                g2D.drawImage(background[1], 0, 0, gameManager);
                if (selectedCard != -1) {
                    g2D.drawImage(inGameAlmanac[selectedCard], 600, 30, gameManager);
                }
                int toBeDrawn = gameManager.plantAlmanac.size();
                int totalCard = toBeDrawn;
                int col = 0;
                int x = 19, y = 155;
                while (toBeDrawn > 0) {
                    g2D.drawImage(inGameCard[totalCard - toBeDrawn], x, y, x + 55, y + 77, 0, 0, 50, 70, gameManager);
                    if (col < 9) {
                        x += 61;
                        col++;
                    } else {
                        x = 19;
                        col = 0;
                        y += 83;
                    }
                    toBeDrawn--;
                }
            }

            default ->
                throw new AssertionError();
        }
    }

    public void checkClick(int x, int y) {
        if (x < 16 || x > 565 || y < 152 || y > 614) {
            selectedCard = -1;
        } else {
            int column = (x - 16) / 61;
            int row = (y - 152) / 77;
            int index = column + row * 9;
            System.out.printf("%d %d %d\n", column, row, index);
            if (index < gameManager.plantAlmanac.size()) {
                selectedCard = index;
                gameManager.deckManager.deck.add(index);
            }
        }
    }
}
