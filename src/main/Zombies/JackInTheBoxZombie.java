package main.Zombies;
import main.Zombie;

public class JackInTheBoxZombie extends Zombie implements ZombieWithAbility,ZombieWithItem{
    public JackInTheBoxZombie(){
        super("JackInTheBox Zombie", 250, 5, 1, 100, false, false);
    }
    
}
