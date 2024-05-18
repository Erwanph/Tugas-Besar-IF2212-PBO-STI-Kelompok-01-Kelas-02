package main.Plants;
import main.Plant;

public class MagnetShroom extends Plant{
    private int abilityCooldown;

    public MagnetShroom(){
        super("Magnet shroom ", 100, 0, 0, 100, 1, 20);
        this.abilityCooldown = 10;
    }

    public int getabilitiyCooldown(){
        return abilityCooldown;
    }
}
