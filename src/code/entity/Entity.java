package src.code.entity;

import src.code.func.GameManager;

public class Entity {

    // set to protected for child-class access
    protected GameManager gamePanel;
    public int Position_X;
    public int Position_Y;
    protected int width;
    protected int height;
    protected String name;

    // set to protected to avoid being constructed publicly
    protected Entity(int Position_X, int Position_Y, GameManager gamePanel) {
        this.Position_X = Position_X;
        this.Position_Y = Position_Y;
        this.gamePanel = gamePanel;
    }
}
