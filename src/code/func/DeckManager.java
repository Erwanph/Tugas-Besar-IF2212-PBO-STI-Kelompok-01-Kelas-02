package src.code.func;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DeckManager {

    private final GameManager gameManager;
    public List<Integer> deck;
    public List<Integer> goToCooldown;
    public List<Integer> totalCooldown;
    public List<Integer> cost;

    public DeckManager(GameManager gameManager) {
        this.gameManager = gameManager;
        deck = new ArrayList<>();
        goToCooldown = new ArrayList<>();
        totalCooldown = new ArrayList<>();
        cost = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            goToCooldown.add(0);
            totalCooldown.add(0);
            cost.add(0);
        }
    }

    public void drawDeck(Graphics2D g2D) {
        switch (gameManager.getGameState()) {
            case "SELECT" -> {
                int pos_x = 100, pos_y = 13;
                for (Integer i : deck) {
                    g2D.drawImage(gameManager.backgroundManager.inGameCard[i], pos_x, pos_y, pos_x + 60, pos_y + 83, 0, 0, 50, 70, gameManager);
                    pos_x += 64;
                }
            }
            default -> {
            }
        }

    }
}
