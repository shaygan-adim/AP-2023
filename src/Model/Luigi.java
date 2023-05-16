package Model;

public class Luigi extends Hero{
    // Fields
    private int frameNumber = 0;
    private static int frameDelay = 10;
    private static int price = 250;
    public Luigi(int lives) {
        super(lives,125,80);
    }
    // Methods
    public void addFrame(){
        frameNumber++;
        frameNumber%=4;
    }
    public void resetFrame(){
        frameNumber=0;
    }
    // Getters
    public int getFrameNumber() {return frameNumber;}
    public static int getFrameDelay() {return frameDelay;}
    public static int getPrice() {return price;}
}