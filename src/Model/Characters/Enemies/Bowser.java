package Model.Characters.Enemies;

public class Bowser extends Enemy{
    // Fields
    private int runningFrameNumber = 0;
    private static int frameDelay = 20;
    private final double restingX,restingY;
    private boolean triggered = false;
    private boolean toLeft = true;

    // Constructor
    public Bowser(int lives, double[] coordinates) {
        super(lives, coordinates, 222, 244, new double[]{0,0});
        restingX = coordinates[0];
        restingY = coordinates[1];
    }

    // Methods
    public void addRunningFrame(){
        runningFrameNumber++;
        runningFrameNumber%=4;
    }

    // Setters
    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
    public void setToLeft(boolean toLeft) {
        this.toLeft = toLeft;
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
}
