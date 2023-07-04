package Model.Shots;

public class Sword extends Shot {
    // Fields
    private static final int acceleration = 10;

    // Constructor
    public Sword(int[] coordinates) {
        super(coordinates, 50, (int) ((double)1100*50/315), 80);
        getStopwatch().start();
    }

    // Getters
    public static int getAcceleration(){return acceleration;}

}
