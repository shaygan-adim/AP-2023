package Model;

abstract public class Item {
    private double[] coordinates;
    private final double[] velocity = {0,0};
    private final int width,height;
    private boolean visible = false;

    public Item(double[] coordinates,int width,int height){
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
    }

    // Methods
    public void addX(double deltaX){this.coordinates[0]+=deltaX;}
    public void addY(double deltaY){this.coordinates[1]+=deltaY;}
    public void addVx(double deltaX){this.velocity[0]+=deltaX;}
    public void addVy(double deltaY){this.velocity[1]+=deltaY;}

    // Setters
    public void setCoordinates(double[] coordinates) {this.coordinates = coordinates;}
    public void setVisible(boolean visible) {this.visible = visible;}
    public void setVx(double Vx) {this.velocity[0] = Vx;}
    public void setVy(double Vy) {this.velocity[1] = Vy;}


    // Getters
    public double[] getCoordinates() {return coordinates;}
    public double[] getVelocity() {return velocity;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public boolean isVisible() {return visible;}
    public double getX(){return coordinates[0];}
    public double getY(){return coordinates[1];}
    public double getVx(){return velocity[0];}
    public double getVy(){return velocity[1];}

}
