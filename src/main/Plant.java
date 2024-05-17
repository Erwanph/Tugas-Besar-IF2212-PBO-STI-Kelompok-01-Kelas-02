package main;

public abstract class Plant extends GameObject{
    private int attackSpeed;
    private int attackDamage;
    private int cost;
    private int range;
    private int cooldown;

    public Plant(String name, int health, int attackSpeed, int attackDamage, int cost, int range, int cooldown){
        super(name, health);
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }
    // Getter and Setter for attackSpeed
    public Integer getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Integer attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    // Getter and Setter for attackDamage
    public Integer getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(Integer attackDamage) {
        this.attackDamage = attackDamage;
    }

    // Getter and Setter for cost
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    // Getter and Setter for range
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    // Getter and Setter for cooldown
    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
}
