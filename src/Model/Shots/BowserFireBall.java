package Model.Shots;

public class BowserFireBall extends Shot {

    // Constructor
    public BowserFireBall(int[] coordinates,boolean right) {
        super(coordinates, 100, 100, 40);
        if (!right){
            setVelocity(-40);
        }
    }
}
