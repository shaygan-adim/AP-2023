package Model;

abstract public class Enemy extends Character{
    // Constructor
    public Enemy(int lives, double[] coordinates, int height, int width, double[] velocity) {
        super(lives, coordinates, height, width, velocity);
    }

}
