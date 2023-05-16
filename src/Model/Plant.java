package Model;

public class Plant extends Enemy{
    // Fields
    transient private Pipe pipe;
    private final Stopwatch stopwatch = new Stopwatch(1);
    private boolean visible;
    private final int timePeriod;

    // Constructor
    public Plant(int lives,Pipe pipe,int timePeriod) {
        super(lives, null, 123, 172, null);
        this.pipe = pipe;
        this.timePeriod = timePeriod;
    }

    // Setters
    public void setVisible(boolean visible) {this.visible = visible;}
    public void setPipe(Pipe pipe) {this.pipe = pipe;}

    // Getters
    public Stopwatch getStopwatch() {return stopwatch;}
    public boolean isVisible() {return visible;}
    public Pipe getPipe() {return pipe;}
    public int getTimePeriod() {return timePeriod;}
}
