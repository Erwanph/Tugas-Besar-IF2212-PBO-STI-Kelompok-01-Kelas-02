package src.code.main;

import javax.swing.JFrame;
import src.code.func.GameManager;

public class Main {

    // Frame that contains the game
    private static JFrame frame;
    // GameManager control the whole game
    private static GameManager gameManager;

    public static void main(String[] args) {
        // Initiate JFrame
        frame = new JFrame();
        // Initiate GameManager
        gameManager = new GameManager();

        // JFrame's settings
        frame.setTitle("MICHAEL VS LALAPAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Load game into the JFrame
        frame.add(gameManager);
        frame.pack();

        // Preview JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start the game
        gameManager.startGame();
    }
}
