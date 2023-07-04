package Model.Shots;

public class FireBall extends Shot {
    // Fields
    private static final int period = 437;

    // Constructor
    public FireBall(int[] coordinates,boolean right) {
        super(coordinates, 40, 40, 80);
        if (!right){
            setVelocity(-80);
        }
        getStopwatch().start();
    }

    // Getters
    public static int getPeriod(){return period;}
}
