package main.Plants;
import main.Plant;

public class Magnetshroom extends Plant{
    private int abilityCooldown;

    public Magnetshroom(){
        super("Magnet-shroom ", 100, 0, 0, 100, 1, 20);
        this.abilityCooldown = 10;
    }

    public int getabilitiyCooldown(){
        return abilityCooldown;
    }
}
