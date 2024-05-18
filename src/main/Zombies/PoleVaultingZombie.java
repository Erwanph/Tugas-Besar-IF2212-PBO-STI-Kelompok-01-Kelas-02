package main.Zombies;
import main.Zombie;

public class PoleVaultingZombie extends Zombie implements ZombieWithAbility{
    private boolean abilityStatus;

    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 3, 1, 100, false, false);
        abilityStatus = true;
    }

    public boolean getAbilityStatus(){
        return abilityStatus;
    }

    public void useABility(){


        abilityStatus = false;
    }
    
}
