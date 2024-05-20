package src.code.entity;

import src.code.main.GamePanel;

public class Entity {
    // set to protected for child-class access
    protected GamePanel gamePanel;
    protected int Position_X;
    protected int Position_Y;
    protected int width;
    protected int height;
    protected String name;

    // set to protected to avoid being constructed publicly
    protected Entity(int Position_X, int Position_Y, GamePanel gamePanel)
    {
        this.Position_X = Position_X;
        this.Position_Y = Position_Y;
    }
}
