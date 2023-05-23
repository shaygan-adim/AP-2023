package Model;

public class Star extends Item{
    // Fields
    private Stopwatch stopwatch = new Stopwatch(1);
    private boolean activated = false;
    private boolean standingOnSomething = true;

    // Constructor
    public Star(double[] coordinates){
        super(coordinates,44,44);
        super.setVisible(true);
    }

    // Setters
    public void setActivated(boolean activated) {this.activated = activated;}
    public void setStandingOnSomething(boolean standingOnSomething) {this.standingOnSomething = standingOnSomething;}

    // Getters
    public Stopwatch getStopwatch() {return stopwatch;}
    public boolean isActivated() {return activated;}
    public boolean isStandingOnSomething() {return standingOnSomething;}
}