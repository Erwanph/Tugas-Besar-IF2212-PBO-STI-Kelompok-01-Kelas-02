package main.Zombies;
import main.Zombie;

public class JackInTheBoxZombie extends Zombie implements ZombieWithAbility,ZombieWithItem{
    private boolean abilityStatus;
    private boolean itemStatus;
    
    public JackInTheBoxZombie(){
        super("JackInTheBox Zombie", 250, 5, 1, 100, false, false);
        abilityStatus = true;
    }

    public boolean getAbilityStatus(){
        return abilityStatus;
    }

    public void useABility(){


        abilityStatus = false;
    }

    public boolean getItemStatus(){ 
        return itemStatus;
    }

    public void setItemStatus(){

    }
    
}
