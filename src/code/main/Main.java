package src.code.main;

import javax.swing.JFrame;

public class Main 
{
    public static void main(String[] args) {
        // Initiate frame that contains the game
        JFrame window = new JFrame();
        // Initiate the game
        Game game = new Game();

        // window's settings
        window.setTitle("MICHAEL VS LALAPAN");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        // add game to the window
        window.add(game);
        window.pack();

        // preview windowW
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // start game
        game.startGame();
    }
}