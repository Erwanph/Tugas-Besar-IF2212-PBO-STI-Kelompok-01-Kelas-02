package src.code.entity.plant;

import src.code.entity.Entity;
import src.code.main.Game;

public abstract class Plant extends Entity{
    // set to protected for child-class access
    protected int cost;
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected int range;
    protected int cooldown;

    protected Plant(Game game, int x, int y)
    {
        super(game, x, y);
    }
}
