package main;

public abstract class Zombie extends GameObject {
    private int speed;
    private int attackSpeed;
    private int attackDamage;
    private boolean isSlowed;
    private boolean isAquatic;


    public Zombie(String name, int health, int speed, int attackSpeed, int attackDamage, boolean isSlowed, boolean isAquatic){
        super(name, health);
        this.speed = speed;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.isSlowed = isSlowed;
        this.isAquatic = isAquatic;
    }
    public int getSpeed(){

    }
}
