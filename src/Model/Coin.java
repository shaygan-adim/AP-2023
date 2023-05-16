package Model;

public class Coin extends Item{
    // Fields
    private double[] coordinates;
    private final int width = 36;
    private final int height = 36;
    boolean visible = true;

    // Constructor
    public Coin(double[] coordinates){
        this.coordinates = coordinates;
    }

    // Setters
    public void setCoordinates(double[] coordinates) {this.coordinates = coordinates;}
    public void setVisible(boolean visible) {this.visible = visible;}

    // Getters
    public double[] getCoordinates() {return coordinates;}
    public double getX(){return coordinates[0];}
    public double getY(){return coordinates[1];}
    public boolean isVisible() {return visible;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}
