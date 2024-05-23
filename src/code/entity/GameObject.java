package src.code.entity;

import java.awt.Graphics2D;

public interface GameObject {

    public abstract void draw(Graphics2D g2D);

    public abstract void update();
}
