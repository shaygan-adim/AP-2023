package Model;

abstract public class Enemy extends Character{
    // Fields
    private boolean visible = true;
    private boolean standingOnSomething = false;

    // Constructor
    public Enemy(int lives, double[] coordinates, int height, int width, double[] velocity) {
        super(lives, coordinates, height, width, velocity);
    }
    // Setters
    public void setVisible(boolean visible) {this.visible = visible;}
    public void setStandingOnSomething(boolean standingOnSomething) {this.standingOnSomething = standingOnSomething;}

    // Getters
    public boolean isVisible() {return visible;}
    public boolean isStandingOnSomething() {return standingOnSomething;}
}
