package Model.Characters.Enemies;

import Logic.Stopwatch;
import Model.Shots.BowserFireBall;
import Model.Shots.Shot;

import java.util.Random;

public class Bowser extends Enemy{
    // Fields
    private int runningFrameNumber = 0;
    private int firingFrameNumber = 0;
    private static int frameDelay = 20;
    private final double restingX,restingY;
    private boolean triggered = false;
    private boolean toLeft = true;
    private boolean running = false;
    private Stopwatch attackReloadStopwatch = new Stopwatch(1000);
    private boolean dizzy = false;
    private final Stopwatch dizzyStopwatch = new Stopwatch(1000);
    private boolean grabAttacking = false;
    private boolean grabHero = false;
    private boolean jumpAttacking = false;
    private final Stopwatch grabAttackStopwatch = new Stopwatch(1000);
    private int[] runTries = new int[2];
    private final Stopwatch[] reloadStopwatches = new Stopwatch[4];
    private Shot shot = null;
    private boolean fireBallAttacking = false;

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
    public void addFiringFrame(){
        boolean dontDoAdding = false;
        if (isToLeft()){
            if (firingFrameNumber==3) {
                if (new Random().nextDouble()>0.5){
                    shot = new BowserFireBall(new int[]{(int)getX(),(int)getY()+50},false);
                }
                else{
                    shot = new BowserFireBall(new int[]{(int)getX(),(int)getY()+100},false);
                }
                dontDoAdding=true;
            }
        }
        else{
            if (firingFrameNumber==3){
                if (new Random().nextDouble()>0.5){
                    shot = new BowserFireBall(new int[]{(int)getX(),(int)getY()+50},true);
                }
                else{
                    shot = new BowserFireBall(new int[]{(int)getX(),(int)getY()+100},true);
                }
                dontDoAdding=true;
            }
        }
        if (!dontDoAdding){
            firingFrameNumber++;
        }
        else{
            firingFrameNumber=0;
            reloadStopwatches[2].start();
            getAttackReloadStopwatch().start();
            fireBallAttacking=false;
        }
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
            attackReloadStopwatch.start();
            reloadStopwatches[0] = new Stopwatch(1000);
            reloadStopwatches[1] = new Stopwatch(1000);
            reloadStopwatches[2] = new Stopwatch(1000);
            reloadStopwatches[3] = new Stopwatch(1000);
            reloadStopwatches[0].start();
            reloadStopwatches[1].start();
            reloadStopwatches[2].start();
            reloadStopwatches[3].start();
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
    public void setShot(Shot shot) {
        this.shot = shot;
    }
    public void setFireBallAttacking(boolean fireBallAttacking) {
        this.fireBallAttacking = fireBallAttacking;
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
    public Stopwatch[] getReloadStopwatches() {
        return reloadStopwatches;
    }
    public Shot getShot() {
        return shot;
    }
    public boolean isFireBallAttacking() {
        return fireBallAttacking;
    }
    public int getFiringFrameNumber() {
        return firingFrameNumber;
    }
    public Stopwatch getAttackReloadStopwatch() {
        return attackReloadStopwatch;
    }
}
