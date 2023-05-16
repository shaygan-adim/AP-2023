package Model;

abstract public class Hero extends Character{
    // Fields
    private int score = 0;
    private int coin = 0;
    private boolean standingOnSomething = false;
    private boolean jumping = false;

    // Constructor
    public Hero(int lives, int height, int width) {
        super(lives, new double[]{150,200}, height, width, new double[]{0,0});
    }

    // Methods
    public void addCoin(){this.coin++;}
    public void addCoin(int n){this.coin+=n;}
    public void removeCoin(){this.coin--;}
    public void removeCoin(int n){this.coin-=n;}
    public void emptyCoin(){this.coin = 0;}
    public void addScore(int n){this.score+=n;}

    // Setters
    public void setScore(int score) {this.score = score;}
    public void setStandingOnSomething(boolean standingOnSomething) {this.standingOnSomething = standingOnSomething;}
    public void setJumping(boolean jumping) {this.jumping = jumping;}

    // Getters
    public int getScore() {return score;}
    public int getCoin() {return coin;}
    public boolean isStandingOnSomething() {return standingOnSomething;}
    public boolean isJumping() {return jumping;}
}
