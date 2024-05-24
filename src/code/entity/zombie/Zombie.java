package src.code.entity.zombie;

import src.code.entity.Entity;
import src.code.func.GameManager;

public abstract class Zombie extends Entity {

    // set to protected for child-class use
    protected int health;
    protected int attack_damage;
    protected int attack_speed;
    protected int range;
    protected boolean alive;
    public String mode;
    public int attackTime;
    public int slow;

    // set to protected to avoid being constructed publicly
    protected Zombie(int Position_X, int Position_Y, GameManager gameManager) {
        super(Position_X, Position_Y, gameManager);
        slow = 0;
        alive = true;
    }

    public void reduceHealth(int x) {
        health -= x;
        if (health <= 0) {
            alive = false;
            gameManager.zombieManager.incrementZombie(-1);
        }
    }

    public int getDamage() {
        return attack_damage;
    }

    public abstract void setMode(String s);

    public void setSlow(int x) {
        slow = x;
    }
}
