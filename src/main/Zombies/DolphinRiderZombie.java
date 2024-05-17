package main.Zombies;
import main.Zombie;

public class DolphinRiderZombie extends Zombie implements ZombieWithAbility {
    private boolean abilityStatus;

    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 3, 1, 100, false, true);
        abilityStatus = true;
    }

    public boolean getAbilityStatus(){
        return abilityStatus;
    }

    public void useABility(){


        abilityStatus = false;
    }
}
