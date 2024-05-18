package main.Plants;
import main.Plant;

public class Lilypad extends Plant{
    private Plant plant;

    public Lilypad(){
        super("LilyPad", 100, 0, 0, 25, 0, 10);
    }

    public Plant getPlant(){
        return plant;
    }

    public void setPlant(Plant plant){
        this.plant = plant;
    }

    public int getTotalHealth(){
        if(this.plant != null){
            return (this.getHealth() + this.plant.getHealth());
        }
        else{
            return this.getHealth();
        }
    }
}
