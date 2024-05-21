package src.code.main;

import javax.swing.JFrame;

public class Main {

    private static JFrame frame;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        // Initiate frame that contains the game
        frame = new JFrame();
        // Initiate the game
        gamePanel = new GamePanel();

        // window's settings
        frame.setTitle("MICHAEL VS LALAPAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // add game to the window
        frame.add(gamePanel);
        frame.pack();

        // preview windowW
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // start game
        gamePanel.startGame();
    }
}
