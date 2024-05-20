package src.code.entity.plant;

import src.code.entity.Entity;
import src.code.main.GamePanel;

public class Plant extends Entity{
    // set to protected for child-class access
    protected int cost;
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected int range;
    protected int cooldown;

    // set to protected to avoid being constructed publicly
    protected Plant(int Position_X, int Position_Y, GamePanel gamePanel)
    {
        super(Position_X, Position_Y, gamePanel);
    }
}
