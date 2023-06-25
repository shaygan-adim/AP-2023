package Model.Levels;

import Model.Characters.Enemies.Enemy;
import Model.Characters.Heroes.Hero;
import Model.Items.Coin;
import Model.Items.Item;
import Model.Physics.Block;
import Model.Physics.Floor;
import Model.Physics.Pipe;
import Logic.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part {
    // Fields
    private final int id;
    private final Block[] blocks;
    private final Floor[] floors;
    private final Pipe[] pipes;
    private final Enemy[] enemies;
    private final Hero[] heroes;
    private Coin[] coins;
    private List<Item> items =  new ArrayList<>();
    private final int[] endY;
    private final int time;
    private final Stopwatch stopwatch = new Stopwatch(1);
    private int finalScore = 0;

    // Constructor
    public Part(int id,Block[] blocks, Floor[] floors, Pipe[] pipes, Enemy[] enemies, Hero[] heroes, int coinsNumber,int[] endY,int time) {
        this.id = id;
        this.blocks = blocks;
        this.floors = floors;
        this.pipes = pipes;
        this.enemies = enemies;
        this.heroes = heroes;
        this.endY = endY;
        this.time = time;

        // Putting coins in the world randomly
        this.coins = new Coin[coinsNumber];
        for (int i = 0 ; i<coinsNumber ; i++){
            this.coins[i] = new Coin(null);
            Random random = new Random();
            int x = -1;
            int y = -1;
            boolean goodRandom = false;
            while (!goodRandom){
                goodRandom = true;
                x = random.nextInt(200,5000);
                y = random.nextInt(30,600);
                for (Block block : this.blocks){
                    if (x+this.coins[i].getWidth()>block.getCoordinates()[0] && x<block.getCoordinates()[0]+block.getWidth() && y+this.coins[i].getHeight()>block.getCoordinates()[1] && y<block.getCoordinates()[1]+block.getHeight()){
                        goodRandom = false;
                        break;
                    }
                }
                for (Pipe pipe : this.pipes){
                    if (x+this.coins[i].getWidth()>pipe.getCoordinates()[0] && x<pipe.getCoordinates()[0]+pipe.getWidth() && y+this.coins[i].getHeight()>pipe.getCoordinates()[1] && y<pipe.getCoordinates()[1]+pipe.getHeight()){
                        goodRandom = false;
                        break;
                    }
                }
                boolean goodCertified = false;
                for (Floor floor : this.floors){
                    if (x+this.coins[i].getWidth()>floor.getCoordinates()[0] && x<floor.getCoordinates()[0]+floor.getWidth() && 700+this.coins[i].getHeight()>floor.getCoordinates()[1] && 700<floor.getCoordinates()[1]+floor.getHeight()){
                        goodCertified = true;
                        break;
                    }
                }
                if (!goodCertified){
                    goodRandom = false;
                }
                if (goodRandom){
                    boolean flag = true;
                    while (flag){
                        for (Block block : this.blocks){
                            if (y+this.coins[i].getHeight()==block.getCoordinates()[1] && x+this.coins[i].getWidth()>block.getCoordinates()[0] && x<block.getCoordinates()[0]+block.getWidth()){
                                flag = false;
                                break;
                            }
                        }
                        for (Pipe pipe : this.pipes){
                            if (y+this.coins[i].getHeight()==pipe.getCoordinates()[1] && x+this.coins[i].getWidth()>pipe.getCoordinates()[0] && x<pipe.getCoordinates()[0]+pipe.getWidth()){
                                flag = false;
                                break;
                            }
                        }
                        for (Floor floor : this.floors){
                            if (y+this.coins[i].getHeight()==floor.getCoordinates()[1] && x+this.coins[i].getWidth()>floor.getCoordinates()[0] && x<floor.getCoordinates()[0]+floor.getWidth()){
                                flag = false;
                                break;
                            }
                        }
                        y++;
                    }
                }
            }
            double[] coordinates = {x,y};
            this.coins[i].setCoordinates(coordinates);
            this.items.add(this.coins[i]);
        }
    }
    // Methods
    public void addBlockCoin(Coin coin){
        this.items.add(coin);
        Coin[] coins2 = new Coin[this.coins.length+1];
        coins2[coins2.length-1] = coin;
        for (int i = 0 ; i<this.coins.length ; i++) coins2[i] = this.coins[i];
        this.coins = coins2;
    }

    // Setters
    public void setFinalScore(int finalScore) {this.finalScore = finalScore;}

    // Getters
    public int getId() {return id;}
    public Block[] getBlocks() {return blocks;}
    public Floor[] getFloors() {return floors;}
    public Pipe[] getPipes() {return pipes;}
    public Enemy[] getEnemies() {return enemies;}
    public Hero[] getHeroes() {return heroes;}
    public List<Item> getItems() {return items;}
    public int[] getEndY() {return endY;}
    public int getFinalScore() {return finalScore;}
    public Stopwatch getStopwatch() {return stopwatch;}
    public int getTime() {return time;}
}
