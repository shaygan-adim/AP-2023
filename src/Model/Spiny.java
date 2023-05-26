package Model;

public class Spiny extends Enemy{
    // Fields
    private int frameNumber = 0;
    private static final int frameDelay = 20;
    private static final double acceleration = 0.5;
    private boolean runActivated = false;

    // Constructor
    public Spiny(int lives, double[] coordinates) {
        super(lives, coordinates, 55,55, new double[]{2.5,0});
    }

    // Methods
    public void addFrame(){
        frameNumber++;
        frameNumber%=4;
    }

    // Setters
    public void setRunActivated(boolean runActivated) {
        this.runActivated = runActivated;
    }

    // Getters
    public int getFrameNumber() {return frameNumber;}
    public static int getFrameDelay() {return frameDelay;}
    public static double getAcceleration(){return acceleration;}
    public boolean isRunActivated() {return runActivated;}
}