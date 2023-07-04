package Model.Characters.Heroes;

import Loading.AudioLoader;
import Model.Characters.Character;
import Logic.Stopwatch;
import Model.Shots.Shot;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

abstract public class Hero extends Character {
    // Fields
    private int score = 0;
    private int coin = 0;
    private boolean visible = true;
    private boolean standingOnSomething = false;
    private boolean jumping = false;
    private boolean seating = false;
    private HeroMode mode = HeroMode.MINI;
    private boolean shieldActivated = false;
    private final Stopwatch stopwatchForShield = new Stopwatch(1);
    private final Stopwatch stopwatchForTransitioning = new Stopwatch(1000);
    private final Stopwatch stopwatchForLightning = new Stopwatch(1000);
    private final Stopwatch stopwatchForFireball = new Stopwatch(1000);
    {
        stopwatchForFireball.start();
    }
    private final Stopwatch stopwatchForCooldown = new Stopwatch(1000);
    {
        stopwatchForCooldown.start();
    }
    private final Stopwatch stopwatchForDizzy = new Stopwatch(1000);
    private int fireFrameNumber = 0;
    private static final int fireFrameDelay = 10;
    private boolean transitioning = false;
    private boolean swordActivated = false;
    private int lightningFrameNumber = 0;
    private static final int lightningFrameDelay = 10;
    private boolean bossTrapped = false;
    private int[] bossBoundries = null;
    private boolean dizzy = false;
    private List<Shot> shots = new ArrayList<>();
    private transient Clip runningSound = AudioLoader.getRunSound();
    private transient boolean runningSoundStarted = false;

    // Constructor
    public Hero(int lives, int height, int width) {
        super(lives, new double[]{150,200}, height, width, new double[]{0,0});
        updateDimensions();
    }

    // Methods
    public void addCoin(){this.coin++;}
    public void addCoin(int n){this.coin+=n;}
    public void emptyCoin(){this.coin = 0;}
    public void addScore(int n){this.score+=n;}
    public void updateDimensions(){
        if (mode==HeroMode.MINI){
            width = 50;
            height = 78;
        }
        else{
            if (isSeating()){
                width = 80;
                height = 92;
            }
            else{
                width = 80;
                height = 125;
            }
        }
    }
    public void addFireFrame(){
        fireFrameNumber++;
        fireFrameNumber%=4;
    }
    public void addLightningFrame(){
        lightningFrameNumber++;
        lightningFrameNumber%=4;
    }
    public void reloadRunSound(){
        runningSound = AudioLoader.getRunSound();
    }

    // Setters
    public void setStandingOnSomething(boolean standingOnSomething) {this.standingOnSomething = standingOnSomething;}
    public void setJumping(boolean jumping) {this.jumping = jumping;}
    public void setSeating(boolean seating) {
        if (mode!=HeroMode.MINI){
            if (seating && !this.seating){
                setCoordinates(new double[]{getX(),getY()+33});
            }
            if (!seating && this.seating){
                setCoordinates(new double[]{getX(),getY()-33});
            }
            this.seating = seating;
        }
        updateDimensions();
    }
    public void setMode(HeroMode mode) {
        this.mode = mode;
        updateDimensions();
        if (this.mode != HeroMode.MINI){
            setY(getY()-50);
            setVy(20);
        }
    }
    public void setShieldActivated(boolean shieldActivated) {
        if (shieldActivated){
            stopwatchForShield.start();
        }
        this.shieldActivated = shieldActivated;
    }
    public void setTransitioning(boolean transitioning) {
        this.transitioning = transitioning;
        stopwatchForTransitioning.start();
    }
    public void setSwordActivated(boolean swordActivated) {
        stopwatchForLightning.start();
        this.swordActivated = swordActivated;
    }
    public void setBossTrapped(boolean bossTrapped) {
        this.bossTrapped = bossTrapped;
    }
    public void setBossBoundries(int[] bossBoundries) {
        this.bossBoundries = bossBoundries;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setDizzy(boolean dizzy) {
        this.dizzy = dizzy;
    }

    public void setRunningSoundStarted(boolean runningSoundStarted) {
        this.runningSoundStarted = runningSoundStarted;
    }

    // Getters
    public int getScore() {return score;}
    public int getCoin() {return coin;}
    public HeroMode getMode() {return mode;}
    public boolean isStandingOnSomething() {return standingOnSomething;}
    public boolean isJumping() {return jumping;}
    public boolean isSeating() {return seating;}
    public boolean isShieldActivated() {return shieldActivated;}
    public Stopwatch getStopwatchForShield() {return stopwatchForShield;}
    public int getFireFrameNumber() {return fireFrameNumber;}
    public static int getFireFrameDelay() {return fireFrameDelay;}
    public boolean isTransitioning() {return transitioning;}
    public Stopwatch getStopwatchForTransitioning() {return stopwatchForTransitioning;}
    public boolean isSwordActivated() {return swordActivated;}
    public int getLightningFrameNumber() {return lightningFrameNumber;}
    public static int getLightningFrameDelay() {return lightningFrameDelay;}
    public Stopwatch getStopwatchForLightning() {return stopwatchForLightning;}
    public Stopwatch getStopwatchForCooldown() {return stopwatchForCooldown;}
    public boolean isBossTrapped() {
        return bossTrapped;
    }
    public int[] getBossBoundries() {
        return bossBoundries;
    }
    public boolean isVisible() {
        return visible;
    }
    public boolean isDizzy() {
        return dizzy;
    }
    public Stopwatch getStopwatchForDizzy() {
        return stopwatchForDizzy;
    }
    public Stopwatch getStopwatchForFireball() {
        return stopwatchForFireball;
    }
    public List<Shot> getShots() {return shots;}
    public Clip getRunningSound() {
        if (runningSound==null){
            runningSound=AudioLoader.getRunSound();
        }
        return runningSound;
    }
    public boolean isRunningSoundStarted() {
        return runningSoundStarted;
    }
}
