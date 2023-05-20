package Model;

public class Pipe extends PhysicalObject{
    // Fields
    private Plant plantInside;

    // Constructor
    public Pipe(int[] coordinates, Plant plant) {
        super(coordinates, 112, 112);
        this.plantInside = plant;
    }

    // Setters
    public void setPlant(Plant plant) {this.plantInside = plant;}

    // Getters
    public Plant getPlant() {return plantInside;}
}
