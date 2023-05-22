package Model;

abstract public class Character {
    // Fields
    private int lives;
    private double[] coordinates;
    protected int height,width;
    private final double[] velocity;

    // Constructor
    public Character(int lives, double[] coordinates, int height, int width,double[] velocity){
        this.lives = lives;
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
    }

    // Methods
    public void addX(double deltaX){this.coordinates[0]+=deltaX;}
    public void addY(double deltaY){this.coordinates[1]+=deltaY;}

    // Getters
    public int getLives() {return lives;}
    public double[] getCoordinates() {return coordinates;}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public double getX(){return this.coordinates[0];}
    public double getY(){return this.coordinates[1];}
    public double getVx(){return this.velocity[0];}
    public double getVy(){return this.velocity[1];}

    // Setters
    public void setLives(int lives) {this.lives = lives;}
    public void setCoordinates(double[] coordinates) {this.coordinates = coordinates;}
    public void setX(double x){this.coordinates[0] = x;}
    public void setY(double y){this.coordinates[1] = y;}
    public void setVx(double vx){this.velocity[0] = vx;}
    public void setVy(double vy){this.velocity[1] = vy;}
}
