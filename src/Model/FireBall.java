package Model;

public class FireBall extends Shot{
    // Fields
    private static final int period = 700;
    private static final int coolDown = 700;

    // Constructor
    public FireBall(int[] coordinates,boolean right) {
        super(coordinates, 40, 40, 50);
        if (!right){
            setVelocity(-50);
        }
        getStopwatch().start();
    }

    // Getters
    public static int getPeriod(){return period;}
}
