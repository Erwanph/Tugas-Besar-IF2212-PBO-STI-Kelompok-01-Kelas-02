package src.code.func;

import java.util.Random;
import src.code.entity.zombie.BasicZombie;

public class ZombieManager implements Runnable {

    private final GameManager gameManager;
    private final Thread ZOMBIE_GENERATOR;
    private final Random rand;
    private int elapsedTime;
    private int limit;
    public int generated;

    public synchronized void incrementZombie(int x) {
        generated += x;
    }

    public ZombieManager(GameManager gameManager) {
        this.gameManager = gameManager;
        ZOMBIE_GENERATOR = new Thread(this);
        rand = new Random();
        limit = 10;
        generated = 0;
        elapsedTime = 0;
        startGenerating();
    }

    private void startGenerating() {
        ZOMBIE_GENERATOR.start();
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        long end;
        while (ZOMBIE_GENERATOR != null) {
            end = System.nanoTime();
            if (((end - start) / 1000000000.0) > 3) {
                System.out.println(gameManager.getGameState());
                if (gameManager.getGameState().equals("GAME")) {
                    System.out.println("3 SECOND PASSED");
                    elapsedTime += 3;
                    System.out.println("ELAPSED TIME: " + elapsedTime);
                    if (elapsedTime > 100) {
                        limit = 25;
                    }
                    int prob = rand.nextInt(1, 4);
                    System.out.println("LUCKY NUMBER: " + prob);
                    System.out.println("TOTAL ZOMBIE ON FIELD: " + generated);
                    if (prob == 1) {
                        if (generated <= limit) {
                            int row = rand.nextInt(0, 6);
                            BasicZombie cur = new BasicZombie(900, gameManager.positionArray[row][8].y - 30, gameManager);
                            cur.row = row;
                            cur.setMode("WALKING");
                            gameManager.addObject(cur);
                            incrementZombie(1);
                        }
                    }
                }
                start = end;
            }
        }
    }
}
