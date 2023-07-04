package Model.Characters.Enemies;

import Logic.Stopwatch;

public class Koopa extends Enemy {
    // Fields
    private int frameNumber = 0;
    private static final int frameDelay = 20;
    private Stopwatch deathStopwatch = new Stopwatch(1000);
    private boolean deadActivated = false;

    // Constructor
    public Koopa(int lives, double[] coordinates) {
        super(lives, coordinates, 40,55, new double[]{2.5,0});
    }

    // Methods
    public void addFrame(){
        frameNumber++;
        frameNumber%=2;
    }

    // Setters
    public void setDeadActivated(boolean deadActivated,boolean right) {
        this.deadActivated = deadActivated;
        if (deadActivated){
            height = 33;
            width = 33;
            setY(getY()+7);
            setVx(right?20:-20);
            deathStopwatch.start();
        }
        else{
            height = 40;
            width = 55;
            setY(getY()-7);
            setVx(2.5);
        }
    }

    // Getters
    public int getFrameNumber() {return frameNumber;}
    public static int getFrameDelay() {return frameDelay;}
    public Stopwatch getDeathStopwatch() {return deathStopwatch;}
    public boolean isDeadActivated() {return deadActivated;}
}