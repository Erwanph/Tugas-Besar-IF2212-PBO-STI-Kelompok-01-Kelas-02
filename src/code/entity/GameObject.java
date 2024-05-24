package src.code.entity;

import java.awt.Graphics2D;

public interface GameObject {

    public abstract void draw(Graphics2D g2D);

    public abstract void update();

    public abstract boolean isAlive();

    public abstract int getCooldown();

    public abstract int getCost();

    public abstract String getName();

    public abstract void setRow(int i);
}
