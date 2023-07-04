package Model.Physics;

import Model.Items.Item;

import java.io.Serializable;

public class Block extends PhysicalObject {
    // Fields
    private Item[] itemsInside;
    private BlockType blockType;
    private boolean visible = true;

    // Constructor
    public Block(int[] coordinates, BlockType blockType,Item[] itemsInside) {
        super(coordinates,60, 60);
        this.blockType = blockType;
        this.itemsInside = itemsInside;

    }
    // Setters
    public void setItemsInside(Item[] itemsInside) {this.itemsInside = itemsInside;}
    public boolean isVisible() {return visible;}
    public void setBlockType(BlockType blockType) {this.blockType = blockType;}

    // Getters
    public Item[] getItemsInside() {return itemsInside;}
    public BlockType getBlockType() {return blockType;}
    public void setVisible(boolean visible) {this.visible = visible;}
}
