package src.code.entity;

import java.awt.Graphics2D;
import src.code.main.Game;

public abstract class Entity {
    // set to protected for child-class access
    protected  int posX, posY; // Entity position in the game
    protected  Game game; // game data

    // to avoid being constructed publicly
    protected Entity(Game game, int x, int y)
    {
        this.game = game;
        this.posX = x;
        this.posY = y;
    }

    // draw entity to game
    public abstract void draw(Graphics2D g);
    // update entity data per frame
    public abstract void update(Game game);
}
