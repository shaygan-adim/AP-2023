package Model.Characters.Enemies;

import Logic.Stopwatch;

public class Bowser extends Enemy{
    // Fields
    private int runningFrameNumber = 0;
    private static int frameDelay = 20;
    private final double restingX,restingY;
    private boolean triggered = false;
    private boolean toLeft = true;
    private boolean running = false;
    private boolean dizzy = false;
    private final Stopwatch dizzyStopwatch = new Stopwatch(1000);
    private boolean grabAttacking = false;
    private boolean grabHero = false;
    private boolean jumpAttacking = false;
    private final Stopwatch attackStopwatch = new Stopwatch(1000);
    private final Stopwatch grabAttackStopwatch = new Stopwatch(1000);
    private int[] runTries = new int[2];

    // Constructor
    public Bowser(int lives, double[] coordinates) {
        super(lives, coordinates, 244, 222, new double[]{0,0});
        restingX = coordinates[0];
        restingY = coordinates[1];
    }

    // Methods
    public void addRunningFrame(){
        runningFrameNumber++;
        runningFrameNumber%=4;
    }
    public void addRightTry(){
        runTries[0]++;
    }
    public void addLeftTry(){
        runTries[1]++;
    }
    public void resetTries(){
        runTries = new int[2];
    }

    // Setters
    public void setTriggered(boolean triggered) {
        if (triggered){
            width = 293;
            attackStopwatch.start();
        }
        this.triggered = triggered;
    }
    public void setToLeft(boolean toLeft) {
        this.toLeft = toLeft;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void setDizzy(boolean dizzy) {
        this.dizzy = dizzy;
    }
    public void setGrabAttacking(boolean grabAttacking) {
        this.grabAttacking = grabAttacking;
    }
    public void setGrabHero(boolean grabHero) {
        this.grabHero = grabHero;
        if (grabHero){
            height=180;
        }
        else{
            addY(180-244-10);
            height=244;
            resetTries();
        }
    }
    public void setJumpAttacking(boolean jumpAttacking) {
        this.jumpAttacking = jumpAttacking;
        if (jumpAttacking){
            height=316;
        }
        else{
            height=244;
        }
    }

    // Getters
    public boolean isTriggered() {
        return triggered;
    }
    public boolean isToLeft() {
        return toLeft;
    }
    public int getRunningFrameNumber() {return runningFrameNumber;}
    public static int getFrameDelay() {return frameDelay;}
    public double getRestingX() {
        return restingX;
    }
    public double getRestingY() {
        return restingY;
    }
    public boolean isRunning() {
        return running;
    }
    public boolean isDizzy() {
        return dizzy;
    }
    public Stopwatch getDizzyStopwatch() {
        return dizzyStopwatch;
    }
    public boolean isGrabAttacking() {
        return grabAttacking;
    }
    public Stopwatch getAttackStopwatch() {
        return attackStopwatch;
    }
    public Stopwatch getGrabAttackStopwatch() {
        return grabAttackStopwatch;
    }
    public boolean isGrabHero() {
        return grabHero;
    }
    public int[] getRunTries() {
        return runTries;
    }
    public boolean isJumpAttacking() {
        return jumpAttacking;
    }
}
