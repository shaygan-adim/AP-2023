package Model;

import java.io.IOException;

public class PhysicsHandler {
    // Fields
    private final Level level;
    private final User user;
    private static int g = -5;
    private static final double dt = 0.1D;
    private boolean changed;

    // Constructor
    public PhysicsHandler(Level level, User user){
        this.level = level;
        this.user = user;
    }

    // Methods
    public void updatePhysics(){
        changed = false;
        for (Hero hero : level.getActivePart().getHeroes()){
            if (this.level.getActivePart().getStopwatch().passedTime()>=this.level.getActivePart().getTime()){
                this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
                this.level.getActivePart().getStopwatch().start();
                die(hero);
            }
            int n = 0;

            if (!hero.isJumping()){
                hero.setVy(hero.getVy()+g*dt);
            }
            // Handling the physics of blocks
            for (Block block : level.getActivePart().getBlocks()){
                    if (block.isVisible()&& hero.getY()-hero.getVy()*dt>=block.getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<block.getY()+block.getHeight() && hero.getX()>block.getX()-hero.getWidth() && hero.getX()<block.getX()+ block.getWidth()) {
                        if (block.getBlockType() == BlockType.SLIME && Math.abs(hero.getVy())>5) {
                            n++;
                            if (Math.abs(hero.getVy())<45){
                                hero.setVy(-hero.getVy()*1.2);
                            }
                            else{
                                hero.setVy(-hero.getVy());
                            }
                        }
                        else{
                            hero.setStandingOnSomething(true);
                        }
                    }
                    else{
                        n++;
                    }
                    if (block.isVisible() && hero.getY()- hero.getVy()*dt<block.getY()+block.getHeight() && hero.getY()>=block.getY()-hero.getHeight() && hero.getX()>block.getX()-hero.getWidth() && hero.getX()<block.getX()+ block.getWidth()){
                        hero.setVy(0);
                        hero.setY(hero.getY()+5);
                        System.out.println(block.getBlockType());
                        if (block.getBlockType()==BlockType.SIMPLE){
                            hero.addScore(100);
                            block.setVisible(false);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.COIN){
                            block.setBlockType(BlockType.SIMPLE);
                            ((Coin) block.getItemsInside()[0]).setVisible(true);
                            level.getActivePart().addBlockCoin((Coin) block.getItemsInside()[0]);
                            hero.addScore(100);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.COINS){
                            if (block.getItemsInside().length==1){
                                block.setBlockType(BlockType.EMPTY);
                            }
                            block.setItemsInside(new Item[block.getItemsInside().length-1]);
                            hero.addCoin();
                            hero.addScore(100);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.QUESTION){
                            hero.addScore(100);
                            block.setBlockType(BlockType.EMPTY);
                            changed=true;
                        }

                    }
                    if (hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>block.getX() && hero.getX()<block.getX()){
                        hero.setVx(0);
                    }
                    if (hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+hero.getVx()*dt<block.getX()+ block.getWidth() && hero.getX()+ hero.getWidth()>block.getX()+block.getWidth()){
                        hero.setVx(0);
                    }
            }

            // Handling the physics of pipes
            for (Pipe pipe : level.getActivePart().getPipes()){
                if (hero.getY()-hero.getVy()*dt>=pipe.getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<pipe.getY()+pipe.getHeight() && hero.getX()>pipe.getX()-hero.getWidth() && hero.getX()<pipe.getX()+ pipe.getWidth()) {
                    hero.setStandingOnSomething(true);
                }
                else{
                    n++;
                }
                if (hero.getY()- hero.getVy()*dt<pipe.getY()+pipe.getHeight() && hero.getY()>=pipe.getY()-hero.getHeight() && hero.getX()>pipe.getX()-hero.getWidth() && hero.getX()<pipe.getX()+ pipe.getWidth()){
                    hero.setVy(0);
                }
                if (hero.getY()+hero.getHeight()>pipe.getY() && hero.getY()<pipe.getY()+pipe.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>pipe.getX() && hero.getX()<pipe.getX()){
                    hero.setVx(0);
                }
                if (hero.getY()+hero.getHeight()>pipe.getY() && hero.getY()<pipe.getY()+pipe.getHeight() && hero.getX()+hero.getVx()*dt<pipe.getX()+ pipe.getWidth() && hero.getX()+ hero.getWidth()>pipe.getX()+pipe.getWidth()){
                    hero.setVx(0);
                }
            }

            // Handling the physics of floors
            int N = 0;
            for (Floor floor : level.getActivePart().getFloors()){
                if (hero.getY()-hero.getVy()*dt>=floor.getY()-hero.getHeight() && hero.getX()>floor.getX()-hero.getWidth() && hero.getX()<floor.getX()+ floor.getWidth()) {
                    hero.setStandingOnSomething(true);
                }
                else{
                    n++;
                    N++;
                }
                if (!hero.isStandingOnSomething() && hero.getY()+hero.getHeight()>floor.getY() && hero.getY()<floor.getY()+floor.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>floor.getX() && hero.getX()<floor.getX()){
                    hero.setVx(0);
                }
                if (!hero.isStandingOnSomething() && hero.getY()+hero.getHeight()>floor.getY() && hero.getY()<floor.getY()+floor.getHeight() && hero.getX()+hero.getVx()*dt<floor.getX()+ floor.getWidth() && hero.getX()+ hero.getWidth()>floor.getX()+floor.getWidth()){
                    hero.setVx(0);
                }
            }
            if (N==this.level.getActivePart().getFloors().length && hero.getY()+hero.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                die(hero);
            }
            if (n== level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length){
                hero.setStandingOnSomething(false);
                hero.setJumping(false);
                g = -5;
            }
            else{
                if (!hero.isJumping()){
                    hero.setVy(0);
                }
            }
            hero.addY(-hero.getVy()*dt);
            if (hero.getX()+ hero.getVx()*dt>150) hero.addX(hero.getVx()*dt);
            else hero.setVx(0);
        }
        checkEnemies();
        updateCoins();
        updatePlants();
        updateActivePart();
        if (changed){
            if (level.getActivePart().getId()==0){
                if (this.user.getActiveSlot()==1){
                    this.user.setPart1(PartName.L1P1);
                    this.user.setPartScore1(0);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartHeart1(this.level.getActivePart().getHeroes()[0].getLives());
                }
                if (this.user.getActiveSlot()==2){
                    this.user.setPart2(PartName.L1P1);
                    this.user.setPartScore2(0);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartHeart2(this.level.getActivePart().getHeroes()[0].getLives());
                }
                if (this.user.getActiveSlot()==3){
                    this.user.setPart3(PartName.L1P1);
                    this.user.setPartScore3(0);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartHeart3(this.level.getActivePart().getHeroes()[0].getLives());
                }
                try {
                    user.save();
                } catch (IOException e) {}
            }
            else{
                if (this.user.getActiveSlot()==1){
                    this.user.setPart1(PartName.L1P2);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartScore1(this.level.getActivePart().getHeroes()[0].getScore());
                    this.user.setPartHeart1(this.level.getActivePart().getHeroes()[0].getLives());
                }
                if (this.user.getActiveSlot()==2){
                    this.user.setPart2(PartName.L1P2);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartScore2(this.level.getActivePart().getHeroes()[0].getScore());
                    this.user.setPartHeart2(this.level.getActivePart().getHeroes()[0].getLives());
                }
                if (this.user.getActiveSlot()==3){
                    this.user.setPart3(PartName.L1P2);
                    this.user.setPartCoin1(this.level.getActivePart().getHeroes()[0].getCoin());
                    this.user.setPartScore3(this.level.getActivePart().getHeroes()[0].getScore());
                    this.user.setPartHeart3(this.level.getActivePart().getHeroes()[0].getLives());
                }
                try {
                    user.save();
                } catch (IOException e) {}
            }
        }
    }
    public void jump(){
        if (level.getActivePart().getHeroes()[0].getVy()==0 && level.getActivePart().getHeroes()[0].isStandingOnSomething()){
            level.getActivePart().getHeroes()[0].setY(level.getActivePart().getHeroes()[0].getY()-5);
            level.getActivePart().getHeroes()[0].setVy(45);
            level.getActivePart().getHeroes()[0].setJumping(true);
        }
    }
    public void right(){
        level.getActivePart().getHeroes()[0].setVx(40);
    }
    public void stop(){
        level.getActivePart().getHeroes()[0].setVx(0);
    }
    public void left(){
        level.getActivePart().getHeroes()[0].setVx(-40);
    }

