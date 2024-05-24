package src.code.entity;

import src.code.func.GameManager;

public class Entity {

    // set to protected for child-class access
    protected GameManager gameManager;
    public int Position_X;
    public int Position_Y;
    public int row;
    protected int width;
    protected int height;
    protected String name;

    // set to protected to avoid being constructed publicly
    protected Entity(int Position_X, int Position_Y, GameManager gameManager) {
        this.Position_X = Position_X;
        this.Position_Y = Position_Y;
        this.gameManager = gameManager;
    }
}
