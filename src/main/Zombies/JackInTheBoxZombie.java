package main.Zombies;
import main.Zombie;

public class JackInTheBoxZombie extends Zombie implements ZombieWithAbility{
    private boolean abilityStatus;
    
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
    
}
