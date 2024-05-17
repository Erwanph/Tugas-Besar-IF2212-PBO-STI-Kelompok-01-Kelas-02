package main.Zombies;
import main.Zombie;

public class ScreenDoorZombie extends Zombie implements ZombieWithItem{
    private boolean itemStatus;

    public ScreenDoorZombie(){
        super("ScreenDoor Zombie", 175, 5, 1, 100, false, false);
    }
    
    public boolean getItemStatus(){ 
        return itemStatus;
    }

    public void setItemStatus(){

    }
}
