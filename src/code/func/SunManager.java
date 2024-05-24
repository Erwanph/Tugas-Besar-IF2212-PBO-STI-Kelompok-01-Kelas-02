package src.code.func;

import java.util.Random;
import src.code.entity.obj.Sun;

public class SunManager implements Runnable {

    private final GameManager gameManager;
    private final Thread SUN_GENERATOR;
    private final Random rand;
    private int nextGenerate;
    private int sun;

    public SunManager(GameManager gameManager) {
        this.gameManager = gameManager;
        SUN_GENERATOR = new Thread(this);
        rand = new Random();
        nextGenerate = rand.nextInt(5, 11);
        sun = 100;
        startGenerating();
    }

    public synchronized void incrementSun(int x, int y) {
        gameManager.addObject(new Sun(x, y, gameManager));
        sun += 25;
    }

    public synchronized void decrementSun(int x) {
        sun -= x;
    }

    public final int getSun() {
        return sun;
    }

    private void startGenerating() {
        SUN_GENERATOR.start();
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        long end;
        while (SUN_GENERATOR != null) {
            end = System.nanoTime();
            if (((end - start) / 1000000000.0) > nextGenerate) {
                if (gameManager.getGameState().equals("GAME")) {
                    int x = rand.nextInt(10, 990);
                    int y = rand.nextInt(10, 740);
                    incrementSun(x, y);
                }
                nextGenerate = rand.nextInt(5, 11);
                start = end;
            }
        }
    }
}
