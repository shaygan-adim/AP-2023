package Model;

public class Block extends PhysicalObject{
    // Fields
    private Item itemInside;

    // Constructor
    public Block(int[] coordinates, Item itemInside) {
        super(coordinates,80, 80);
        this.itemInside = itemInside;
    }
    // Setters
    public void setItemInside(Item itemInside) {this.itemInside = itemInside;}

    // Getters
    public Item getItemInside() {return itemInside;}
}
