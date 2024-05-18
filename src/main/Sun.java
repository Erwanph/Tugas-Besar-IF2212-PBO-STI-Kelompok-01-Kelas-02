package main;

public class Sun {
    private int totalSun;
    private boolean isSunny;

    public Sun() {
        totalSun = 0;
        isSunny = false;
    }
    public int getTotalSun() {
        return totalSun;
    }
    public void setTotalSun(int totalSun) {
        this.totalSun = totalSun;
    }
    public void spawnSun() {
        totalSun += 25;
    }
}
