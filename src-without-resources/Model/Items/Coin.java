package Model.Items;

import Model.Items.Item;

public class Coin extends Item {

    // Constructor
    public Coin(double[] coordinates){
        super(coordinates,36,36);
        super.setVisible(true);
    }

}
