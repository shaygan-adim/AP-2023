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
        // Physics of shots
        for (Hero hero : level.getActivePart().getHeroes()){
            for (Shot shot : hero.getShots()){
                if (shot instanceof FireBall && shot.getStopwatch().passedTime()>FireBall.getPeriod()){
                    shot.setVisible(false);
                }
                if (shot.isVisible()){
                    shot.addToX((int) (shot.getVelocity()*dt));
                }
            }
        }

        // Physics of Items
        for (Item item : this.level.getActivePart().getItems()){
            if (item.isVisible()){
                if (item instanceof Mushroom){
                    if (((Mushroom) item).getStopwatch().passedTime()>3 && !((Mushroom) item).isActivated()){
                        item.setVx(10);
                        item.setVy(10);
                        ((Mushroom) item).setActivated(true);
                    }
                }
                if (item instanceof Star){
                    if (((Star) item).getStopwatch().passedTime()>3 && !((Star) item).isActivated()){
                        ((Star) item).getStopwatch().start();
                        item.setVx(10);
                        item.setVy(10);
                        ((Star) item).setActivated(true);
                    }
                    if (((Star) item).getStopwatch().passedTime()>1 && ((Star) item).isActivated() && ((Star) item).isStandingOnSomething()){
                        ((Star) item).getStopwatch().start();
                        item.setVy(20);
                    }
                }
                item.addVy(g*dt);
                int n = 0;

                // Handling the physics of blocks
                for (Block block : level.getActivePart().getBlocks()) {
                    if (!(block.isVisible() && item.getY() - item.getVy() * dt >= block.getY() - item.getHeight() && item.getY() + item.getHeight() < block.getY() + block.getHeight() && item.getX() > block.getX() - item.getWidth() && item.getX() < block.getX() + block.getWidth())) {
                        n++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (block.isVisible() && item.getY()+item.getHeight()>block.getY() && item.getY()<block.getY()+block.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>block.getX() && item.getX()<block.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (block.isVisible() && item.getY()+item.getHeight()>block.getY() && item.getY()<block.getY()+block.getHeight() && item.getX()+item.getVx()*dt<block.getX()+ block.getWidth() && item.getX()+ item.getWidth()>block.getX()+block.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }

                // Handling the physics of pipes
                for (Pipe pipe : level.getActivePart().getPipes()) {
                    if (!(item.getY() - item.getVy() * dt >= pipe.getY() - item.getHeight() && item.getY() + item.getHeight() < pipe.getY() + pipe.getHeight() && item.getX() > pipe.getX() - item.getWidth() && item.getX() < pipe.getX() + pipe.getWidth())) {
                        n++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (item.getY()+item.getHeight()>pipe.getY() && item.getY()<pipe.getY()+pipe.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>pipe.getX() && item.getX()<pipe.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (item.getY()+item.getHeight()>pipe.getY() && item.getY()<pipe.getY()+pipe.getHeight() && item.getX()+item.getVx()*dt<pipe.getX()+ pipe.getWidth() && item.getX()+ item.getWidth()>pipe.getX()+pipe.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }

                // Handling the physics of floors
                int N = 0;
                for (Floor floor : level.getActivePart().getFloors()) {
                    if (!(item.getY() - item.getVy() * dt >= floor.getY() - item.getHeight() && item.getY() + item.getHeight() < floor.getY() + floor.getHeight() && item.getX() > floor.getX() - item.getWidth() && item.getX() < floor.getX() + floor.getWidth())) {
                        n++;
                        N++;
                    }
                    else{
                        if (item instanceof Star){
                            ((Star) item).setStandingOnSomething(true);
                        }
                    }
                    if (item instanceof Mushroom || item instanceof Star){
                        if (item.getY()+item.getHeight()>floor.getY() && item.getY()<floor.getY()+floor.getHeight() && item.getX()+ item.getWidth()+item.getVx()*dt>floor.getX() && item.getX()<floor.getX()){
                            item.setVx(-item.getVx());
                        }
                        if (item.getY()+item.getHeight()>floor.getY() && item.getY()<floor.getY()+floor.getHeight() && item.getX()+item.getVx()*dt<floor.getX()+ floor.getWidth() && item.getX()+ item.getWidth()>floor.getX()+floor.getWidth()){
                            item.setVx(-item.getVx());
                        }
                    }
                }
                if (N==this.level.getActivePart().getFloors().length && item.getY()+item.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                    item.setVisible(false);
                }
                if (!(n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length)){
                    item.setVy(0);
                }
                else{
                    if (item instanceof Star){
                        ((Star) item).setStandingOnSomething(false);
                    }
                }
                item.addY(-item.getVy()*dt);
                item.addX(item.getVx()*dt);
                if (item.getX()<0){
                    item.setVisible(false);
                }
            }
        }

        //Physics of Enemies
        for (Enemy enemy : this.level.getActivePart().getEnemies()){
            if (enemy.isVisible() && !(enemy instanceof Plant)){
                enemy.setStandingOnSomething(false);
                enemy.addVy(g*dt);
                if (enemy instanceof Spiny && !level.getActivePart().getHeroes()[0].isTransitioning()){
                    if (enemy.getY()+enemy.getHeight()>level.getActivePart().getHeroes()[0].getY() && enemy.getY()<level.getActivePart().getHeroes()[0].getY()+level.getActivePart().getHeroes()[0].getHeight()){
                        if (Math.pow(enemy.getX()+enemy.getWidth()/2-level.getActivePart().getHeroes()[0].getX()-level.getActivePart().getHeroes()[0].getWidth()/2,2)+Math.pow(enemy.getY()+enemy.getHeight()/2-level.getActivePart().getHeroes()[0].getY()-level.getActivePart().getHeroes()[0].getHeight()/2,2)<Math.pow(5*level.getActivePart().getBlocks()[0].getHeight(),2)){
                            if (!((Spiny) enemy).isRunActivated()){
                                if (enemy.getX()+enemy.getWidth()/2>level.getActivePart().getHeroes()[0].getX()+level.getActivePart().getHeroes()[0].getWidth()/2){
                                    enemy.setVx(-5);
                                }
                                else{
                                    enemy.setVx(5);
                                }
                            }
                            ((Spiny) enemy).setRunActivated(true);
                        }
                        else{
                            ((Spiny) enemy).setRunActivated(false);
                        }
                    }
                    else{
                        ((Spiny) enemy).setRunActivated(false);
                    }
                    if (((Spiny) enemy).isRunActivated()){
                        if (enemy.getVx()>0){
                            enemy.addVx(Spiny.getAcceleration()*dt);
                        }
                        else{
                            enemy.addVx(-Spiny.getAcceleration()*dt);
                        }
                    }
                    else{
                        if (enemy.getVx()>0){
                            enemy.setVx(2.5);
                        }
                        else{
                            enemy.setVx(-2.5);
                        }
                    }
                }
                if (enemy instanceof Spiny && level.getActivePart().getHeroes()[0].isTransitioning()){
                    ((Spiny) enemy).setRunActivated(false);
                    if (enemy.getVx()>0){
                        enemy.setVx(2.5);
                    }
                    else{
                        enemy.setVx(-2.5);
                    }
                }
                int n = 0;
                int nr = 0;
                int nl = 0;

                // Handling the physics of blocks
                for (Block block : level.getActivePart().getBlocks()) {
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX() > block.getX() - enemy.getWidth() && enemy.getX() < block.getX() + block.getWidth())) {
                        n++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                    }
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX()+enemy.getWidth() > block.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < block.getX() + block.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(block.isVisible() && enemy.getY() - enemy.getVy() * dt >= block.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < block.getY() + block.getHeight() && enemy.getX()-enemy.getWidth() > block.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < block.getX() + block.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (block.isVisible() && enemy.getY()+enemy.getHeight()>block.getY() && enemy.getY()<block.getY()+block.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>block.getX() && enemy.getX()<block.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (block.isVisible() && enemy.getY()+enemy.getHeight()>block.getY() && enemy.getY()<block.getY()+block.getHeight() && enemy.getX()+enemy.getVx()*dt<block.getX()+ block.getWidth() && enemy.getX()+ enemy.getWidth()>block.getX()+block.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }

                // Handling the physics of pipes
                for (Pipe pipe : level.getActivePart().getPipes()) {
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX() > pipe.getX() - enemy.getWidth() && enemy.getX() < pipe.getX() + pipe.getWidth())) {
                        n++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX()+enemy.getWidth() > pipe.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < pipe.getX() + pipe.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= pipe.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < pipe.getY() + pipe.getHeight() && enemy.getX()-enemy.getWidth() > pipe.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < pipe.getX() + pipe.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (enemy.getY()+enemy.getHeight()>pipe.getY() && enemy.getY()<pipe.getY()+pipe.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>pipe.getX() && enemy.getX()<pipe.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (enemy.getY()+enemy.getHeight()>pipe.getY() && enemy.getY()<pipe.getY()+pipe.getHeight() && enemy.getX()+enemy.getVx()*dt<pipe.getX()+ pipe.getWidth() && enemy.getX()+ enemy.getWidth()>pipe.getX()+pipe.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }

                // Handling the physics of floors
                int N = 0;
                for (Floor floor : level.getActivePart().getFloors()) {
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX() > floor.getX() - enemy.getWidth() && enemy.getX() < floor.getX() + floor.getWidth())) {
                        n++;
                        N++;
                    }
                    else{
                        enemy.setStandingOnSomething(true);
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX()+enemy.getWidth() > floor.getX() - enemy.getWidth() && enemy.getX()+enemy.getWidth() < floor.getX() + floor.getWidth())) {
                        if (enemy.getVx()>0){
                            nr++;
                        }
                    }
                    if (!(enemy.getY() - enemy.getVy() * dt >= floor.getY() - enemy.getHeight() && enemy.getY() + enemy.getHeight() < floor.getY() + floor.getHeight() && enemy.getX()-enemy.getWidth() > floor.getX() - enemy.getWidth() && enemy.getX()-enemy.getWidth() < floor.getX() + floor.getWidth())) {
                        if (enemy.getVx()<0){
                            nl++;
                        }
                    }
                    if (enemy.getY()+enemy.getHeight()>floor.getY() && enemy.getY()<floor.getY()+floor.getHeight() && enemy.getX()+ enemy.getWidth()+enemy.getVx()*dt>floor.getX() && enemy.getX()<floor.getX()){
                        enemy.setVx(-enemy.getVx());
                    }
                    if (enemy.getY()+enemy.getHeight()>floor.getY() && enemy.getY()<floor.getY()+floor.getHeight() && enemy.getX()+enemy.getVx()*dt<floor.getX()+ floor.getWidth() && enemy.getX()+ enemy.getWidth()>floor.getX()+floor.getWidth()){
                        enemy.setVx(-enemy.getVx());
                    }
                }
                if (N==this.level.getActivePart().getFloors().length && enemy.getY()+enemy.getHeight()/2>this.level.getActivePart().getFloors()[0].getY()){
                    enemy.setVisible(false);
                }
                if (!(n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length)){
                    enemy.setVy(0);
                }
                if (nr==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length || nl==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length){
                    if (enemy.isStandingOnSomething()){
                        enemy.setVx(-enemy.getVx());
                    }
                }
                enemy.addY(-enemy.getVy()*dt);
                enemy.addX(enemy.getVx()*dt);
                if (enemy.getX()<0){
                    enemy.setVisible(false);
                }
            }
        }

        // Physics of Heroes
        for (Hero hero : level.getActivePart().getHeroes()){
            if (hero.getStopwatchForTransitioning().passedTime()>2000){
                hero.setTransitioning(false);
            }
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
                    if (block.isVisible() && hero.getY()-hero.getVy()*dt>=block.getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<block.getY()+block.getHeight() && hero.getX()>block.getX()-hero.getWidth() && hero.getX()<block.getX()+ block.getWidth()) {
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
                        if (block.getBlockType()==BlockType.SIMPLE && hero.getMode()!=HeroMode.MINI){
                            hero.addScore(100);
                            block.setVisible(false);
                            changed=true;
                        }
                        if (block.getBlockType()==BlockType.COIN){
                            block.setBlockType(BlockType.SIMPLE);
                            block.getItemsInside()[0].setVisible(true);
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
                            block.getItemsInside()[0].setVisible(true);
                            if (block.getItemsInside()[0] instanceof Mushroom){
                                ((Mushroom) block.getItemsInside()[0]).getStopwatch().start();
                            }
                            if (block.getItemsInside()[0] instanceof Star){
                                ((Star) block.getItemsInside()[0]).getStopwatch().start();
                            }
                            this.level.getActivePart().getItems().add(block.getItemsInside()[0]);
                            hero.addScore(25);
                            block.setBlockType(BlockType.EMPTY);
                            changed=true;
                        }

                    }
                    if (block.isVisible() && hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+ hero.getWidth()+hero.getVx()*dt>block.getX() && hero.getX()<block.getX()){
                        hero.setVx(0);
                    }
                    if (block.isVisible() && hero.getY()+hero.getHeight()>block.getY() && hero.getY()<block.getY()+block.getHeight() && hero.getX()+hero.getVx()*dt<block.getX()+ block.getWidth() && hero.getX()+ hero.getWidth()>block.getX()+block.getWidth()){
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
                hero.setMode(HeroMode.MINI);
                die(hero);
            }
            if (n==level.getActivePart().getFloors().length+ level.getActivePart().getBlocks().length+ level.getActivePart().getPipes().length){
                hero.setStandingOnSomething(false);
                hero.setJumping(false);
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
        shotsCollisionCheck();
        itemsCollisionCheck();
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
    public void seat(){
        if (level.getActivePart().getHeroes()[0].isStandingOnSomething()){
            level.getActivePart().getHeroes()[0].setSeating(true);
        }
    }
    public void stand(){
        level.getActivePart().getHeroes()[0].setSeating(false);
    }
    public void shoot(boolean right){
        if (level.getActivePart().getHeroes()[0].isStandingOnSomething() && level.getActivePart().getHeroes()[0].getMode()==HeroMode.FIRE){
            boolean shoot = true;
            for (Shot shot : level.getActivePart().getHeroes()[0].getShots()){
                if (shot instanceof FireBall && shot.isVisible()) shoot=false;
            }
            if (shoot){
                if (right){
                    level.getActivePart().getHeroes()[0].getShots().add(new FireBall(new int[]{(int)level.getActivePart().getHeroes()[0].getX()+60,(int)level.getActivePart().getHeroes()[0].getY()+20},right));
                }
                else{
                    level.getActivePart().getHeroes()[0].getShots().add(new FireBall(new int[]{(int)level.getActivePart().getHeroes()[0].getX()-20,(int)level.getActivePart().getHeroes()[0].getY()+20},right));
                }
            }
        }
    }
    public void shotsCollisionCheck(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            for (Shot shot : hero.getShots()){
                // Enemies
                for (Enemy enemy : level.getActivePart().getEnemies()){
                    if (enemy.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> enemy.getX() && shot.getX()< enemy.getX()+ enemy.getWidth() && shot.getY()+ shot.getHeight()> enemy.getY() && shot.getY()< enemy.getY()+ enemy.getHeight()){
                        enemy.setVisible(false);
                        if (enemy instanceof Goomba){
                            hero.addScore(100);
                        }
                        if (enemy instanceof Koopa){
                            hero.addScore(200);
                        }
                        if (enemy instanceof Plant){
                            hero.addScore(100);
                        }
                        if (enemy instanceof Spiny){
                            hero.addScore(300);
                        }
                        shot.setVisible(false);
                        this.changed = true;
                    }
                }
                // Blocks
                for (Block block : level.getActivePart().getBlocks()){
                    if (block.isVisible() && shot.isVisible() && shot.getX()+ shot.getWidth()> block.getX() && shot.getX()< block.getX()+ block.getWidth() && shot.getY()+ shot.getHeight()> block.getY() && shot.getY()< block.getY()+ block.getHeight()){
                        shot.setVisible(false);
                    }
                }
                // Pipes
                for (Pipe pipe : level.getActivePart().getPipes()){
                    if (shot.isVisible() && shot.getX()+ shot.getWidth()> pipe.getX() && shot.getX()< pipe.getX()+ pipe.getWidth() && shot.getY()+ shot.getHeight()> pipe.getY() && shot.getY()< pipe.getY()+ pipe.getHeight()){
                        shot.setVisible(false);
                    }
                }
            }
        }
    }
    public void itemsCollisionCheck(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            if (hero.getStopwatchForShield().passedTime()>15){
                hero.setShieldActivated(false);
            }
            for (int i = 0; i< level.getActivePart().getItems().size() ; i++){
                if (level.getActivePart().getItems().get(i).isVisible() && level.getActivePart().getItems().get(i).getX()+ level.getActivePart().getItems().get(i).getWidth()> hero.getX() && level.getActivePart().getItems().get(i).getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getItems().get(i).getY()+ level.getActivePart().getItems().get(i).getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getItems().get(i).getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                    level.getActivePart().getItems().get(i).setVisible(false);
                    if (level.getActivePart().getItems().get(i) instanceof Coin) {
                        hero.addCoin();
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Flower) {
                        hero.addScore(50);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Mushroom){
                        hero.addScore(100);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                    }
                    if (level.getActivePart().getItems().get(i) instanceof Star){
                        hero.addScore(150);
                        if (hero.getMode() == HeroMode.MINI){
                            hero.setMode(HeroMode.MEGA);
                        }
                        else if (hero.getMode() == HeroMode.MEGA){
                            hero.setMode(HeroMode.FIRE);
                        }
                        hero.setShieldActivated(true);
                    }
                    this.changed = true;
                }
            }
        }
    }
    public void checkEnemies(){
        for (Hero hero : this.level.getActivePart().getHeroes()){
            if (!hero.isTransitioning()){
                for (int i = 0; i< level.getActivePart().getEnemies().length ; i++){
                    if (hero.isShieldActivated()){
                        if ((level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX()-60 && level.getActivePart().getEnemies()[i].getX()< hero.getX()-60+ 200 && level.getActivePart().getEnemies()[i].getY()+ level.getActivePart().getEnemies()[i].getHeight()> hero.getY()-40 && level.getActivePart().getEnemies()[i].getY()< hero.getY()-40+ 200){
                            (level.getActivePart().getEnemies()[i]).setVisible(false);
                            hero.setShieldActivated(false);
                            if (level.getActivePart().getEnemies()[i] instanceof Goomba){
                                hero.addScore(100);
                            }
                            if (level.getActivePart().getEnemies()[i] instanceof Koopa){
                                hero.addScore(200);
                            }
                            if (level.getActivePart().getEnemies()[i] instanceof Plant){
                                hero.addScore(100);
                                ((Plant) level.getActivePart().getEnemies()[i]).setDead(true);
                            }
                            if (level.getActivePart().getEnemies()[i] instanceof Spiny){
                                hero.addScore(300);
                            }
                        }
                    }
                    else{
                        if ((level.getActivePart().getEnemies()[i].isVisible()) && (level.getActivePart().getEnemies()[i]) instanceof Goomba && ((Goomba)level.getActivePart().getEnemies()[i]).isDeadActivated()){
                            if (((Goomba)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>400){
                                level.getActivePart().getEnemies()[i].setVisible(false);
                            }
                        }
                        if ((level.getActivePart().getEnemies()[i].isVisible()) && (level.getActivePart().getEnemies()[i]) instanceof Koopa && ((Koopa)level.getActivePart().getEnemies()[i]).isDeadActivated()){
                            if (((Koopa)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>850){
                                level.getActivePart().getEnemies()[i].setVx(0);
                            }
                            if (((Koopa)level.getActivePart().getEnemies()[i]).getDeathStopwatch().passedTime()>3500){
                                ((Koopa) level.getActivePart().getEnemies()[i]).setDeadActivated(false,true);
                            }
                        }
                        if ((level.getActivePart().getEnemies()[i]).isVisible() && level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth()> hero.getX() && level.getActivePart().getEnemies()[i].getX()< hero.getX()+ hero.getWidth() && level.getActivePart().getEnemies()[i].getY()+ level.getActivePart().getEnemies()[i].getHeight()> level.getActivePart().getHeroes()[0].getY() && level.getActivePart().getEnemies()[i].getY()< level.getActivePart().getHeroes()[0].getY()+ level.getActivePart().getHeroes()[0].getHeight()){
                            if (hero.getY()+ hero.getHeight()-10<level.getActivePart().getEnemies()[i].getY() && !(level.getActivePart().getEnemies()[i] instanceof Plant || level.getActivePart().getEnemies()[i] instanceof Spiny) && (hero.getY()-hero.getVy()*dt>=level.getActivePart().getEnemies()[i].getY()-hero.getHeight() && hero.getY()+ hero.getHeight()<level.getActivePart().getEnemies()[i].getY()+level.getActivePart().getEnemies()[i].getHeight() && hero.getX()>level.getActivePart().getEnemies()[i].getX()-hero.getWidth() && hero.getX()<level.getActivePart().getEnemies()[i].getX()+ level.getActivePart().getEnemies()[i].getWidth())) {
                                if (level.getActivePart().getEnemies()[i] instanceof Goomba){
                                    ((Goomba) level.getActivePart().getEnemies()[i]).getDeathStopwatch().start();
                                    ((Goomba) level.getActivePart().getEnemies()[i]).setDeadActivated(true);
                                    hero.addScore(100);
                                    hero.setVy(20);
                                }
                                if (level.getActivePart().getEnemies()[i] instanceof Koopa){
                                    if (((Koopa) level.getActivePart().getEnemies()[i]).isDeadActivated()){
                                        (level.getActivePart().getEnemies()[i]).setVisible(false);
                                        hero.addScore(200);
                                    }
                                    else{
                                        ((Koopa) level.getActivePart().getEnemies()[i]).setDeadActivated(true, hero.getX() + hero.getWidth() / 2 < level.getActivePart().getEnemies()[i].getX() + level.getActivePart().getEnemies()[i].getWidth() / 2);
                                    }
                                    hero.setVy(20);
                                }
                            }
                            else{
                                die(hero);
                            }
                        }
                    }
                }
            }
        }
    }

    public void updatePlants(){
        for (Enemy enemy : level.getActivePart().getEnemies()){
            if (enemy instanceof Plant && !((Plant) enemy).isDead()){
                if (((Plant) enemy).getStopwatch().passedTime()> ((Plant) enemy).getTimePeriod()){
                    ((Plant) enemy).getStopwatch().start();
                    if (enemy.isVisible()){
                        enemy.setVisible(false);
                    }
                    else{
                        enemy.setVisible(true);
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
        if (hero.getMode()==HeroMode.FIRE){
            hero.setMode(HeroMode.MEGA);
            hero.setTransitioning(true);
        }
        else if (hero.getMode()==HeroMode.MEGA){
            hero.setMode(HeroMode.MINI);
            hero.setTransitioning(true);
        }
        else{
            if (hero.getLives()>=2){
                hero.setLives(hero.getLives()-1);
                hero.setCoordinates(new double[]{150,200});
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
}
