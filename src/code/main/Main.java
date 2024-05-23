package src.code.main;

import javax.swing.JFrame;
import src.code.func.GameManager;

public class Main {

    private static JFrame frame;
    private static GameManager gameManager;

    public static void main(String[] args) {
        // Initiate frame that contains the game
        frame = new JFrame();
        // Initiate the game
        gameManager = new GameManager();

        // window's settings
        frame.setTitle("MICHAEL VS LALAPAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // add game to the window
        frame.add(gameManager);
        frame.pack();

        // preview windowW
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // start game
        gameManager.startGame();
    }
}