    public void updateCoins(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            for (int i = 0; i< level.getActivePart().getCoins().length ; i++){
                if (level.getActivePart().getCoins()[i].isVisible() && level.getActivePart().getCoins()[i].getX()+ level.getActivePart().getCoins()[i].getWidth()> hero.getX() && level.getActivePart().getCoins()[i].getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getCoins()[i].getY()+ level.getActivePart().getCoins()[i].getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getCoins()[i].getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                    level.getActivePart().getCoins()[i].setVisible(false);
                    hero.addCoin();
                    this.changed = true;
                }
            }
        }
    }
    public void checkEnemies(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            for (int i = 0; i< level.getActivePart().getEnemies().length ; i++){
                if (((Plant)level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX() && level.getActivePart().getEnemies()[i].getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getEnemies()[i].getY()+ level.getActivePart().getEnemies()[i].getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getEnemies()[i].getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                    die(hero);
                }
            }
        }
    }

    public void updatePlants(){
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Plant){
                if (((Plant) enemy).getStopwatch().passedTime()> ((Plant) enemy).getTimePeriod()){
                    ((Plant) enemy).getStopwatch().start();
                    if (((Plant) enemy).isVisible()){
                        ((Plant) enemy).setVisible(false);
                    }
                    else{
                        ((Plant) enemy).setVisible(true);
                    }
                }
            }
        }
    }
    public void updateActivePart(){
       if (this.level.getActivePart().getHeroes()[0].getX()>5019 && this.level.getActivePart().getHeroes()[0].getY()>this.level.getActivePart().getEndY()[0] && this.level.getActivePart().getHeroes()[0].getY()+this.level.getActivePart().getHeroes()[0].getHeight()/2<this.level.getActivePart().getEndY()[1]){
           if (this.level.getActivePart().getId()==this.level.getParts().length-1){
               this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
               this.level.getActivePart().getHeroes()[0].addScore((this.level.getActivePart().getTime()-(int)this.level.getActivePart().getStopwatch().passedTime())*50);
               this.level.getActivePart().getHeroes()[0].addScore(this.level.getActivePart().getHeroes()[0].getLives()*150);
               this.user.setCoin(this.user.getCoin()+this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.addCoin(this.level.getActivePart().getHeroes()[0].getCoin());
               if (this.user.getHighscore()<this.level.getActivePart().getHeroes()[0].getScore()){
                   this.user.setHighscore(this.level.getActivePart().getHeroes()[0].getScore());
               }
               if (user.getActiveSlot()==1){
                   user.setPart1(null);
               }
               if (user.getActiveSlot()==2){
                   user.setPart2(null);
               }
               if (user.getActiveSlot()==3){
                   user.setPart3(null);
               }
               try {
                   this.user.save();
               } catch (IOException e) {}
               this.level.setDone(1);
           }
           else{
               this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
               this.level.getActivePart().getHeroes()[0].addScore((this.level.getActivePart().getTime()-(int)this.level.getActivePart().getStopwatch().passedTime())*50);
               this.level.getActivePart().getHeroes()[0].addScore(this.level.getActivePart().getHeroes()[0].getLives()*150);
               this.user.setCoin(this.user.getCoin()+this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.addCoin(this.level.getActivePart().getHeroes()[0].getCoin());
               this.level.getActivePart().getHeroes()[0].emptyCoin();
               this.level.getActivePart().setFinalScore(this.level.getActivePart().getHeroes()[0].getScore());
               try {
                   this.user.save();
               } catch (IOException e) {}
               this.level.setActivePart(this.level.getParts()[this.level.getActivePart().getId()+1]);
               this.level.getActivePart().getHeroes()[0].setCoordinates(new double[]{150,200});
               this.level.getActivePart().getStopwatch().start();
           }
       }
    }
    public void die(Hero hero){
        if (hero.getLives()>=2){
            hero.setLives(hero.getLives()-1);
            hero.setCoordinates(new double[]{150,200});
//            for (Coin coin : this.level.getActivePart().getCoins()){
//                coin.setVisible(true);
//            }
//            for (Block block : this.level.getActivePart().getBlocks()){
//                if (block.getItemInside() instanceof Coin){
//                    ((Coin) block.getItemInside()).setVisible(true);
//                }
//            }
            if (this.level.getActivePart().getId()==0){
                this.level.getActivePart().getHeroes()[0].setScore(0);
            }
            else{
                this.level.getActivePart().getHeroes()[0].setScore(this.level.getParts()[this.level.getActivePart().getId()-1].getFinalScore());
            }
        }
        else{
            this.level.addTime((int)this.level.getActivePart().getStopwatch().passedTime());
            if (this.user.getActiveSlot()==1){
                this.user.setPart1(null);
            }
            if (this.user.getActiveSlot()==2){
                this.user.setPart2(null);
            }
            if (this.user.getActiveSlot()==3){
                this.user.setPart3(null);
            }
            try {
                this.user.save();
            } catch (IOException e) {}
            this.level.setDone(2);
        }
        this.changed = true;
    }
}
