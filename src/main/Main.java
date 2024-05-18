package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // initiate main frame
        JFrame window = new JFrame();
        Mainframe gamePanel = new Mainframe();

        window.setTitle("MICHAEL VS LALAPAN");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();
    }
}
