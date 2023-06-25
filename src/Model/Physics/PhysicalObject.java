package Model.Physics;

abstract public class PhysicalObject {
    // Fields
    private int[] coordinates;
    private final int height,width;

    // Constructor
    public PhysicalObject(int[] coordinates,int height,int width){
        this.coordinates = coordinates;
        this.height = height;
        this.width = width;
    }

    // Methods
    public void addToX(int deltaX){this.coordinates[0]+=deltaX;}
    public void addToY(int deltaY){this.coordinates[1]+=deltaY;}

    // Setters
    public void setCoordinates(int[] coordinates) {this.coordinates = coordinates;}
    public void setX(int x){this.coordinates[0] = x;}
    public void setY(int y){this.coordinates[1] = y;}

    // Getters
    public int[] getCoordinates() {return coordinates;}
    public int getX(){return coordinates[0];}
    public int getY(){return coordinates[1];}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
}
