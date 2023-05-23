package Model;

public class Mushroom extends Item{
    // Fields
    private Stopwatch stopwatch = new Stopwatch(1);
    private boolean activated = false;

    // Constructor
    public Mushroom(double[] coordinates){
        super(coordinates,44,44);
        super.setVisible(true);
    }

    // Setters
    public void setActivated(boolean activated) {this.activated = activated;}

    // Getters
    public Stopwatch getStopwatch() {return stopwatch;}
    public boolean isActivated() {return activated;}
}