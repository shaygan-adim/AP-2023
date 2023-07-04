package Model.Characters.Heroes;

public class Mario extends Hero {
    // Fields
    private int frameNumber = 0;
    private static int frameDelay = 10;

    // Constructor
    public Mario(int lives) {
        super(lives,94,60);
    }
    // Methods
    public void addFrame(){
        frameNumber++;
        frameNumber%=4;
    }
    // Getters
    public int getFrameNumber() {return frameNumber;}
    public static int getFrameDelay() {return frameDelay;}
}
