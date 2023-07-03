package Model.Characters.Enemies;

import Model.Physics.Pipe;
import Logic.Stopwatch;

public class Plant extends Enemy {
    // Fields
    transient private Pipe pipe;
    private final Stopwatch stopwatch = new Stopwatch(1);
    private final int timePeriod;
    private boolean dead = false;

    // Constructor
    public Plant(int lives,Pipe pipe,int timePeriod) {
        super(lives, null, 55, 77, null);
        this.pipe = pipe;
        this.timePeriod = timePeriod;
        getStopwatch().start();
    }

    // Setters
    public void setPipe(Pipe pipe) {this.pipe = pipe;}
    public void setDead(boolean dead) {this.dead = dead;}

    // Getters
    public Stopwatch getStopwatch() {return stopwatch;}
    public int getTimePeriod() {return timePeriod;}
    public boolean isDead() {return dead;}
}
