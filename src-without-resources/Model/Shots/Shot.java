package Model.Shots;

import Model.Physics.PhysicalObject;
import Logic.Stopwatch;

import java.io.Serializable;

abstract public class Shot extends PhysicalObject implements Serializable {
    // Fields
    private double velocity;
    private final Stopwatch stopwatch = new Stopwatch(1000);
    private boolean visible = true;

    // Constructor
    public Shot(int[] coordinates, int height, int width, double velocity) {
        super(coordinates, height, width);
        this.velocity = velocity;
    }

    // Getters
    public double getVelocity() {return velocity;}
    public Stopwatch getStopwatch() {return stopwatch;}
    public boolean isVisible() {return visible;}

    // Setters
    public void setVelocity(double velocity) {this.velocity = velocity;}
    public void setVisible(boolean visible) {this.visible = visible;}
}
