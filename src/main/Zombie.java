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
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getAttackSpeed(){ 
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }

    public int getAttackDamage(){ 
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }

    public boolean getIsSlowed(){
        return isSlowed;
    }

    public void setIsSlowed(boolean isSlowed){
        this.isSlowed = isSlowed;
    }

    public boolean getIsAquatic(){
        return isAquatic;
    }
}
