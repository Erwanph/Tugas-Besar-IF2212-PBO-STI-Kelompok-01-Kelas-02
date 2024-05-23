package src.code.func;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DeckManager {

    private GameManager gameManager;
    public List<Integer> deck;

    public DeckManager(GameManager gameManager) {
        this.gameManager = gameManager;
        deck = new ArrayList<>();
        deck.add(0);
        deck.add(1);

        deck.add(2);
        deck.add(3);
        deck.add(4);
    }

    public void drawDeck(Graphics2D g2D) {
        int x = 100, y = 14;
        for (Integer i : deck) {
            g2D.drawImage(gameManager.backgroundManager.inGameCard[i], x, y, x + 60, y + 84, 0, 0, 50, 70, gameManager);
            x += 65;
        }

    }

    public void handleClick(int x, int y) {
        if (!(x < 100 || x > 490 || y < 14 || y > 84)) {
            int deleted = (x - 100) / 65;
            if (deleted < deck.size()) {
                deck.remove(deleted);
            }
        }
    }
}
