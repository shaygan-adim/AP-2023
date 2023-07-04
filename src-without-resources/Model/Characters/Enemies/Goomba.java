package Model.Characters.Enemies;

import Logic.Stopwatch;

public class Goomba extends Enemy {
    // Fields
    private int frameNumber = 0;
    private static final int frameDelay = 10;
    private Stopwatch deathStopwatch = new Stopwatch(1000);
    private boolean deadActivated = false;

    // Constructor
    public Goomba(int lives, double[] coordinates) {
        super(lives, coordinates, 60,55, new double[]{5,0});
    }

    // Methods
    public void addFrame(){
        frameNumber++;
        frameNumber%=6;
    }

    // Setters
    public void setDeadActivated(boolean deadActivated) {
        this.deadActivated = deadActivated;
        height = 18;
        setY(getY()+42);
        setVx(0);
    }

    // Getters
    public int getFrameNumber() {return frameNumber;}
    public static int getFrameDelay() {return frameDelay;}
    public Stopwatch getDeathStopwatch() {return deathStopwatch;}
    public boolean isDeadActivated() {return deadActivated;}
}
