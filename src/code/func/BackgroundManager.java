package src.code.func;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import src.code.entity.GameObject;
import src.code.entity.plant.Peashooter;

public class BackgroundManager {

    // GameManager
    private final GameManager gameManager;
    // Array of background images
    private Image[] backgroundImages;
    // Array of in-game plant card images
    public Image[] inGameCard;
    // Array of in-game plant almanac images
    public Image[] inGameAlmanac;
    // Internal timer
    private int frameCount;
    // Card (plant) that is selected by the player
    private int selectedCard;

    // Constructor
    public BackgroundManager(GameManager gameManager) {
        // Assign gameManager
        this.gameManager = gameManager;
        // Reset timer
        frameCount = 0;
        // Default value for selectedCard
        selectedCard = -1;
        // Render images
        renderImage();
    }

    // Method to load all images
    private void renderImage() {
        // Load background images
        backgroundImages = new Image[4];
        // Image[0] = Menu
        // Image[1] = GameplayBackground
        // Image[2] = SelectDeckImage
        // Image[3] = InGameDeck
        try {
            backgroundImages[0] = ImageIO.read(new File("src\\assets\\image\\background\\MenuImage.png"));
            backgroundImages[1] = ImageIO.read(new File("src\\assets\\image\\background\\GameplayBackground.png"));
            backgroundImages[2] = ImageIO.read(new File("src\\assets\\image\\background\\SelectDeckImage.png"));
            backgroundImages[3] = ImageIO.read(new File("src\\assets\\image\\background\\InGameDeck.png"));
        } catch (IOException ex) {
            System.out.println("BACKGROUND IMAGES NOT FOUND!");
        }

        // Load inGameCard
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

        // Load inGameAlmanac
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
            case "MENU" -> {
                g2D.drawImage(backgroundImages[0], 0, 0, 1000, 750, 0, 0, 817, 660, gameManager);
            }
            case "TRANSITION_IN_SELECT" -> {
                // Draw GameplayBackground
                if (frameCount < 1000) {
                    frameCount += 10;
                    g2D.drawImage(backgroundImages[0], 0, 0, 1000, 750, 0, 0, 817, 660, gameManager);
                    g2D.drawImage(backgroundImages[1], 0, 0, frameCount, 750, 0, 0, frameCount, 750, gameManager);
                } else {
                    g2D.drawImage(backgroundImages[1], 0, 0, gameManager);
                    // SelectDeckImage animation
                    frameCount += 5;
                    if (frameCount < 1660) {
                        g2D.drawImage(backgroundImages[2], 0, 0, frameCount - 1000, 750, 0, 0, frameCount - 1000, 750, gameManager);
                    } else {
                        g2D.drawImage(backgroundImages[2], 0, 0, gameManager);
                        // Draw available card
                        int totalCard = gameManager.plantAlmanac.size();
                        // Draw a card every 0.5 seconds
                        int toBeDrawn = (frameCount - 1660) / 100;
                        if (toBeDrawn == totalCard) {
                            // If every card is drawn, change gameState to SELECT
                            gameManager.setGameState("SELECT");
                            // Reset timer
                            frameCount = 0;
                        } else {
                            // 
                            int col = 0;
                            int pos_x = 19, pos_y = 155;
                            while (toBeDrawn > 0) {
                                g2D.drawImage(inGameCard[totalCard - toBeDrawn], pos_x, pos_y, pos_x + 55, pos_y + 77, 0, 0, 50, 70, gameManager);
                                if (col < 9) {
                                    pos_x += 61;
                                    col++;
                                } else {
                                    pos_x = 19;
                                    col = 0;
                                    pos_y += 83;
                                }
                                toBeDrawn--;
                            }
                        }
                    }
                }
            }
            case "TRANSITION_OUT_SELECT" -> {
                frameCount += 5;
                if (frameCount < 660) {
                    g2D.drawImage(backgroundImages[1], 0, 0, gameManager);
                    g2D.drawImage(backgroundImages[2], 0, 0, 660 - frameCount, 750, 0, 0, 660 - frameCount, 750, gameManager);
                } else {
                    gameManager.setGameState("TRANSITION_IN_GAME");
                    frameCount = 0;
                }
            }
            case "SELECT" -> {
                g2D.drawImage(backgroundImages[1], 0, 0, gameManager);
                g2D.drawImage(backgroundImages[2], 0, 0, gameManager);
                if (selectedCard != -1) {
                    g2D.drawImage(inGameAlmanac[selectedCard], 600, 30, gameManager);
                }
                int toBeDrawn = gameManager.plantAlmanac.size();
                int totalCard = toBeDrawn;
                int col = 0;
                int pos_x = 19, pos_y = 155;
                while (toBeDrawn > 0) {
                    g2D.drawImage(inGameCard[totalCard - toBeDrawn], pos_x, pos_y, pos_x + 55, pos_y + 77, 0, 0, 50, 70, gameManager);
                    if (col < 9) {
                        pos_x += 61;
                        col++;
                    } else {
                        pos_x = 19;
                        col = 0;
                        pos_y += 83;
                    }
                    toBeDrawn--;
                }
            }
            case "TRANSITION_IN_GAME" -> {
                g2D.drawImage(backgroundImages[1], 0, 0, gameManager);
                frameCount += 2;
                if (frameCount < 120) {
                    g2D.drawImage(backgroundImages[3], 0, 0, 565, frameCount, 0, 0, 1138, 219, gameManager);
                } else {
                    g2D.drawImage(backgroundImages[3], 0, 0, 565, 120, 0, 0, 1138, 219, gameManager);
                    frameCount += 5;
                    int toBeDrawn = (frameCount - 120) / 100;
                    for (int i = 0; i < toBeDrawn; i++) {
                        g2D.drawImage(inGameCard[gameManager.deckManager.deck.get(i)], 95 + i * 77, 5, 95 + (i + 1) * 77, 110, 0, 0, 50, 70, gameManager);
                    }
                    if (toBeDrawn == gameManager.deckManager.deck.size()) {
                        frameCount = 0;
                        gameManager.setGameState("GAME");
                    }
                }
            }
            case "GAME" -> {
                g2D.drawImage(backgroundImages[1], 0, 0, gameManager);
                g2D.drawImage(backgroundImages[3], 0, 0, 565, 120, 0, 0, 1138, 219, gameManager);
                for (int i = 0; i < gameManager.deckManager.deck.size(); i++) {
                    g2D.drawImage(inGameCard[gameManager.deckManager.deck.get(i)], 95 + i * 77, 5, 95 + (i + 1) * 77, 110, 0, 0, 50, 70, gameManager);
                }
                if (selectedCard != -1) {
                    g2D.setStroke(new BasicStroke(7));
                    g2D.setColor(Color.yellow);
                    g2D.drawRect(95 + selectedCard * 77, 5, 77, 100);
                }
            }
            default -> {
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public final void handleClick(int x, int y) throws SecurityException {
        switch (gameManager.getGameState()) {
            case "MENU" -> {
                if (x >= 609 && x <= 876 && y >= 387 && y <= 448) {
                    gameManager.setGameState("TRANSITION_IN_SELECT");
                }
            }
            case "SELECT" -> {
                if (checkPoint(x, y, 193, 683, 385, 730)) {
                    selectedCard = -1;
                    gameManager.setGameState("TRANSITION_OUT_SELECT");
                } else if (checkPoint(x, y, 16, 152, 565, 614)) {
                    int column = (x - 16) / 61;
                    int row = (y - 152) / 77;
                    int index = column + row * 9;
                    if (index < gameManager.plantAlmanac.size()) {
                        selectedCard = index;
                        if (gameManager.deckManager.deck.size() != 6 && !gameManager.deckManager.deck.contains(index)) {
                            gameManager.deckManager.deck.add(index);
                        }
                    }
                } else if (checkPoint(x, y, 98, 13, 482, 83)) {
                    int deleted = (x - 98) / 64;
                    if (deleted < gameManager.deckManager.deck.size()) {
                        gameManager.deckManager.deck.remove(deleted);
                    }
                } else {
                    selectedCard = -1;
                }
            }
            case "GAME" -> {
                // Click happening inside deck
                if (checkPoint(x, y, 95, 5, 557, 110)) {
                    int index = (x - 95) / 77;
                    if (index < gameManager.deckManager.deck.size()) {
                        selectedCard = index;
                    }
                } // Click happening inside planting area
                else if (checkPoint(x, y, 250, 210, 952, 720)) {
                    int row = (y - 210) / 85;
                    int col = (x - 250) / 78;
                    try {
                        try {
                            gameManager.objectList.add((GameObject) gameManager.plantAlmanac.get(gameManager.deckManager.deck.get(selectedCard)).getConstructor(int.class, int.class, GameManager.class).newInstance(Peashooter.getPosition(row, col).x, Peashooter.getPosition(row, col).y, gameManager));
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.out.println("CANNOT INSTANTIATE");
                        }
                    } catch (NoSuchMethodException ex) {
                        System.out.println("Constructor NOT FOUND!");
                    }
                }

            }
            default -> {
            }
        }

    }

    private boolean checkPoint(int x, int y, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        return x >= topLeftX && x <= bottomRightX && y >= topLeftY && y <= bottomRightY;
    }
}
