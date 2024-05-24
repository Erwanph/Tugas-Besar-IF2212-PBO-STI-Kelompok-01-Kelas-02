package src.code.func;

import src.code.entity.GameObject;
import src.code.entity.obj.IcePea;
import src.code.entity.obj.Pea;
import src.code.entity.plant.Peashooter;
import src.code.entity.plant.Plant;
import src.code.entity.plant.Snowpea;
import src.code.entity.plant.Squash;
import src.code.entity.zombie.BasicZombie;
import src.code.entity.zombie.Zombie;

public class CollisionChecker {

    private final GameManager gameManager;

    public CollisionChecker(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void checkCollision(GameObject a) {

        if (a instanceof Peashooter ax) {
            boolean foundEnemies = false;
            for (GameObject b : gameManager.objectList) {
                if (b instanceof BasicZombie bx) {
                    if (ax.row == bx.row) {
                        foundEnemies = true;
                    }
                }
            }
            if (foundEnemies) {
                ax.mode = "ATTACKING";
            } else {
                ax.mode = "IDLE";
            }
        }

        if (a instanceof Snowpea ax) {
            boolean foundEnemies = false;
            for (GameObject b : gameManager.objectList) {
                if (b instanceof BasicZombie bx) {
                    if (ax.row == bx.row) {
                        foundEnemies = true;
                    }
                }
            }
            if (foundEnemies) {
                ax.mode = "ATTACKING";
            } else {
                ax.mode = "IDLE";
            }
        }
        if (a instanceof Zombie ax) {
            boolean foundEnemies = false;
            for (GameObject b : gameManager.objectList) {
                if (b instanceof Plant bx) {
                    if (ax.row == bx.row && ax.Position_X - bx.Position_X < 85) {
                        foundEnemies = true;
                        ax.setMode("ATTACKING");
                        if (ax.attackTime == 100) {
                            bx.reduceHealth(ax.getDamage());
                        }
                    }
                }
            }
            if (!foundEnemies) {
                ax.setMode("WALKING");
            }
        }
        if (a instanceof Pea ax) {
            for (GameObject b : gameManager.objectList) {
                if (b instanceof BasicZombie bx) {
                    if (Math.abs(ax.Position_X - bx.Position_X) < 2 && ax.row == bx.row) {
                        bx.reduceHealth(25);
                        ax.alive = false;
                    }
                }
            }
        }
        if (a instanceof IcePea ax) {
            for (GameObject b : gameManager.objectList) {
                if (b instanceof BasicZombie bx) {
                    if (Math.abs(ax.Position_X - bx.Position_X) < 2 && ax.row == bx.row) {
                        bx.reduceHealth(25);
                        bx.setSlow(400);
                        ax.alive = false;
                    }
                }
            }
        }
        if (a instanceof Squash ax) {
            boolean foundEnemies = false;
            for (GameObject b : gameManager.objectList) {
                if (b instanceof Zombie bx) {
                    if (ax.row == bx.row && bx.Position_X - ax.Position_X < 85 && bx.Position_X > ax.Position_X) {
                        bx.reduceHealth(5000);
                        foundEnemies = true;
                    }
                }
            }
            if (foundEnemies) {
                ax.reduceHealth(100);
            }
        }

    }
}
